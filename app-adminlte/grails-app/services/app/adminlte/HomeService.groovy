package app.adminlte

import grails.gorm.transactions.Transactional
import common.backend.ProxiedBean

@ProxiedBean(targetBeanName="homeService")
@Transactional
class HomeService {
  
    static scope = 'session'

    def serviceMethod() {
        log.debug "Isto é o HomeService!"
    }
}
