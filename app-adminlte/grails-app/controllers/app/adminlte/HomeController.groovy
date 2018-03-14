package app.adminlte

import common.backend.breadcrumbs.annotation.BreadCrumbs
import common.backend.breadcrumbs.scope.BreadCrumbsScopeEnum
import common.backend.web.actions.ActionDescription



class HomeController {


    //HomeService proxiedHomeService
    HomeService homeService

    @ActionDescription(title="Home",description="index")
    @BreadCrumbs(scope = BreadCrumbsScopeEnum.SESSION)
    def index() {

        homeService.serviceMethod()
        render "<h1>Home Controller !</h1>"

    }

    
}
