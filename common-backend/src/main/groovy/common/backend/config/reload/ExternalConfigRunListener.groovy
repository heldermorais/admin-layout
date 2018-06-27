package common.backend.config.reload

import grails.core.GrailsApplication
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.io.FilenameUtils
import org.grails.config.NavigableMapPropertySource
import org.grails.config.yaml.YamlPropertySourceLoader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationHome
import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringApplicationRunListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader



@Slf4j
//@CompileStatic
class ExternalConfigRunListener implements SpringApplicationRunListener {



    protected static String APP_MASTER_CONFIG = "/grails-apps/conf/application.groovy"
    public static final String ENV_GRAILS_CONFIG_LOCATIONS = 'grails.config.locations'


    protected ResourceLoader defaultResourceLoader = new DefaultResourceLoader()
    protected YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader()
//    protected Logger log = LoggerFactory.getLogger('common.backend.config.reload.ExternalConfig')



    protected String appKey = ""



    protected HashSet<String> externalConfigs = new HashSet<>()


    public ExternalConfigRunListener(SpringApplication application, String[] args) {

        APP_MASTER_CONFIG = "${System.getProperty('user.home')}"+APP_MASTER_CONFIG
        this.appKey = application.mainApplicationClass.package.name
        log.debug "Searching for EXTERNAL CONFIG on ${APP_MASTER_CONFIG} to ${this.appKey}"

        this.appKey = "${appKey}.config.locations"

    }


    @Override
    void environmentPrepared(ConfigurableEnvironment environment) {


        externalConfigs.add(APP_MASTER_CONFIG)


        String APP_MASTER_CONFIG_ENV = APP_MASTER_CONFIG.replaceAll(".groovy","-${environment.activeProfiles[0]}.groovy")
        log.debug "Alternative EXTERNAL CONFIG: ${APP_MASTER_CONFIG_ENV}"
        externalConfigs.add(APP_MASTER_CONFIG_ENV)



        List locations  = environment.getProperty(ENV_GRAILS_CONFIG_LOCATIONS, ArrayList, [])
        locations.addAll( environment.getProperty("${this.appKey.toLowerCase()}.config.locations", ArrayList, []) )

        environment.getProperty("${this.appKey.toLowerCase()}.config.locations", ArrayList, []).each {println " My locations ... ${it}" }

        String encoding = environment.getProperty('grails.config.encoding', String, 'UTF-8')




        for (String extConfigName in externalConfigs){


            def configBase = new File(extConfigName)

            if(configBase.exists()) {

                log.debug "Loading EXTERNAL CONFIG from : ${configBase.absolutePath}"
                def config = new ConfigSlurper().parse(configBase.toURL())

                //def lst = config.get("${this.appKey.toLowerCase()}.config.locations")
                def lst = config.flatten()
                for (String item in lst.keySet()){
                    println " ... ${item}"
                }


                Collection locs = lst.get(ENV_GRAILS_CONFIG_LOCATIONS)
                for (String configFileName in locs){

                }

                locations.addAll( locs )

                String apk = "${appKey}.config.locations"
                locs =  lst.get(apk)
                locations.addAll( locs )

                environment.propertySources.addFirst(new MapPropertySource("externalGroovyConfig", config))

            } else {
                log.debug "EXTERNAL CONFIG ${configBase.absolutePath} could not be found."
            }



        }



/*
        println "environmentPrepared ${grails.core.ApplicationAttributes.APPLICATION}"
        ApplicationHome apph = new ApplicationHome()
        String app_name = apph.source != null ? apph.source.name : apph.dir.name
        app_name = FilenameUtils.getName(app_name)
        println " app_name : -> ${app_name}"

        for (String propName in environment.getProperties().keySet()) {

            println " propName : -> ${propName}"

        }



        List locations  = environment.getProperty(ENV_GRAILS_CONFIG_LOCATIONS, ArrayList, [])
        locations.addAll( environment.getProperty("${this.appKey.toLowerCase()}.config.locations", ArrayList, []) )

        environment.getProperty("${this.appKey.toLowerCase()}.config.locations", ArrayList, []).each {println " My locations ... ${it}" }

        String encoding = environment.getProperty('grails.config.encoding', String, 'UTF-8')
*/

        for (location in locations) {
            log.info(" External config file: ${location} ")

            MapPropertySource propertySource = null
            if (location instanceof Class) {
                propertySource = loadClassConfig(location as Class)
            } else {
                String finalLocation = location.toString()
                // Replace ~ with value from system property 'user.home' if set
                String userHome = System.properties.getProperty('user.home')
                if (userHome && finalLocation.startsWith('~/')) {
                    finalLocation = "file:${userHome}${finalLocation[1..-1]}"
                }
                finalLocation = environment.resolvePlaceholders(finalLocation)

                Resource resource = defaultResourceLoader.getResource(finalLocation)
                if (resource.exists()) {
                    if (finalLocation.endsWith('.groovy')) {
                        propertySource = loadGroovyConfig(resource, encoding)
                    } else if (finalLocation.endsWith('.yml')) {
                        environment.activeProfiles
                        propertySource = loadYamlConfig(resource)
                    } else {
                        // Attempt to load the config as plain old properties file (POPF)
                        propertySource = loadPropertiesConfig(resource)
                    }
                } else {
                    log.debug("Config file {} not found", [finalLocation] as Object[])
                }
            }
            if (propertySource?.getSource() && !propertySource.getSource().isEmpty()) {
                environment.propertySources.addFirst(propertySource)
            }
        }
    }

    private MapPropertySource loadClassConfig(Class location) {
        log.info("Loading config class {}", location.name)
        Map properties = new ConfigSlurper(grails.util.Environment.current.name).parse((Class) location)?.flatten()
        new MapPropertySource(location.toString(), properties)
    }

    private MapPropertySource loadGroovyConfig(Resource resource, String encoding) {
        log.info("Loading groovy config file {}", resource.URI)
        String configText = resource.inputStream.getText(encoding)
        Map properties = configText ? new ConfigSlurper(grails.util.Environment.current.name).parse(configText)?.flatten() : [:]
        new MapPropertySource(resource.filename, properties)
    }

    private NavigableMapPropertySource loadYamlConfig(Resource resource) {
        log.info("Loading YAML config file {}", resource.URI)
        NavigableMapPropertySource propertySource = yamlPropertySourceLoader.load(resource.filename, resource, null) as NavigableMapPropertySource
        return propertySource
    }

    private MapPropertySource loadPropertiesConfig(Resource resource) {
        log.info("Loading properties config file {}", resource.URI)
        Properties properties = new Properties()
        properties.load(resource.inputStream)
        new MapPropertySource(resource.filename, properties as Map)
    }

    // Spring Boot 1.4 or higher
    void starting() { }
    // Spring Boot 1.3
    void started() { }

    void contextPrepared(ConfigurableApplicationContext context) { }

    void contextLoaded(ConfigurableApplicationContext context) { }

    void finished(ConfigurableApplicationContext context, Throwable exception) { }
}