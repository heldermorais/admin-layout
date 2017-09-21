package gui.adminlayout

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class AdminLayoutInterceptorSpec extends Specification implements InterceptorUnitTest<AdminLayoutInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test adminLayout interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"adminLayout")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
