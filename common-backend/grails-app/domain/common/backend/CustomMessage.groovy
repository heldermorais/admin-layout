package common.backend

class CustomMessage {

    String code
    Locale locale
    String text


    static constraints = {

        code    unique: true, nullable: false, blank: false
        locale  nullable: true, blank: false
        text    nullable: false, blank: false

    }

}
