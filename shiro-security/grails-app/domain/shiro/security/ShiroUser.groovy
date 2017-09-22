package shiro.security

class ShiroUser {

    String name
    String password

    static hasMany = [ roles: ShiroRole, permissions: String ]


    static constraints = {
        name nullable: false, blank: false
    }

}
