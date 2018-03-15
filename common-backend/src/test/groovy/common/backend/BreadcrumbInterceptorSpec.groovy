package common.backend

import common.backend.breadcrumb.BreadcrumbInterceptor
import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class BreadcrumbInterceptorSpec extends Specification implements InterceptorUnitTest<BreadcrumbInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test breadcrumb interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"breadcrumb")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
