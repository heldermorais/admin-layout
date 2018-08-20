package secured.app

import grails.config.Config
import grails.core.support.GrailsConfigurationAware

class HomeController implements GrailsConfigurationAware{

    def staticRules


    def index() {


        render "Aqui esta livre!"
    }

    @Override
    void setConfiguration(Config config) {

        this.staticRules = config.get("grails.plugin.springsecurity.controllerAnnotations.staticRules")
        this.staticRules.add([pattern: '/home/**',      access: ['permitAll']])
        config.put("grails.plugin.springsecurity.controllerAnnotations.staticRules", this.staticRules)

    }
}
