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

        HttpSession session = request.getSession()

        request.model = new HashMap()



        if (session.isNew()) {
            // Freshly created.
            log.debug "ForEachControllerInterceptor - session.isNew"
        } else {
            // Already created.
            log.debug "ForEachControllerInterceptor - session.alreadyCreated"
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

                log.debug "processor != null"
                request.model.lastRequest << actionDescriptionProcessorService.process(controllerName, actionName)

                //log.debug "processor ${request.model?.admin}"
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
