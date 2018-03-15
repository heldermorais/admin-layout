package common.backend.messagesources

/**
 * Classe de apoia à {@link common.backend.messagesources.DbMessageSourceService}, representando um MessageBundle persistido em Db.
 * Em suma, esta classe permite que mensagens i18n sejam recuperadas a partir do DB.
 */
class CustomMessage {

    /**
     * Código da Mensagem , ex.: default.null.message
     */
    String code

    /**
     * Linguagem em que a mensagem está escrita, ex. "en", "pt_BR"
     */
    Locale locale

    /**
     * Texto da mensagem. Pode conter placeholders para substituição de parâmetros, ex.: O campo [{0}] da classe [{1}] não pode ser vazio
     */
    String text


    static constraints = {

        code    unique: true, nullable: false, blank: false
        locale  nullable: true, blank: true
        text    nullable: false, blank: false

    }

}
