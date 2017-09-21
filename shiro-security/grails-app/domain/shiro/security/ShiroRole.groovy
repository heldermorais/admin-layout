package shiro.security

class ShiroRole {

    String name

    static belongsTo = [ app: ShiroApp ]
    static hasMany   = [ permissions: String ]


    static constraints = {
          name nullable:false, blank: false
    }
}
