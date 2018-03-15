package common.backend.breadcrumb

import common.backend.ControllerProcessorException
import common.backend.GenericControllerExecutionProcessor
import common.backend.bean.ProxiedBean

import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.util.ReflectionUtils

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import java.lang.reflect.Method



@Transactional
class BreadcrumbProcessorService implements GenericControllerExecutionProcessor {


    @Override
    String getProcessorName() {
        return "breadcrumbProcessorService"
    }


    @Override
    void process(GrailsClass controllerClass, String actionName, HttpServletRequest request, HttpServletResponse response) throws ControllerProcessorException {

        HttpSession session = request.session

        try{
            if (controllerClass != null) {
                log.debug "BreadcrumbProcessorService - process - Achou controllerClass"

                Method method = ReflectionUtils.findMethod(controllerClass.clazz, actionName)
                if (method != null) {
                    log.debug "ActionDescriptionProcessor - process - Achou Method"

                    Breadcrumb ann = AnnotationUtils.findAnnotation(method, Breadcrumb)
                    log.debug "ActionDescriptionProcessor - process - Achou Annotation"

                    if (ann != null){
                        process(controllerClass,actionName,ann,request,response)
                    }

                }
            }

        } catch (Exception ex){
            throw new ControllerProcessorException(ex)
        }

    }


    void process(GrailsClass controllerClass, String actionName, Breadcrumb annotation, HttpServletRequest request, HttpServletResponse response) throws ControllerProcessorException {

        HttpSession session = request.session

        try{
            if (controllerClass != null) {
                log.debug "BreadcrumbProcessorService - process - Achou controllerClass"


                Method method = ReflectionUtils.findMethod(controllerClass.clazz, actionName)
                if (method != null) {
                    log.debug "ActionDescriptionProcessor - process - Achou Method"

                    Breadcrumb ann = annotation
                    log.debug "ActionDescriptionProcessor - process - Achou Annotation"

                    if (request.model != null) {
                        request.model = new HashMap()
                    }


                    if(session.breadcrumbs == null){
                        session.breadcrumbs = new Stack()
                    }

                    String controllerName = controllerClass.logicalPropertyName
                    String label = controllerName

                    boolean addBreadCrumb = true

                    if (ann != null) {

                        if (ann.operation() == BreadcrumbLifecycle.START) {
                            session.breadcrumbs.clear()
                        }

                        if (ann.operation() == BreadcrumbLifecycle.POP) {
                            session.breadcrumbs.pop()
                        }


                        if(ann.label().equals(".")){
                            label = controllerName
                        }else{
                            label = ann.label()
                        }

                        addBreadCrumb = (ann.operation() != BreadcrumbLifecycle.NONE)
                    }


                    if(addBreadCrumb){
                        session.breadcrumbs.push ([label: label, controllerName: controllerName, actionName: actionName, query: request.queryString])
                    }

                    request.model.breadcrumbs = session.breadcrumbs
                }
            }

        } catch (Exception ex){
            throw new ControllerProcessorException(ex)
        }

    }
}
