package common.backend.controller.processor

import grails.artefact.Interceptor
import grails.core.GrailsClass
import org.apache.commons.chain.impl.ContextBase

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerExecutionContext extends ContextBase {

    GrailsClass controllerClass
    String controllerName
    String actionName
    HttpServletRequest request
    HttpServletResponse response

    Interceptor callerInterceptor

}
