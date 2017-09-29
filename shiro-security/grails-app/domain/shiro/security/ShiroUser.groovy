package shiro.security

import org.apache.shiro.authz.Permission
import org.apache.shiro.authz.permission.WildcardPermission



class ShiroUser {

    String name

    String login
    String password


    static hasMany = [roles: ShiroRole, permissions: String]


    static constraints = {
        name nullable: false, blank: false
    }


    Set<String> getAllPermissions() {

        HashSet<String> tempPermissionNames = new HashSet<String>()

        for (String permissionName in this.permissions) {
            tempPermissionNames.add(permissionName)
        }

        for (ShiroRole role in this.roles) {
            tempPermissionNames.addAll(role.permissions)
        }

        return tempPermissionNames
    }



    boolean isPermitted(String resource) {

        boolean achou = false

        Permission resourcePermission = new WildcardPermission(resource)

        for (String userPermissionName in this.getAllPermissions()) {

            Permission userPermission = new WildcardPermission(userPermissionName)

            if (userPermission.implies(resourcePermission)) {
                achou = true
                break
            }

        }

        return achou

    }
}
