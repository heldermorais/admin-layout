package common.backend.processors

import common.backend.bean.ProxiedBean
import common.backend.controller.Breadcrumb
import common.backend.controller.BreadcrumbLifecycle
import common.backend.controller.processor.ControllerExecutionContext
import common.backend.controller.processor.ControllerProcessorException
import common.backend.controller.processor.GenericControllerExecutionProcessor
import common.backend.controller.processor.IControllerExecutionProcessor
import common.backend.utils.Constants
import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.util.ReflectionUtils

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import java.lang.reflect.Method


@Transactional
class BreadcrumbProcessorService extends GenericControllerExecutionProcessor {


    @Override
    String getProcessorName() {
        return "breadcrumbProcessorService"
    }

    @Override
    boolean execute(ControllerExecutionContext context) throws ControllerProcessorException {
        boolean resultado = Constants.CONTINUE_CHAIN

        HttpSession session = context.request.session

        try{
            if (context.controllerClass != null) {
                log.debug "BreadcrumbProcessorService - execute - Achou controllerClass"


                Breadcrumb ann = extractAnnotationFromMethod(context.controllerClass, context.actionName)

                if (ann != null){
                    process(
                            context.controllerClass,
                            context.actionName,
                            context.ann,
                            context.request,
                            context.response
                           )
                }

            }

        } catch (Exception ex){
            throw new ControllerProcessorException(ex)
        }


        return resultado
    }



    void process(GrailsClass controllerClass, String actionName, Breadcrumb annotation, HttpServletRequest request, HttpServletResponse response) throws ControllerProcessorException {

        HttpSession session = request.session

        try{
            if (controllerClass != null) {
                log.debug "BreadcrumbProcessorService - process - Achou controllerClass"

                //Breadcrumb foundBreadcrumbAnnotation = extractAnnotationFromMethod(controllerClass, actionName)

                if (request.model != null) {
                    request.model = new HashMap()
                }

                if(session.breadcrumbs == null){
                    session.breadcrumbs = new Stack()
                }

                String controllerName = controllerClass.logicalPropertyName
                String label          = controllerName

                boolean addOnBreadcrumbStack = true

                if (annotation != null) {

                    if (annotation.operation() == BreadcrumbLifecycle.START) {
                        session.breadcrumbs.clear()
                    }

                    if (annotation.operation() == BreadcrumbLifecycle.POP) {
                        session.breadcrumbs.pop()
                    }


                    if(annotation.label().equals(".")){
                        label = controllerName
                    }else{
                        label = annotation.label()
                    }

                    addOnBreadcrumbStack = (annotation.operation() != BreadcrumbLifecycle.NONE)
                }


                if(addOnBreadcrumbStack){
                    session.breadcrumbs.push ([
                                                label:          label,
                                                controllerName: controllerName,
                                                actionName:     actionName,
                                                query:          request.queryString
                                              ])
                }

                request.model.breadcrumbs = session.breadcrumbs

            }

        } catch (Exception ex){
            throw new ControllerProcessorException(ex)
        }

    }


    protected Breadcrumb extractAnnotationFromMethod (GrailsClass controllerClass, String methodName){

        Breadcrumb ann = null

        Method method = ReflectionUtils.findMethod(controllerClass.clazz, methodName)
        if (method != null) {

            ann = AnnotationUtils.findAnnotation(method, Breadcrumb.class)

        }

        return ann
    }
}
