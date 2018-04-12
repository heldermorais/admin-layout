package app.adminlte

import common.backend.bean.ProxiedBean
import grails.gorm.transactions.Transactional




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
