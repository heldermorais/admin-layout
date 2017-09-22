package app.lteadmin

import grails.gorm.transactions.Transactional
import org.apache.shiro.authc.Account
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.UsernamePasswordToken
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


    MainShiroRealmService(){


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
        return true
    }

    @Override
    boolean isPermitted(PrincipalCollection principals, String permission) {
        return true
    }
}
