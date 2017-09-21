package shiro.security

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class ShiroInterceptorSpec extends Specification implements InterceptorUnitTest<ShiroInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test shiro interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"shiro")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
