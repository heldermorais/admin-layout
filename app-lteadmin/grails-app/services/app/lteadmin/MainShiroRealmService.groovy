package app.lteadmin

import grails.gorm.transactions.Transactional
import org.apache.shiro.authc.Account
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.subject.PrincipalCollection
import shiro.security.AbstractShiroRealm

@Transactional
class MainShiroRealmService extends AbstractShiroRealm{


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
