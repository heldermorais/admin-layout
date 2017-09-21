package shiro.security


import org.apache.shiro.authc.Account
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.credential.CredentialsMatcher
import org.apache.shiro.authz.AuthorizationException
import org.apache.shiro.authz.Authorizer
import org.apache.shiro.authz.Permission
import org.apache.shiro.authz.UnauthorizedException
import org.apache.shiro.authz.permission.InvalidPermissionStringException
import org.apache.shiro.authz.permission.PermissionResolver
import org.apache.shiro.authz.permission.PermissionResolverAware
import org.apache.shiro.realm.Realm
import org.apache.shiro.subject.PrincipalCollection
import org.apache.shiro.subject.SimplePrincipalCollection
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * Classe que deve ser implementada por TODOS os services que servirão como Realms do Shiro.
 *
 * @author heldermorais
 *
 */
abstract class AbstractShiroRealm implements Realm, Authorizer, PermissionResolverAware {

    /**
     * Log
     */
    protected static final Logger log = LoggerFactory.getLogger(AbstractShiroRealm)

    /**
     * Método que deve ser implementado pelas classes descendentes, para definir o tipo de token que será usado para a autenticação nesta Realm.
     * @return classe descendente de AuthenticationToken que será suportada por esta Realm.
     */
    protected abstract Class<? extends AuthenticationToken> getAuthTokenClass();


    /**
     * Método deve implementar o procedimento de autenticação para esta Realm.
     *
     * @param authToken instancia do token para autenticação. deve ser do tipo indicado em {@link #getAuthTokenClass}.
     * @return instância de Account (Shiro) representando o usuário logado.
     */
    protected abstract Account authenticate(AuthenticationToken authToken);



    @Override
    public boolean isPermitted(PrincipalCollection subjectPrincipal, Permission permission){
        return isPermitted(principals, permission.toString() );
    };



    public abstract boolean hasRole(PrincipalCollection subjectPrincipal, String roleIdentifier);


    public abstract boolean isPermitted(PrincipalCollection principals, String permission) ;


    @Override
    public String getName() {
        return this.class.name
    }

    @Override
    public boolean supports(AuthenticationToken token) {

        Class<? extends AuthenticationToken> cl = getAuthTokenClass()

        if (cl) {
            return cl.isAssignableFrom(token.getClass())
        }
        else {
            return false
        }
    }

    @Override
    AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            def account = authenticate(authenticationToken)

            if (account instanceof AuthenticationInfo) {
                return account
            }
            else {
                // Create an account to host the returned principals.
                def principals = []
                if (account instanceof Collection) {
                    principals.addAll(account)
                }
                else {
                    principals << account
                }

                return new SimpleAuthenticationInfo(new SimplePrincipalCollection(account, getName()), null)
            }
        }
        catch (MissingMethodException ex) {
            // No authentication performed.
            if (ex.message.indexOf('authenticate')) {
                log.error "Unable to authenticate with ${getName()}", ex
            }
            return null
        }
        catch (Exception ex) {

            log.info "Unable to authenticate with ${getName()} - ${ex.message}"
            log.debug 'The exception', ex

            throw ex
        }
    }










    @Override
    public boolean[] isPermitted(PrincipalCollection subjectPrincipal, List<Permission> permissions) {
        boolean[] resultados = null
        if ((permissions != null)&&(!permissions.empty)){

            resultados = new boolean[permissions.size()]
            int i = 0;
            for (Permission perm in permissions) {
                resultados[i] = isPermitted(subjectPrincipal, perm)
                i++
            }
        }else{

            resultados = new boolean[permissions.size()]
            for (int i in 0..<permissions.size()) {
                resultados[i] = false;
            }
        }

        return resultados;
    }



    @Override
    public boolean[] isPermitted(PrincipalCollection subjectPrincipal, String... permissions) {
        return isPermitted(subjectPrincipal, toPermissionList(permissions))
    }





    @Override
    public boolean isPermittedAll(PrincipalCollection subjectPrincipal, String... permissions) {
        return isPermittedAll(subjectPrincipal, toPermissionList(permissions))
    }




    @Override
    public boolean isPermittedAll(PrincipalCollection subjectPrincipal, Collection<Permission> permissions) {

        boolean resultados = true

        if ((permissions != null)){

            for (Permission perm in permissions) {
                resultados = resultados && isPermitted(subjectPrincipal, perm)
            }
        }

        return resultados;
    }





    @Override
    public void checkPermission(PrincipalCollection subjectPrincipal, Permission permission)
            throws AuthorizationException {

        if(!isPermitted(subjectPrincipal, permission)){
            throw new UnauthorizedException("User '${subjectPrincipal.name}' does not have permission '${permission.toString()}'")
        }
    }


    @Override
    public void checkPermission(PrincipalCollection subjectPrincipal, String permission) throws AuthorizationException {
        checkPermission( subjectPrincipal, toPermission(permission) )
    }




    @Override
    public void checkPermissions(PrincipalCollection subjectPrincipal, Collection<Permission> permissions)
            throws AuthorizationException {

        if(permissions != null){
            for (Permission perm in permissions) {
                checkPermission(subjectPrincipal,perm)
            }
        }
    }




    @Override
    public void checkPermissions(PrincipalCollection subjectPrincipal, String... permissions)
            throws AuthorizationException {

        ArrayList<Permission> perms = toPermissionList(Arrays.asList(permissions))
        checkPermissions(subjectPrincipal, perms)
    }








    @Override
    public boolean[] hasRoles(PrincipalCollection subjectPrincipal, List<String> roleIdentifiers) {
        boolean[] resultados = null
        if ((roleIdentifiers != null)&&(!roleIdentifiers.empty)){

            resultados = new boolean[roleIdentifiers.size()]
            int i = 0;
            for (String roleId in roleIdentifiers) {
                resultados[i] = hasRole(subjectPrincipal, roleId)
                i++
            }
        }else{

            resultados = new boolean[roleIdentifiers.size()]
            for (int i in 0..<roleIdentifiers.size()) {
                resultados[i] = false;
            }
        }

        return resultados;
    }



    @Override
    public boolean hasAllRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers) {

        boolean hasAll = true

        if ((roleIdentifiers != null)){
            for (String roleId in roleIdentifiers) {
                hasAll = hasAll && hasRole(subjectPrincipal, roleId)
            }
        }

        return hasAll;
    }




    @Override
    public void checkRole(PrincipalCollection subjectPrincipal, String roleIdentifier) throws AuthorizationException {

        if (!hasRole(subjectPrincipal, roleIdentifier)) {
            throw new UnauthorizedException("User '${subjectPrincipal.name}' does not have role '${roleIdentifier}'")
        }
    }

    @Override
    public void checkRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers)
            throws AuthorizationException {

        if(roleIdentifiers != null){
            for (String roleId in roleIdentifiers) {
                checkRole(subjectPrincipal, roleId) // call checkRole(PrincipalCollection subjectPrincipal, String roleIdentifier)
            }
        }
    }

    @Override
    public void checkRoles(PrincipalCollection subjectPrincipal, String... roleIdentifiers)
            throws AuthorizationException {
        checkRoles ( subjectPrincipal ,  Arrays.asList(roleIdentifiers) ) // call checkRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers)
    }




    PermissionResolver permissionResolver

    @Override
    @Autowired
    public void setPermissionResolver(PermissionResolver pr) {
//		println "setPermissionResolver"
        this.permissionResolver = pr
    }



    CredentialsMatcher credentialMatcher

    @Autowired
    public void setCredentialMatcher (CredentialsMatcher cm){
        this.credentialMatcher = cm
    }

    /**
     * Converts a single permission string into a Permission instances.
     */
    private Permission toPermission(String s) {

        if (this.permissionResolver == null){
            log.debug "this.permissionResolver == null"
            return null;
        }
        try {
            return this.permissionResolver.resolvePermission(s);
        }
        catch (InvalidPermissionStringException ex) {
            // Nothing we can do about it
            return null; //@todo Is returning null the right thing to do?
        }
    }




    /**
     * Converts an array of string permissions into a list of
     * {@link WildcardPermission} instances.
     */
    private List toPermissionList(String[] strings) {
        List permissions = new ArrayList(strings.length);
        for (int i = 0; i < strings.length; i++) {
            permissions.add(toPermission(strings[i]));
        }

        return permissions;
    }




    private getFirstPrincipal(PrincipalCollection principal) {
        return principal.asList()[0]
    }
}
