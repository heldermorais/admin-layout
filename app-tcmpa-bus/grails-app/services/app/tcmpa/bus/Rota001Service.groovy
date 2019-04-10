package app.tcmpa.bus

import grails.gorm.transactions.Transactional
import org.apache.camel.LoggingLevel
import tcmpa.servicebus.common.ServicebusRouteProcessor

@Transactional
class Rota001Service extends ServicebusRouteProcessor {


    @Override
    void configure() throws Exception {

        from("timer://foo?fixedRate=true&period=5000")
                .log(LoggingLevel.INFO, "Hello World")
                .transform().simple("Hello World")

    }
}
