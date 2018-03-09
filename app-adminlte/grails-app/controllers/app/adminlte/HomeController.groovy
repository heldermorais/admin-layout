package app.adminlte

import common.backend.ActionDescription



class HomeController {


    //HomeService proxiedHomeService
    HomeService homeService

    @ActionDescription(title="Home",description="index")
    def index() {

        homeService.serviceMethod()
        render "<h1>Home Controller !</h1>"

    }

    
}
