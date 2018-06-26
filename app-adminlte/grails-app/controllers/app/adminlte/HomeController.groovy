package app.adminlte

import common.backend.controller.Breadcrumb
import common.backend.controller.BreadcrumbLifecycle
import common.backend.controller.ActionDescription
import grails.core.GrailsApplication


class HomeController {


    GrailsApplication grailsApplication

    //HomeService proxiedHomeService
    HomeService homeService



    @ActionDescription(title="Home",description="index", breadcrumb = @Breadcrumb(label = "Home (index)" ))
    def index() {

        log.info "HomeController.index(): session ${session.id}"
        log.warn "HomeController.config : ${grailsApplication.config?.mail?.hostname} "

        homeService.serviceMethod()
        //render "<h1>Home Controller !</h1>"

        render view: "index"

    }

    @ActionDescription(title="Home",description="show", breadcrumb = @Breadcrumb(label = "Home (show)", operation = BreadcrumbLifecycle.ADD))
    def show() {

        log.info "HomeController.show(): session ${session.id}"

        homeService.serviceMethod()
        //render "<h1>Home Controller !</h1>"

        render view: "index"

    }


    @ActionDescription(title="Home",description="pop", breadcrumb = @Breadcrumb( label = "Home (pop)", operation = BreadcrumbLifecycle.POP))
    def pop() {

        homeService.serviceMethod()
        //render "<h1>Home Controller !</h1>"

        render view: "index"

    }



}
