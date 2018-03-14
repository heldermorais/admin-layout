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
            retorno.actionTitle = controllerName
            retorno.actionIcon  = null

            Method method = ReflectionUtils.findMethod(controllerClass.clazz, actionName)
            if (method != null) {
                log.debug "ActionDescriptionProcessor - process - Achou Method"
                retorno.actionDescription = actionName

                String messageTitle       = "${controllerName}.${actionName}.action.title"
                String messageDescription = "${controllerName}.${actionName}.action.description"
                String messageIcon        = "${controllerName}.${actionName}.action.icon"

                if (!(dbMessageSourceService.hasMessage(messageTitle,locale) && dbMessageSourceService.hasMessage(messageDescription,locale))) {

                    ActionDescription ann = AnnotationUtils.findAnnotation(method, ActionDescription)
                    log.debug "ActionDescriptionProcessor - process - Achou Annotation"

                    if (ann != null) {
                        retorno.actionTitle = ann.title()
                        retorno.actionDescription = ann.description()
                        retorno.actionIcon = ann.icon()
                    }

                } else {

                    log.debug "ActionDescriptionProcessor - locale: ${locale.displayLanguage}"

                    Object[] args = []

                    //String messageKey = "${controllerName}.${actionName}.action.title"

                    retorno.actionTitle = dbMessageSourceService.getMessage(messageTitle, args, controllerName, locale)

                    //messageKey = "${controllerName}.${actionName}.action.description"
                    retorno.actionDescription = dbMessageSourceService.getMessage(messageDescription, args, actionName, locale)

                    //messageKey = "${controllerName}.${actionName}.action.icon"
                    retorno.actionIcon = dbMessageSourceService.getMessage(messageIcon, args, "", locale)


                }
            }
        }

        log.debug "ActionDescriptionProcessor - process - retorno= ${retorno}"
        log.debug "ActionDescriptionProcessor - process - END"
        return retorno
    }
}
