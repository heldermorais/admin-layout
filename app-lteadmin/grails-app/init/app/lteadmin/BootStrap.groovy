package app.lteadmin

import shiro.security.ShiroApp
import shiro.security.ShiroRole
import shiro.security.ShiroUser


class BootStrap {


    def init = { servletContext ->


        ShiroApp app = new ShiroApp(name: "app-lteadmin")
        ShiroRole adminRole = new ShiroRole(name: "admin", permissions: ["*:*"])
        adminRole.app = app
        app.addToRoles(adminRole)
        app.save()

        ShiroRole userRole = new ShiroRole(name: "user", permissions: ["home:*", "outro:print", "outro:outro"])
        adminRole.app = app
        app.addToRoles(userRole)
        app.save()

        ShiroUser usr1 = new ShiroUser(name: "Helder Morais", login: "helder.morais", password: "abc123")
        usr1.addToRoles (userRole)
        usr1.save()


        log.info "app-LteAdmin - Started."

    }


    def destroy = {
        log.info "app-LteAdmin - Stopped."
    }


}
