package app.adminlte

import common.backend.breadcrumb.Breadcrumb
import common.backend.breadcrumb.BreadcrumbLifecycle
import common.backend.web.actions.ActionDescription



class HomeController {


    //HomeService proxiedHomeService
    HomeService homeService



    @ActionDescription(title="Home",description="index", breadcrumb = @Breadcrumb(label = "Home (index)" ))
    def index() {

        homeService.serviceMethod()
        //render "<h1>Home Controller !</h1>"

        render view: "index"

    }

    @ActionDescription(title="Home",description="show", breadcrumb = @Breadcrumb(label = "Home (show)", operation = BreadcrumbLifecycle.ADD))
    def show() {

        homeService.serviceMethod()
        //render "<h1>Home Controller !</h1>"

        render view: "index"

    }


    @ActionDescription(title="Home",description="pop", breadcrumb = @Breadcrumb( operation = BreadcrumbLifecycle.POP, label = "Home (pop)" ))
    def pop() {

        homeService.serviceMethod()
        //render "<h1>Home Controller !</h1>"

        render view: "index"

    }



}
