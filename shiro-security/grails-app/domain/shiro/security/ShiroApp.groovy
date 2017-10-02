package shiro.security





class ShiroApp {

    String nome
    String descricao
    String sigla
    String icone

    static hasMany = [ roles: ShiroRole ]

    static constraints = {

        nome        nullable: false, blank: false
        sigla       nullable: false, blank: false

        icone       nullable: true, blank: true
        descricao   nullable: true, blank: true

    }

    void addRole(ShiroRole newRole){
         newRole.app = this
         roles.add(newRole)
    }

}
