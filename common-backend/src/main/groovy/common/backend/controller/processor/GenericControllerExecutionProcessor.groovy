package common.backend.controller.processor


import org.apache.commons.chain.Context


abstract class GenericControllerExecutionProcessor implements IControllerExecutionProcessor {


    @Override
    boolean execute(Context context) throws Exception {
        return execute((ControllerExecutionContext) context)
    }

    abstract boolean execute(ControllerExecutionContext context) throws ControllerProcessorException


}
