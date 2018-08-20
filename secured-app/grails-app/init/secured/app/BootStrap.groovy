package secured.app

import demo.RequestMap
import demo.Role
import demo.RoleGroup
import demo.RoleGroupRole
import demo.User
import demo.UserRoleGroup

class BootStrap {

    def springSecurityService


    def init = { servletContext ->


        final boolean flush = true
        final boolean failOnError = true

        def sherlock = new User(username: 'admin', password: 'admin')
        sherlock.save(flush: flush, failOnError: failOnError)

        def watson = new User(username: 'user1', password: 'user1')
        watson.save(flush: flush, failOnError: failOnError)



        def detectives =  new RoleGroup(name: 'Detectives')
        detectives.save(flush: flush, failOnError: failOnError)

        def roleAdmin = new Role(authority: 'ROLE_ADMIN')
        roleAdmin.save(flush: flush, failOnError: true)


        def users =  new RoleGroup(name: 'Users')
        users.save(flush: flush, failOnError: failOnError)

        def roleUser = new Role(authority: 'ROLE_USER')
        roleUser.save(flush: flush, failOnError: true)








        new RoleGroupRole(roleGroup: detectives, role: roleAdmin).save(flush: flush, failOnError: failOnError)
        new RoleGroupRole(roleGroup: users, role: roleUser).save(flush: flush, failOnError: failOnError)


        new UserRoleGroup(user: sherlock, roleGroup: detectives).save(flush: flush, failOnError: failOnError)
        new UserRoleGroup(user: watson, roleGroup: users).save(flush: flush, failOnError: failOnError)









        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            new RequestMap(url: url, configAttribute: 'permitAll').save()
        }

        new RequestMap(url: '/profile/**',    configAttribute: 'ROLE_USER').save()
        new RequestMap(url: '/admin/**',      configAttribute: 'ROLE_ADMIN').save()
        new RequestMap(url: '/admin/role/**', configAttribute: 'ROLE_SUPERVISOR').save()
        new RequestMap(url: '/admin/user/**',
                configAttribute: 'ROLE_ADMIN,ROLE_SUPERVISOR').save()
        new RequestMap(url: '/login/impersonate',
                configAttribute: 'ROLE_SWITCH_USER,isFullyAuthenticated()').save()


        new RequestMap(url: '/secured/show', configAttribute: 'permitAll').save()


        springSecurityService.clearCachedRequestmaps()

    }


    def destroy = {

    }


}
