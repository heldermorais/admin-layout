package common.backend

import javax.servlet.http.HttpSession
import org.slf4j.MDC


/**
 * Interceptor que prepara parâmetros padrões dos controllers e extrai informações de {@link ActionDescription} presentes em seus métodos.
 */
class ForEachControllerInterceptor {



    int order = HIGHEST_PRECEDENCE + 100

    ActionDescriptionProcessorService actionDescriptionProcessorService

    ForEachControllerInterceptor() {
        matchAll()
                .excludes(controller: 'login')
                .excludes(controller: 'auth')
                .excludes(uri: '/static/**')
    }

    boolean before() {

        HttpSession session = request.getSession(true)

        request.model = new HashMap()



        if (session.isNew()) {
            // Freshly created.
            log.info "ForEachControllerInterceptor - session.isNew (${session.id})"
        } else {
            // Already created.
            log.info "ForEachControllerInterceptor - session.alreadyCreated (${session.id})"
        }



        MDC.put(Constants.LOG_SESSIONID_KEY,session.id)



        if (request.model.lastRequest == null) {
            log.debug "ForEachControllerInterceptor - initializing request.model.lastRequest"
            request.model.lastRequest = new HashMap()
        }

        if ((controllerName != null) && (!controllerName.isEmpty())) {

            request.model.lastRequest.controllerName = controllerName
            request.model.lastRequest.actionName     = actionName


            request.model.lastRequest.actionTitle       = controllerName
            request.model.lastRequest.actionDescription = actionName


            if(actionDescriptionProcessorService != null){

                log.debug "ActionDescriptionProcessor is acive."
                request.model.lastRequest << actionDescriptionProcessorService.process(controllerName, actionName)
            }


            log.debug "ForEachControllerInterceptor - ${controllerName}.${actionName}"

        }



        return true
    }

    boolean after() {

        if(model == null){
            model = new HashMap()
        }

        model << request?.model

        return true
    }

    void afterView() {
        // no-op
    }
}
