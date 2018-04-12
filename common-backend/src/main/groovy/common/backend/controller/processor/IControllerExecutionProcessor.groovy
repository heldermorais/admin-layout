package common.backend.controller.processor

import common.backend.controller.processor.ControllerProcessorException
import grails.artefact.controller.support.RequestForwarder
import grails.artefact.controller.support.ResponseRedirector
import grails.core.GrailsClass
import grails.web.api.WebAttributes
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse




interface IControllerExecutionProcessor extends Command {

    static final String BYPASS_CONTROLLER             = "_ADMIN_BYPASS_CONTROLLER"
    static final String BYPASS_CONTROLLER_REDIRECT_TO = "_ADMIN_BYPASS_CONTROLLER_REDIRECT_TO"

    String getProcessorName()

}
