package common.backend

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class BreadCrumbsInterceptorSpec extends Specification implements InterceptorUnitTest<BreadCrumbsInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test breadCrumbs interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"breadCrumbs")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
