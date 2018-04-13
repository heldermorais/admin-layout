package common.backend.processors

import common.backend.bean.ProxiedBean
import common.backend.controller.processor.ControllerExecutionContext
import common.backend.controller.processor.GenericControllerExecutionProcessor
import common.backend.messagesources.DbMessageSourceService
import common.backend.controller.processor.ControllerProcessorException
import common.backend.controller.processor.IControllerExecutionProcessor
import common.backend.controller.ActionDescription
import common.backend.utils.Constants
import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.util.ReflectionUtils


import java.lang.reflect.Method



/**
 * Classe responsável por processar Controllers
 */
@Transactional
class ActionDescriptionProcessorService extends GenericControllerExecutionProcessor {


    /**
     * instância de grailsApplication
     */
    GrailsApplication grailsApplication
    //MessageSource messageSource



    /**
     * classe que implementa um MessageSource que recupera as mensagens a partir do DB.
     */
    DbMessageSourceService dbMessageSourceService


    BreadcrumbProcessorService breadcrumbProcessorService


    @Override
    String getProcessorName() {
        return "actionDescriptionProcessorService"
    }

    @Override
    boolean execute(ControllerExecutionContext context) throws ControllerProcessorException {
        boolean resultado = Constants.CONTINUE_CHAIN


        log.debug "ActionDescriptionProcessor - process - BEGIN"
        try {
            if (context.controllerClass != null) {

                if (context.flash.model != null) {
                    context.flash.model = new HashMap()
                }

                String controllerName = context.controllerClass.logicalPropertyName

                context.flash.model.actionTitle = context.controllerName
                context.flash.model.actionIcon = null


                context.flash.model.actionDescription = context.actionName

                String messageTitleCode       = "${controllerName}.${context.actionName}.action.title"
                String messageDescriptionCode = "${controllerName}.${context.actionName}.action.description"
                String messageIconCode        = "${controllerName}.${context.actionName}.action.icon"


                if (!(dbMessageSourceService.hasMessageOnDb(messageTitleCode, context.request.locale) && dbMessageSourceService.hasMessageOnDb(messageDescriptionCode, context.request.locale))) {

                    ActionDescription ann = extractAnnotationFromMethod(context.controllerClass, context.actionName)

                    if (ann != null) {

                        context.flash.model.actionTitle       = ann.title()
                        context.flash.model.actionDescription = ann.description()
                        context.flash.model.actionIcon        = ann.icon()

                        //breadcrumbProcessorService.process(controllerClass,actionName,ann.breadcrumb(),request,response)

                    }

                } else {

                    Object[] args = []

                    context.flash.model.actionTitle       = dbMessageSourceService.getMessage(messageTitleCode, args, context.controllerName, context.request.locale)
                    context.flash.model.actionDescription = dbMessageSourceService.getMessage(messageDescriptionCode, args, context.actionName, context.request.locale)
                    context.flash.model.actionIcon        = dbMessageSourceService.getMessage(messageIconCode, args, "", context.request.locale)

                }


            }
        } catch (Exception ex){
            throw new ControllerProcessorException(ex)
        }

        log.debug "ActionDescriptionProcessor - process - END"


        return resultado
    }



    protected ActionDescription extractAnnotationFromMethod (GrailsClass controllerClass, String methodName){

        ActionDescription ann = null

        Method method = ReflectionUtils.findMethod(controllerClass.clazz, methodName)
        if (method != null) {

            ann = AnnotationUtils.findAnnotation(method, ActionDescription.class)

        }

        return ann
    }
}
