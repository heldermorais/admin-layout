package common.backend

class Notification {

    String texto
    String icon
    Date   dataEmissao


    static constraints = {
        texto nullable: false, blank: false
        icon  nullable: true , blank: true
        dataEmissao nullable: false, blank: false
    }


}
