package common.backend

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment
import org.springframework.core.env.MapPropertySource

@Transactional
class ExternalConfigSetupService implements EnvironmentAware {

    static  scope    = 'singleton'
    boolean lazyInit = false


    protected HashSet<String> externalConfigs = new HashSet<>()



    GrailsApplication grailsApplication

    @Override
    void setEnvironment(Environment environment) {

        String app_name = grailsApplication.metadata.getApplicationName()

        externalConfigs.add("${System.getProperty('user.home')}/grails-config/${app_name}-config.groovy")
        externalConfigs.add("${System.getProperty('user.home')}/grails-config/${app_name}-config-${environment.activeProfiles[0]}.groovy")

        for (String extConfigName in externalConfigs){

            def configBase = new File(extConfigName)

            if(configBase.exists()) {
                println "Loading external configuration from Groovy: ${configBase.absolutePath}"
                def config = new ConfigSlurper().parse(configBase.toURL())
                environment.propertySources.addFirst(new MapPropertySource("externalGroovyConfig", config))
            } else {
                println "External config could not be found, checked ${configBase.absolutePath}"
            }

        }


    }
}
