package common.backend

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class ForEachControllerInterceptorSpec extends Specification implements InterceptorUnitTest<ForEachControllerInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test forEachController interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"forEachController")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
