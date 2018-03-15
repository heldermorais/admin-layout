package common.backend

import common.backend.bean.ProxiedBean
import common.backend.breadcrumb.BreadcrumbProcessorService
import common.backend.web.actions.ActionDescription
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
    Map execute(String controllerName, String actionName, Locale locale = new Locale("pt_BR")) {
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

    @Override
    String getProcessorName() {
        return "actionDescriptionProcessorService"
    }

    @Override
    void process(GrailsClass controllerClass, String actionName, HttpServletRequest request, HttpServletResponse response) throws ControllerProcessorException {

        log.debug "ActionDescriptionProcessor - process - BEGIN"
        try {
            if (controllerClass != null) {
                log.debug "ActionDescriptionProcessor - process - Achou controllerClass"

                if (request.model != null) {
                    request.model = new HashMap()
                }

                String controllerName = controllerClass.logicalPropertyName

                request.model.actionTitle = controllerName
                request.model.actionIcon = null

                Method method = ReflectionUtils.findMethod(controllerClass.clazz, actionName)
                if (method != null) {
                    log.debug "ActionDescriptionProcessor - process - Achou Method"
                    request.model.actionDescription = actionName

                    String messageTitleCode       = "${controllerName}.${actionName}.action.title"
                    String messageDescriptionCode = "${controllerName}.${actionName}.action.description"
                    String messageIconCode        = "${controllerName}.${actionName}.action.icon"


                    if (!(dbMessageSourceService.hasMessageOnDb(messageTitleCode, request.locale) && dbMessageSourceService.hasMessageOnDb(messageDescriptionCode, request.locale))) {

                        ActionDescription ann = AnnotationUtils.findAnnotation(method, ActionDescription)
                        log.debug "ActionDescriptionProcessor - process - Achou Annotation"

                        if (ann != null) {

                            request.model.actionTitle = ann.title()
                            request.model.actionDescription = ann.description()
                            request.model.actionIcon = ann.icon()

                            breadcrumbProcessorService.process(controllerClass,actionName,ann.breadcrumb(),request,response)

                        }

                    } else {

                        log.debug "ActionDescriptionProcessor - locale: ${request.locale.displayLanguage}"

                        Object[] args = []

                        request.model.actionTitle       = dbMessageSourceService.getMessage(messageTitleCode, args, controllerName, request.locale)
                        request.model.actionDescription = dbMessageSourceService.getMessage(messageDescriptionCode, args, actionName, request.locale)
                        request.model.actionIcon        = dbMessageSourceService.getMessage(messageIconCode, args, "", request.locale)

                    }
                }
            }
        } catch (Exception ex){
            throw new ControllerProcessorException(ex)
        }

        log.debug "ActionDescriptionProcessor - process - END"

    }
}
