package app.adminlte

import common.backend.ActionDescription

class HomeController {

    @ActionDescription(title="Home",description="index")
    def index() {
        render " Home Controller !"
    }
}
