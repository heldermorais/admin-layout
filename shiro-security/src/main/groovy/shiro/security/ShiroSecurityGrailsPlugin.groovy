package shiro.security

import grails.plugins.*
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy
import org.apache.shiro.authc.pam.ModularRealmAuthenticator
import org.apache.shiro.authz.permission.WildcardPermissionResolver

import org.apache.shiro.realm.Realm
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
import org.apache.shiro.web.mgt.CookieRememberMeManager
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.boot.web.servlet.FilterRegistrationBean
import shiro.security.authc.credential.PasswordHashAdapter

class ShiroSecurityGrailsPlugin extends Plugin {



    def log = LoggerFactory.getLogger(ShiroSecurityGrailsPlugin)

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.3.0 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Shiro Security" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/shiro-security"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() { {->

        def securityConfig = grailsApplication.config.security.shiro

        log.info 'Configuring Shiro ...'

        shiroLifecycleBeanPostProcessor(LifecycleBeanPostProcessor)

        // Shiro annotation support for services...
        shiroAdvisorAutoProxyCreator(DefaultAdvisorAutoProxyCreator) { bean ->
            bean.dependsOn = "shiroLifecycleBeanPostProcessor"
            proxyTargetClass = true
        }

        shiroAuthorizationAttributeSourceAdvisor(AuthorizationAttributeSourceAdvisor) { bean ->
            securityManager = ref("shiroSecurityManager")
        }

        // The default credential matcher.
        credentialMatcher(HashedCredentialsMatcher) { bean ->
            hashAlgorithmName = 'SHA-256'
            storedCredentialsHexEncoded = true
        }

        // This bean allows you to use the credential matcher to encode
        // passwords.
        passwordHashAdapter(PasswordHashAdapter) {
            credentialMatcher = ref("credentialMatcher")
        }

        // Default permission resolver: WildcardPermissionResolver.
        // This converts permission strings into WildcardPermission
        // instances.
        shiroPermissionResolver(WildcardPermissionResolver)

        // Default authentication strategy
        shiroAuthenticationStrategy(AtLeastOneSuccessfulStrategy)

        // Default authenticator
        shiroAuthenticator(ModularRealmAuthenticator) {
            authenticationStrategy = ref("shiroAuthenticationStrategy")
        }

        // Default remember-me manager.
        shiroRememberMeManager(CookieRememberMeManager)

        def sessionMode = securityConfig.session.mode ?: null
        if (sessionMode == null || !sessionMode.equalsIgnoreCase('native')) {
            sessionManager(ServletContainerSessionManager)
        } else {
            sessionManager(DefaultWebSessionManager)
        }

        // The real security manager instance.
        shiroSecurityManager(DefaultWebSecurityManager) { bean ->

            // Allow the user to customise the session type: 'http' or
            // 'native'.
            sessionManager = ref('sessionManager')

            // Allow the user to provide his own versions of these
            // components in resources.xml or resources.groovy.
            authenticator = ref("shiroAuthenticator")
            rememberMeManager = ref("shiroRememberMeManager")

        }

        //Alias
        //      shiroAnnotationSecurityInterceptor(ShiroAnnotationSecurityInterceptor)


        // Create a basic authentication filter bean if the relevant
        // configuration setting is used.
        if (securityConfig.filter.basicAppName) {
            authcBasicFilter(BasicHttpAuthenticationFilter) {
                applicationName = securityConfig.filter.basicAppName
            }
        }


        // Create the main security filter.
        shiroFilter(ShiroFilterFactoryBean) { bean ->

            securityManager = ref("shiroSecurityManager")

            loginUrl        = securityConfig.filter.loginUrl ?: "/auth/login"
            unauthorizedUrl = securityConfig.filter.unauthorizedUrl ?: "/auth/unauthorized"

            if (securityConfig.filter.filterChainDefinitions) {
                filterChainDefinitions = securityConfig.filter.filterChainDefinitions
            }

            if (securityConfig.filter.basicAppName) {
                filters = [authcBasic: ref("authcBasicFilter")]
            }

        }




        //New in Grails 3.0.x
        //instead of web.xml configuration
        log.debug('Filter definition via FilterRegistrationBean')
        servletShiroFilter(FilterRegistrationBean) {
            filter = ref('shiroFilter')
            urlPatterns = ['/*']
            dispatcherTypes = EnumSet.of(javax.servlet.DispatcherType.REQUEST, javax.servlet.DispatcherType.ERROR)
            //order = Ordered.HIGHEST_PRECEDENCE
        }

        log.info 'Shiro Configured'





        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)

        def mgr = applicationContext.getBean("shiroSecurityManager")

        // Add any extra realms that might have been defined in the project
        def beans = applicationContext.getBeanNamesForType(Realm) as List

        // Filter out beans created by the plugin for the realm artefacts.
        beans = beans.findAll { !(it.endsWith("Wrapper") || it.endsWith("Proxy")) }

        // Add the remaining beans to the security manager.
        log.debug "Registering native realms: $beans"
        def realms = beans.collect { applicationContext.getBean(it) }


        if (mgr.realms == null) {
            // If there are no native realms and no normal realms,
            // then there is probably something wrong.
            if (!realms) {
                log.warn "No Shiro realms configured - access control won't work!"
            } else {
                mgr.realms = realms
            }
        } else {
            mgr.realms.addAll(realms)
        }


    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
