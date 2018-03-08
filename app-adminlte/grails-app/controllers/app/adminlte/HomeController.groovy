package app.adminlte

import common.backend.ActionDescription

class HomeController {


    HomeService proxiedHomeService

    @ActionDescription(title="Home",description="index")
    def index() {

        proxiedHomeService.serviceMethod()
        render "<h1>Home Controller !</h1>"

    }

    
}
