package common.backend

import common.backend.bean.ProxiedBean
import common.backend.web.actions.ActionDescription
import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.util.ReflectionUtils

import java.lang.reflect.Method



/**
 * Classe responsável por processar Controllers
 */
@ProxiedBean
@Transactional
class ActionDescriptionProcessorService {



    /**
     * instância de grailsApplication
     */
    GrailsApplication grailsApplication
    //MessageSource messageSource



    /**
     * classe que implementa um MessageSource que recupera as mensagens a partir do DB.
     */
    DbMessageSourceService dbMessageSourceService



    /**
     * Verifica se um controller.action possui alguma indicação de descrição. A verificação acontece primeiro no DB e,
     * se for o case, inferindo se a action possui annotation de descrição {@link ActionDescription}.
     *
     * @param controllerName Nome do controller
     * @param actionName Nome da Action
     * @param locale Linguagem selecionada no request
     *
     * @return HashMap contendo as seguintes chaves: actionTitle, actionDescription e actionIcon. Vide: {@link ActionDescription}
     */
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

                if (!(dbMessageSourceService.hasMessageOnDb(messageTitle,locale) && dbMessageSourceService.hasMessageOnDb(messageDescription,locale))) {

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
