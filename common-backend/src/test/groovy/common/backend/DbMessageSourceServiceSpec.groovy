package common.backend

import common.backend.messagesources.DbMessageSourceService
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class DbMessageSourceServiceSpec extends Specification implements ServiceUnitTest<DbMessageSourceService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
