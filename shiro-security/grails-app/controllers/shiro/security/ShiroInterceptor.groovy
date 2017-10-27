package shiro.security

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject


class ShiroInterceptor {


    int order = HIGHEST_PRECEDENCE + 10

    String redirectUri = null


    ShiroInterceptor(){

        matchAll()
                .excludes(controller: 'login')
                .excludes(controller: 'auth')
                .excludes(uri: '/static/**')
                .excludes(uri:'/favicon.ico')
                .excludes(controller: 'favicon')

    }


    boolean before() {

        // condições em que o interceptor DEVE autorizar o request ....
        if (!controllerName){
            log.debug "controllerName is null. By passing ..."
            return true;                                 // Request de conteudo estático...
        }
        if (controllerName == "auth"){
            log.debug "controllerName is auth. By passing ..."
            return true;                                // Request para o controller de autenticacao padrao...
        }
        if (controllerName == "static"){
            log.debug "controllerName is static. By passing ..."
            return true;                                // Request para static como controller...
        }



        redirectUri = grailsApplication.config?.security?.shiro?.redirect?.uri
        if ( (redirectUri != null )&&(request.forwardURI.toString().startsWith(redirectUri)) ) {
            log.debug "request is security.shiro.redirect.uri (config). By passing ..."
            return true; // Request para a uri  de autenticacao (config)
        }



        //true
        log.debug "before() - BEGIN"

        boolean resultado = true

        // Recupera (se houver) o "subject" autenticado no Shiro
        def subject = SecurityUtils.subject

        if (subject.principal == null || (!subject.authenticated)) {

            log.debug "Usuario ainda nao logou !"

            // Default behaviour is to redirect to the login page.
            // We start by building the target URI from the request's
            // 'forwardURI', which is the URL specified by the
            // browser.
            def targetUri = new StringBuilder(request.forwardURI[request.contextPath.size()..-1])
            def query = request.queryString
            if (query) {
                if (!query.startsWith('?')) {
                    targetUri << '?'
                }
                targetUri << query
            }

            if (redirectUri) {

                log.debug "Redirecionando para a uri: security.shiro.redirect.uri = (${redirectUri})"

                redirect(uri: redirectUri + "?targetUri=${targetUri.encodeAsURL()}")


            } else {

                log.debug "Redirecionando para o controller: auth.login"

                redirect(
                        controller: "auth",
                        action: "login",
                        params: [targetUri: targetUri.toString()])

            }


            resultado = false;
        }



        if(resultado){
            log.debug "Usuario logado : ${subject}"


            boolean isPermitted  = isRequestPermitted(controllerName, actionName, params, subject)


            if (!isPermitted) {

                // Default behaviour is to redirect to the 'unauthorized' page.
                redirect(controller: "auth", action: "unauthorized")
                resultado = false;

            }

        }

        return resultado

        log.debug "before() - returning ${resultado} - END"

    }



    protected Boolean isRequestPermitted(String controllerName, String actionName, params, Subject subject) {

        log.debug "isRequestPermitted - BEGIN"

        def permString = new StringBuilder()
        permString << controllerName << ':' << (actionName ? actionName : "index")

        // Add the ID if it's in the web parameters.
        if (params.id) {
            permString << ':' << params.list('id').join(',')
        }

        boolean isPermitted = subject.isPermitted(permString.toString())

        log.debug "isRequestPermitted - returning ${isPermitted} - END"

        return isPermitted

    }


    boolean after() {


        true
    }

    void afterView() {
        // no-op
    }
}
