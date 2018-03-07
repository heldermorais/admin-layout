package common.backend

import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.util.ReflectionUtils

import java.lang.reflect.Method

@Transactional
class ActionDescriptionProcessorService {


    GrailsApplication grailsApplication

    Map process ( String controllerName, String actionName ){
        log.debug "ActionDescriptionProcessor - process - BEGIN"
        def retorno = [:]

        GrailsClass controllerClass = grailsApplication.getArtefactByLogicalPropertyName("Controller", controllerName)

        if(controllerClass != null){
            log.debug "ActionDescriptionProcessor - process - Achou controllerClass"
            Method method = ReflectionUtils.findMethod(controllerClass.clazz, actionName)
            if (method != null){
                log.debug "ActionDescriptionProcessor - process - Achou Method"
                ActionDescription ann = AnnotationUtils.findAnnotation(method, ActionDescription)
                if(ann != null){
                    log.debug "ActionDescriptionProcessor - process - Achou Annotation"

                    retorno.actionTitle       = ann.title()
                    retorno.actionDescription = ann.description()
                    retorno.actionIcon        = ann.icon()
                }
            }
        }
        log.debug "ActionDescriptionProcessor - process - retorno= ${retorno}"
        log.debug "ActionDescriptionProcessor - process - END"
        return retorno
    }
}
