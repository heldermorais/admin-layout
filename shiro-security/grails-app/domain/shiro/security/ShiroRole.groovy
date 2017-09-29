package shiro.security






class ShiroRole {

    String name
    ShiroApp app

    static belongsTo = [ app: ShiroApp ]
    static hasMany   = [ permissions: String ]


    static constraints = {
          name nullable:false, blank: false
    }

    void addResourcePermission (String resourcePermission){
        this.permissions.add(resourcePermission)
    }
}
