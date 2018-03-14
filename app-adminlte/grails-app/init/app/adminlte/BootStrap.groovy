package app.adminlte

import common.backend.CustomMessage
import common.backend.icons.Fontawsome


class BootStrap {

    def init = { servletContext ->

        /*
        home.index.action.title=Home2
        home.index.action.description=Isto esta FORA da App
        home.index.action.icon=fa-error
         */

        CustomMessage msg = new CustomMessage(code: "home.index.action.title",
                locale: new Locale("pt_BR"),
                text: "From DB: Home")

        msg.save()

        msg = new CustomMessage(code: "home.index.action.description",
                locale: new Locale("pt_BR"),
                text: "From DB: Isto esta no DB")

        msg.save()

        msg = new CustomMessage(code: "home.index.action.icon",
                locale: new Locale("pt_BR"),
                text: "fa-information")

        msg.save()



    }
    def destroy = {
    }
}
