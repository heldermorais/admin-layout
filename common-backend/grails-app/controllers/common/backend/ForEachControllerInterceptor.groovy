package common.backend

import common.backend.utils.Constants
import grails.core.GrailsApplication

import javax.servlet.http.HttpSession
import org.slf4j.MDC


/**
 * Interceptor que prepara parâmetros padrões dos controllers e extrai informações de {@link common.backend.web.actions.ActionDescription} presentes em seus métodos.
 */
class ForEachControllerInterceptor {


    int order = HIGHEST_PRECEDENCE + 100


    /**
     * Service de processamento de {@link common.backend.web.actions.ActionDescription}
     */
    ActionDescriptionProcessorService actionDescriptionProcessorService


    GrailsApplication grailsApplication

    ForEachControllerInterceptor() {
        matchAll()
                .excludes(controller: 'login')
                .excludes(controller: 'auth')
                .excludes(uri: '/static/**')
    }



    /**
     * Executado antes do Request ser enviado ao respectivo Controller.
     *
     * @return DEVE retornar TRUE, sinalizando que o request será repassado ao Controller
     */
    boolean before(){

        HttpSession session = request.getSession(true)

        request.model = new HashMap()


        if (session.isNew()) {
            log.info "ForEachControllerInterceptor - session.isNew (${session.id})"
        } else {
            log.info "ForEachControllerInterceptor - session.alreadyCreated (${session.id})"
        }

        MDC.put(Constants.LOG_SESSIONID_KEY,session.id)


        log.debug "Finding the names of all beans implementing GenericControllerExecutionProcessor... "
        Map processorBeans = grailsApplication.getMainContext().getBeansOfType(GenericControllerExecutionProcessor)

        for (processorName in processorBeans.keySet()){

            GenericControllerExecutionProcessor processor = null

            try{

                //log.debug "Getting instance of bean ${processorName}..."
                processor = processorBeans.get(processorName)

                processor.process(controllerClass, actionName, request, response)
                //log.debug "${processorName} has finished its job."

            }catch (ControllerProcessorException ex){

               log.warn("An exception was raised just before executing [${controllerName}.${actionName}], while ${processor.processorName}.process.",ex)

            }

        }

        log.info "breadcrumbs: ${request.model}"

        if(request[GenericControllerExecutionProcessor.BYPASS_CONTROLLER] == true){
            log.warn "Aborting the execution of [${controllerName}.${actionName}]"

            if(request[GenericControllerExecutionProcessor.BYPASS_CONTROLLER_REDIRECT_TO] != null){
                redirect url: request[GenericControllerExecutionProcessor.BYPASS_CONTROLLER_REDIRECT_TO]
            }

            return false
        }else{
            return true
        }


    }

    boolean beforeOLD() {

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

            request.model.lastRequest.controllerName    = controllerName
            request.model.lastRequest.actionName        = actionName


            request.model.lastRequest.actionTitle       = controllerName
            request.model.lastRequest.actionDescription = actionName


            if(actionDescriptionProcessorService != null){

                log.debug "ActionDescriptionProcessor is acive."
                request.model.lastRequest << actionDescriptionProcessorService.execute(controllerName, actionName, request.locale)

            }


            log.debug "ForEachControllerInterceptor - ${controllerName}.${actionName}"

        }



        return true
    }


    /**
     * Executa após o controller porém antes de renderizar a view.
     *
     * @return DEVE retornar TRUE sinalizando que o request foi processado e vai para a view.
     */
    boolean after() {

        if(model == null){
            model = new HashMap()
        }

        model << request?.model

        return true
    }


    /**
     * Executado após a renderização da view.
     */
    void afterView() {
        // no-op
    }


}
