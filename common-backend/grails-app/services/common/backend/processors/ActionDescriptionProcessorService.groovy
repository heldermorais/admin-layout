package common.backend.processors

import common.backend.messagesources.DbMessageSourceService
import common.backend.controller.ControllerProcessorException
import common.backend.controller.GenericControllerExecutionProcessor
import common.backend.controller.ActionDescription
import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.util.ReflectionUtils

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import java.lang.reflect.Method



/**
 * Classe responsável por processar Controllers
 */
@Transactional
class ActionDescriptionProcessorService implements GenericControllerExecutionProcessor {


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
    void process(GrailsClass controllerClass, String actionName, HttpServletRequest request, HttpServletResponse response) throws ControllerProcessorException {

        log.debug "ActionDescriptionProcessor - process - BEGIN"
        try {
            if (controllerClass != null) {
                
                if (request.model != null) {
                    request.model = new HashMap()
                }

                String controllerName = controllerClass.logicalPropertyName

                request.model.actionTitle = controllerName
                request.model.actionIcon = null


                request.model.actionDescription = actionName

                String messageTitleCode       = "${controllerName}.${actionName}.action.title"
                String messageDescriptionCode = "${controllerName}.${actionName}.action.description"
                String messageIconCode        = "${controllerName}.${actionName}.action.icon"


                if (!(dbMessageSourceService.hasMessageOnDb(messageTitleCode, request.locale) && dbMessageSourceService.hasMessageOnDb(messageDescriptionCode, request.locale))) {

                    ActionDescription ann = extractAnnotationFromMethod(controllerClass,actionName)

                    if (ann != null) {

                        request.model.actionTitle       = ann.title()
                        request.model.actionDescription = ann.description()
                        request.model.actionIcon        = ann.icon()

                        breadcrumbProcessorService.process(controllerClass,actionName,ann.breadcrumb(),request,response)

                    }

                } else {

                    Object[] args = []

                    request.model.actionTitle       = dbMessageSourceService.getMessage(messageTitleCode, args, controllerName, request.locale)
                    request.model.actionDescription = dbMessageSourceService.getMessage(messageDescriptionCode, args, actionName, request.locale)
                    request.model.actionIcon        = dbMessageSourceService.getMessage(messageIconCode, args, "", request.locale)

                }


            }
        } catch (Exception ex){
            throw new ControllerProcessorException(ex)
        }

        log.debug "ActionDescriptionProcessor - process - END"

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
