package common.backend

class ControllerProcessorException extends Exception {

    ControllerProcessorException() {
        super()
    }

    ControllerProcessorException(String message) {
        super(message)
    }

    ControllerProcessorException(String message, Throwable cause) {
        super(message, cause)
    }

    ControllerProcessorException(Throwable cause) {
        super(cause)
    }

    protected ControllerProcessorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace)
    }

}
