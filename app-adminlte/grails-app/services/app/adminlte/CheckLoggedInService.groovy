package app.adminlte

import common.backend.bean.ProxiedBean
import common.backend.controller.processor.ControllerExecutionContext
import common.backend.controller.processor.ControllerProcessorException
import common.backend.controller.processor.GenericControllerExecutionProcessor
import common.backend.utils.Constants
import grails.gorm.transactions.Transactional



@Transactional
class CheckLoggedInService extends GenericControllerExecutionProcessor{

    @Override
    boolean execute(ControllerExecutionContext context) throws ControllerProcessorException {

        boolean resultado = Constants.CONTINUE_CHAIN
        /*log.info "Executando ${context.controllerName}.${context.actionName}()"

        if ((context?.controllerName?.equalsIgnoreCase("home") )&&(context?.actionName?.equalsIgnoreCase("index"))){
            log.warn "Redirecting to [home.show]"
            context.callerInterceptor.redirect controller: "home", action: "show"
            resultado = Constants.ABORT_CHAIN
        }
*/
        return resultado
    }

    @Override
    String getProcessorName() {
        return this.class.canonicalName
    }
}
