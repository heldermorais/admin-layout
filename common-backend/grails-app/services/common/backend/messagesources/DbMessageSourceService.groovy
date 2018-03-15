package common.backend.messagesources

import common.backend.bean.ProxiedBean
import grails.gorm.transactions.Transactional
import org.springframework.context.MessageSource
import org.springframework.context.support.AbstractMessageSource

import java.text.MessageFormat


/**
 * Classe que implementa uma MessageSource, que lê as mensagens a partir do DB.
 * Se não encontrar a mensagem no DB, retorna valor inscrito no MessageSource.
 * Pode ser usada em substituição à MessageSource.
 *
 */
@ProxiedBean
@Transactional
class DbMessageSourceService extends AbstractMessageSource {


    MessageSource messageSource


    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {

        MessageFormat format = null

        // TODO: Implementar consulta em CACHE (EhCache???) para melhorar performance.

        log.info "Procurando por [${code}]"
        CustomMessage msg = CustomMessage.findByCodeAndLocale(code, locale)

        log.info "Msg: ${code} ${msg}"

        if (msg == null) {
            msg = CustomMessage.findByCodeAndLocale(code, null)
        }

        if (msg != null) {

            log.info "Achou MSG"
            //def format = messageCache.get(key)?.value;
            format = new MessageFormat(msg.text, msg.locale);

        } else {

            log.warn "procurando no messageSource Padrão"
            format = messageSource.resolveCode(code, locale)

        }

        return format
    }

    /**
     * Retorna de uma determinada mensagem etá salva no Db.
     *
     * @param code Código da mensagem
     * @param locale Locale (linguagem) da mensagem, ex. "en","pt_BR", etc...
     * @return TRUE se existe a mensagem no locale desejado no Db.
     */
    boolean hasMessageOnDb(String code, Locale locale) {
        CustomMessage msg = CustomMessage.findByCodeAndLocale(code, locale)
        return (msg != null)
    }

}
