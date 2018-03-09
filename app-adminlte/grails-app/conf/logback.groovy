import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '[%date] [%X{sessionId:--}] [%level] [%logger{10}] [%file:%line] [%msg]%n'

    }
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
root(ERROR, ['STDOUT'])


logger 'common.backend', WARN, ['STDOUT'], false
logger 'common.backend.ForEachControllerInterceptor', WARN, ['STDOUT'], false
logger 'common.backend.ActionDescriptionProcessorService', WARN, ['STDOUT'], false
logger 'common.backend.ProxiedSessionBeanPostProcessor', DEBUG, ['STDOUT'], false
logger 'app.adminlte', DEBUG, ['STDOUT'], false
