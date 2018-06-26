package common.backend.config.reload

import grails.core.GrailsApplication
import groovy.transform.CompileStatic
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
import org.springframework.util.FileSystemUtils


@CompileStatic
class ExternalConfigRunListener implements SpringApplicationRunListener {

    private ResourceLoader defaultResourceLoader = new DefaultResourceLoader()
    private YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader()
    private Logger log = LoggerFactory.getLogger('common.backend.config.reload.ExternalConfig')



    public ExternalConfigRunListener(SpringApplication application, String[] args) {

       if(application instanceof GrailsApplication){
           println "GrailsApp !"
       }else{
           println "SpringApp ! ${application}"
       }

    }




    @Override
    void environmentPrepared(ConfigurableEnvironment environment) {


        println "environmentPrepared ${grails.core.ApplicationAttributes.APPLICATION}"
        ApplicationHome apph = new ApplicationHome()
        String app_name = apph.source != null ? apph.source.name : apph.dir.name
        app_name = FilenameUtils.getName(app_name)
        println " app_name : -> ${app_name}"

        for (String propName in environment.getProperties().keySet()){

            println " propName : -> ${propName}"

        }



        List locations  = environment.getProperty('grails.config.locations', ArrayList, [])
        String encoding = environment.getProperty('grails.config.encoding', String, 'UTF-8')

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
    void starting() { println "Starting" }
    // Spring Boot 1.3
    void started() {  println "Started" }
    void contextPrepared(ConfigurableApplicationContext context) { println "contextPrepared" }
    void contextLoaded(ConfigurableApplicationContext context) { println "contextLoaded" }
    void finished(ConfigurableApplicationContext context, Throwable exception) { println "finished" }
}