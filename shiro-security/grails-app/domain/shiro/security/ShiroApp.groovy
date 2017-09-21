package shiro.security

class ShiroApp {

    String name

    static hasMany = [ roles: ShiroRole ]

    static constraints = {
        name nullable: false, blank: false
    }

}
