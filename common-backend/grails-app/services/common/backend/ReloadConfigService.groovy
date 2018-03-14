package common.backend

import grails.gorm.transactions.Transactional
import grails.util.Environment

@Transactional
class ReloadConfigService {


    def grailsApplication


    def reload( String fileName ) {
        try {

            log.debug("Config file ${fileName} - BEFORE")

            if (fileName.startsWith("~") ) {
                fileName = System.getProperty("user.home") +"/"+ fileName.substring(2);
            }

            log.debug("Config file ${fileName} - AFTER")


            File configFile = null

            if(fileName.startsWith("file:")){
                configFile = new File(fileName.replace("file:", "")).absoluteFile
            }else{
                configFile = new File(fileName)
            }


            ConfigSlurper configSlurper = new ConfigSlurper(Environment.getCurrent().getName())
            ConfigObject updatedConfig
            updatedConfig = configSlurper.parse(configFile.text)
            grailsApplication.config.merge(updatedConfig)

            log.debug("Config file ${fileName} changes merged successfully.");

        } catch (Throwable e) {
            log.error("Failed parsing and merging config file ${fileName} changes. ${e.message}")
        }

    }

    def reload() {

        def files = grailsApplication.config.grails.config.locations

        files.each { String fileName ->

            reload( fileName )

        }

    }


}
