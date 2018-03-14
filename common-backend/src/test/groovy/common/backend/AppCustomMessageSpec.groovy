package common.backend

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AppCustomMessageSpec extends Specification implements DomainUnitTest<CustomMessage> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
