package app.adminlte

import common.backend.ActionDescription

class HomeController {


    HomeService proxied_homeService

    @ActionDescription(title="Home",description="index")
    def index() {

        proxied_homeService.serviceMethod()
        render "<h1>Home Controller !</h1>"

    }
}
