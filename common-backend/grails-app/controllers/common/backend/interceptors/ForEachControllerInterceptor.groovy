package common.backend.interceptors

import common.backend.controller.processor.ControllerExecutionContext
import common.backend.controller.processor.ControllerProcessorException
import common.backend.controller.processor.IControllerExecutionProcessor
import common.backend.processors.ActionDescriptionProcessorService
import common.backend.utils.Constants
import grails.core.GrailsApplication

import javax.servlet.http.HttpSession
import org.slf4j.MDC


/**
 * Interceptor que prepara parâmetros padrões dos controllers e extrai informações de {@link common.backend.controller.ActionDescription} presentes em seus métodos.
 */
class ForEachControllerInterceptor {


    int order = HIGHEST_PRECEDENCE + 100


    /**
     * Service de processamento de {@link common.backend.controller.ActionDescription}
     */
    ActionDescriptionProcessorService actionDescriptionProcessorService


    GrailsApplication grailsApplication

    ForEachControllerInterceptor() {

        matchAll()
                .excludes(controller: 'login')
                .excludes(controller: 'logon')
                .excludes(controller: 'logout')
                .excludes(controller: 'logoff')
                .excludes(controller: 'auth')
                .excludes(uri: '/static/**')

    }



    /**
     * Executado antes do Request ser enviado ao respectivo Controller.
     *
     * @return DEVE retornar TRUE, sinalizando que o request será repassado ao Controller
     */
    boolean before(){

        boolean resultado = true

        //HttpSession session = session

        flash.model = new HashMap()


        if (session.isNew()) {
            log.info "ForEachControllerInterceptor - session.isNew (${session.id})"
            session.putAt("Zzz", "Helder")
        } else {
            log.info "ForEachControllerInterceptor - session.alreadyCreated (${session.id})"
        }


        MDC.put(Constants.LOG_SESSIONID_KEY,session.id)


        log.debug "Finding the names of all beans implementing IControllerExecutionProcessor... "
        Map processorBeans = grailsApplication.getMainContext().getBeansOfType(IControllerExecutionProcessor)

        ControllerExecutionContext context = new ControllerExecutionContext()
        context.actionName        = actionName
        context.controllerClass   = controllerClass
        context.controllerName    = controllerName
        context.request           = request
        context.response          = response
        context.flash             = flash
        context.callerInterceptor = this

        for (processorName in processorBeans.keySet()){

            IControllerExecutionProcessor processor = null

            try{

                log.debug "Getting instance of bean ${processorName}..."
                processor = processorBeans.get(processorName)

                boolean res = processor.execute(context)

                if(res == Constants.ABORT_CHAIN){
                    resultado = false
                    break
                }

                log.debug "${processorName} has finished its job."

            }catch (ControllerProcessorException ex){

               log.warn("An exception was raised just before executing [${controllerName}.${actionName}], while ${processor.processorName}.process.",ex)

            }

        }


        //log.info "breadcrumbs: ${request.model}"

/*
        if(request[IControllerExecutionProcessor.BYPASS_CONTROLLER] == true){
            log.warn "Aborting the execution of [${controllerName}.${actionName}]"

            if(request[IControllerExecutionProcessor.BYPASS_CONTROLLER_REDIRECT_TO] != null){
                redirect url: request[IControllerExecutionProcessor.BYPASS_CONTROLLER_REDIRECT_TO]
            }

            return false
        }else{
            return true
        }
*/


        /*log.info "Executando ${context.controllerName}.${context.actionName}()"

        if ((context?.controllerName?.equalsIgnoreCase("home") )&&(context?.actionName?.equalsIgnoreCase("index"))){
            log.warn "Redirecting to [home.show]"
            context.callerInterceptor.redirect controller: "home", action: "show"
            resultado = false
        }*/


        return resultado

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

        model << flash?.model

        return true
    }


    /**
     * Executado após a renderização da view.
     */
    void afterView() {
        // no-op
    }


}
