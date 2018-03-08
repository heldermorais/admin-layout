package app.adminlte

import common.backend.ActionDescription

class HomeController {

    @ActionDescription(title="Home",description="index")
    def index() {
        render "<h1>Home Controller !</h1>"
    }
}
