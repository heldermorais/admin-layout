package app.lteadmin


class BootStrap {


    def init = { servletContext ->

        log.info "app-LteAdmin - Started."

    }
    def destroy = {
        log.info "app-LteAdmin - Stopped."
    }
}
