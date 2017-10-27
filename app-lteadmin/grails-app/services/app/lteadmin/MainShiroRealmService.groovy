package app.lteadmin

import grails.gorm.transactions.Transactional
import org.apache.shiro.authc.Account
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.Permission
import org.apache.shiro.authz.permission.WildcardPermission
import org.apache.shiro.subject.PrincipalCollection
import shiro.security.AbstractShiroRealm
import shiro.security.ShiroApp
import shiro.security.ShiroRole
import shiro.security.ShiroUser


/**
 * Realm Shiro principal. Esta classe simula a autenticação e autorização de usuários à recursos da app.
 */
@Transactional
class MainShiroRealmService extends AbstractShiroRealm {


    MainShiroRealmService() {

    }


    @Override
    protected Class<? extends AuthenticationToken> getAuthTokenClass() {
        return UsernamePasswordToken
    }

    @Override
    protected Account authenticate(AuthenticationToken authToken) {
        return new SimpleAccount(authToken.principal, authToken.credentials, "MainShiroRealmService")
    }

    @Override
    boolean hasRole(PrincipalCollection subjectPrincipal, String roleIdentifier) {
        log.info "MainShiroRealmService.hasRole( ${roleIdentifier} ) - BEGIN"
        ShiroUser user = ShiroUser.findByLogin(subjectPrincipal.getPrimaryPrincipal())

        boolean achou = (user != null)

        if (achou) {
            achou = false
            for (ShiroRole role in user.roles) {
                if (roleIdentifier == role.name) {
                    achou = true
                    break
                }
            }
        }

        log.info "MainShiroRealmService.hasRole( ${roleIdentifier} ) == ${achou} - END"

        return achou
    }

    @Override
    boolean isPermitted(PrincipalCollection principals, String permission) {
        log.info "MainShiroRealmService.isPermitted( ${permission} ) - BEGIN"
        ShiroUser user = ShiroUser.findByLogin(principals.getPrimaryPrincipal())
        boolean achou = (user != null)


        if (achou) {
            achou = false

            achou = user.isPermitted(permission)

/*
            Permission p1 = new WildcardPermission(permission)

            for (String p in user.getAllPermissions()) {

                log.info "checking : ${p} -> ${permission}"

                Permission pe = getPermissionResolver().resolvePermission(p)

                if (pe.implies(p1)) {
                    achou = true
                    break
                }
            }*/
        }


        log.info "MainShiroRealmService.isPermitted( ${permission} ) == ${achou} - END"
        return achou
    }
}
