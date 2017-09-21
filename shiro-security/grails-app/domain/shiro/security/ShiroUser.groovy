package shiro.security

class ShiroUser {

    String name

    static hasMany = [ roles: ShiroRole, permissions: String ]


    static constraints = {
        name nullable: false, blank: false
    }

}
