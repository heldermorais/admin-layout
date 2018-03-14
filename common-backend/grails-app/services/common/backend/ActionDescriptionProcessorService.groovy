package common.backend

import common.backend.web.actions.ActionDescription
import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import org.springframework.context.MessageSource
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.util.ReflectionUtils

import java.lang.reflect.Method

@Transactional
class ActionDescriptionProcessorService {


    GrailsApplication grailsApplication
    //MessageSource messageSource

    DbMessageSourceService dbMessageSourceService


    Map process(String controllerName, String actionName, Locale locale = new Locale("pt_BR")) {
        log.debug "ActionDescriptionProcessor - process - BEGIN"
        def retorno = [:]

        GrailsClass controllerClass = grailsApplication.getArtefactByLogicalPropertyName("Controller", controllerName)

        if (controllerClass != null) {
            log.debug "ActionDescriptionProcessor - process - Achou controllerClass"

            Method method = ReflectionUtils.findMethod(controllerClass.clazz, actionName)
            if (method != null) {
                log.debug "ActionDescriptionProcessor - process - Achou Method"

                ActionDescription ann = AnnotationUtils.findAnnotation(method, ActionDescription)

                if (ann != null) {

                    log.debug "ActionDescriptionProcessor - process - Achou Annotation"

                    retorno.actionTitle       = ann.title()
                    retorno.actionDescription = ann.description()
                    retorno.actionIcon        = ann.icon()

                } else {

                    log.debug "ActionDescriptionProcessor - locale: ${locale.displayLanguage}"

                    Object[] args = []

                    String messageKey = "${controllerName}.${actionName}.action.title"
                    retorno.actionTitle = dbMessageSourceService.getMessage(messageKey, args, controllerName, locale)

                    messageKey = "${controllerName}.${actionName}.action.description"
                    retorno.actionDescription = dbMessageSourceService.getMessage(messageKey, args, actionName, locale)

                    messageKey = "${controllerName}.${actionName}.action.icon"
                    retorno.actionIcon = dbMessageSourceService.getMessage(messageKey, args, "", locale)


                }
            }
        }

        log.debug "ActionDescriptionProcessor - process - retorno= ${retorno}"
        log.debug "ActionDescriptionProcessor - process - END"
        return retorno
    }
}
