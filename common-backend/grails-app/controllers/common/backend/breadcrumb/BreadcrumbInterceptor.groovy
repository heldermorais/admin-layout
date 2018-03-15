package common.backend.breadcrumb

import javax.servlet.http.HttpSession


class BreadcrumbInterceptor {

    int order = HIGHEST_PRECEDENCE + 90

    //BreadcrumbProcessorService breadcrumbProcessorService

    BreadcrumbInterceptor(){
        matchAll()
                .excludes(controller: 'login')
                .excludes(controller: 'auth')
                .excludes(uri: '/static/**')
    }

    boolean before() {

        /*
        HttpSession session = request.getSession(true)


        if (session.isNew()) {
            // Freshly created.
            log.info "ForEachControllerInterceptor - session.isNew (${session.id})"
        } else {
            // Already created.
            log.info "ForEachControllerInterceptor - session.alreadyCreated (${session.id})"
        }



        if(session.breadcrumbs == null){
           session.breadcrumbs = new Stack()
        }


        breadcrumbProcessorService.execute( controllerName, actionName, controllerClass, session, request)

        log.debug "breadcrumbs : ${session.breadcrumbs}"
        */

        return true
    }

    boolean after() {

        if(model == null){
            model = new HashMap()
        }

        //model.breadcrumbs = session?.breadcrumbs

        return true

    }

    void afterView() {
        // no-op
    }
}
