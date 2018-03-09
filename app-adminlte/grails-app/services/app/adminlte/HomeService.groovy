package app.adminlte

import grails.gorm.transactions.Transactional
import common.backend.ProxiedBean


@ProxiedBean()
@Transactional
class HomeService {
  
    static scope = 'session'

    public HomeService(){
        log.warn "Inicializou HomeService"
    }


    def serviceMethod() {
        log.debug "Isto é o HomeService!"
    }

}
