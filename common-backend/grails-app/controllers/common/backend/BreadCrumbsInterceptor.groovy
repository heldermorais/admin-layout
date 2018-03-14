package common.backend

import common.backend.breadcrumbs.BreadCrumbsService
import common.backend.breadcrumbs.annotation.BreadCrumbs
import common.backend.breadcrumbs.exception.BreadCrumbsException
import common.backend.breadcrumbs.plugins.MenuDefinitionService
import grails.core.GrailsApplication
import org.apache.commons.lang3.StringUtils
import org.springframework.context.MessageSource

import java.lang.reflect.Method


class BreadCrumbsInterceptor {


    int order = HIGHEST_PRECEDENCE + 110


    /** i18n service */
    MessageSource messageSource

    /** Service as proxy {@link BreadCrumbsService} */
    BreadCrumbsService breadCrumbsService

    /** The global grails application*/
    GrailsApplication grailsApplication

    /** Menu definition service provide by application */
    MenuDefinitionService menuDefinitionService


    BreadCrumbsInterceptor(){
        matchAll()
                .excludes(controller: 'login')
                .excludes(controller: 'auth')
                .excludes(uri: '/static/**')
    }

    boolean before() {

        //Exclusion des requete Ajax
        if(!request.xhr){
            log.debug "BreadCrumbsInterceptor - before() - begin"
            //Build the breadcrumbs
            buildBreadCrumbs(controllerName, actionName, params, session)
            //Retrieve if home link must be display
            if(grailsApplication.config.breadcrumbs.enable.home && session["breadcrumbs"].homeitem == null){
                def homeItem = menuDefinitionService.getHomeItem();
                session["breadcrumbs"].home = homeItem
            }
            log.debug "BreadCrumbsInterceptor - before() - end"
        }

        true
    }

    boolean after() {

        if(!request.xhr){

            log.debug "BreadCrumbsInterceptor - after() - begin"

            BreadCrumbs bm
            /* Retrieve whether target method is annoted */
            if(controllerName != null && actionName != null){
                Class clazz = breadCrumbsService.retrievesArtifact("Controller", controllerName).clazz
                Method m
                try{
                    m = clazz.getMethod(actionName, null)
                }catch(NoSuchMethodException e){/* No method !!! Dynamic Scaffolding ... ??? */}

                if(m != null && m.isAnnotationPresent(BreadCrumbs)){
                    bm = m.getAnnotation(BreadCrumbs)
                    if(breadCrumbsService.validate(bm)){
                        //delete breadcrumbs
                        session["breadcrumbs"].path = null

                        String ctrl = controllerName
                        String act = actionName

                        /* find params from scope */
                        def parameters = breadCrumbsService.findOverrideParams(bm)

                        if(parameters["actionName"]){
                            act = parameters["actionName"]
                        }

                        if(parameters["controllerName"]){
                            ctrl = parameters["controllerName"]
                        }

                        buildBreadCrumbs(ctrl, act, params, session)
                    }
                }
            }
            log.debug "BreadCrumbsInterceptor - before() - end"
        }

        true
    }

    void afterView() {
        // no-op
    }


    /**
     * Build breadcrumbs
     * @param ctrl controller
     * @param act action
     * @param prs params
     * @param sess session
     */
    def buildBreadCrumbs(ctrl, act, prs, sess){

        ctrl = StringUtils.lowerCase(ctrl)
        act = StringUtils.lowerCase(act)

        if(sess["breadcrumbs"] == null){
            sess["breadcrumbs"] = [:]
        }

        if(sess["breadcrumbs"].path == null){
            def path = []
            def divider = ""
            try {
                /* Application service */
                def menus = menuDefinitionService.loadMenuDefinition()
                path = breadCrumbsService.retrievesItemMenu(menus, act, ctrl, prs)
            }catch(Exception e){
                /* no service is provided by the application */
                throw new BreadCrumbsException("No MenuDefinitionService is provided or not implement loadMenuDefinition method")
            }

//			//Home Page
//			if(path == null || path.size() == 0 ){
//				path << messageSource.getMessage('breadcrumbs.home.label', null, LCH.getLocale())
//			}

            sess["breadcrumbs"].path = path
        }
    }
}
