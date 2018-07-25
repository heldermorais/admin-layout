package secured.app

import grails.plugin.springsecurity.annotation.Secured

class SecuredController {

    @Secured('ROLE_ADMIN')
    def index() {
        render 'Secure access only'
    }

    def show() {
        render "Aqui esta livre também (show)!"
    }
}
