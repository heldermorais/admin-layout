package common.backend

import common.backend.bean.ProxiedBean
import grails.gorm.transactions.Transactional
import org.springframework.context.MessageSource
import org.springframework.context.support.AbstractMessageSource

import java.text.MessageFormat


@Transactional
class DbMessageSourceService extends AbstractMessageSource {



    MessageSource messageSource

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {

        MessageFormat format = null

        log.info "Procurando por [${code}]"
        CustomMessage msg = CustomMessage.findByCodeAndLocale(code, locale)

        log.info "Msg: ${code} ${msg}"

        if(msg == null){
            msg = CustomMessage.findByCode(code)
        }

        if(msg != null){

            log.info "Achou MSG"
            //def format = messageCache.get(key)?.value;
            format = new MessageFormat(msg.text, msg.locale);

        }else{

            log.warn "procurando no messageSource Padrão"
            format = messageSource.resolveCode(code, locale)

        }

        return format
    }


    boolean hasMessage( String code, Locale locale ){
        CustomMessage msg = CustomMessage.findByCodeAndLocale(code, locale)
        return (msg != null)
    }

}
