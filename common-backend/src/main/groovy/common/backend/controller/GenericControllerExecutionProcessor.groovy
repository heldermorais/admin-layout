package common.backend.controller

import grails.core.GrailsClass

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse




interface GenericControllerExecutionProcessor {

    static final String BYPASS_CONTROLLER             = "_ADMIN_BYPASS_CONTROLLER"
    static final String BYPASS_CONTROLLER_REDIRECT_TO = "_ADMIN_BYPASS_CONTROLLER_REDIRECT_TO"

    String getProcessorName()

    void process(GrailsClass controllerClass, String actionName,
                 HttpServletRequest request , HttpServletResponse response) throws ControllerProcessorException

}
