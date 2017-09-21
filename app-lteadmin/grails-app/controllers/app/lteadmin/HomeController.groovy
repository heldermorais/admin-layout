package app.lteadmin

import common.backend.ActionDescription
import common.backend.icons.Fontawsome
import grails.util.Environment

class HomeController {

    @ActionDescription(title = "Home", description = "Action padrão.", icon = Fontawsome.FA_HOME)
    def index() {


        String cnf  = grailsApplication.config.getProperty('foo.bar.hello')

        String cnf2 = grailsApplication.config.getProperty('app.username')

        log.info "HomeController - Index2: ${cnf}"
        log.info "HomeController - Index2: ${cnf2}"


    }


    def reloadConfigService

    def reloadConfig() {

        reloadConfigService.reload()

        redirect action:'index'

    }
}
