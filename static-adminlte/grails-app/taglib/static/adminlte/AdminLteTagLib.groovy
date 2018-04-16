package static.adminlte


import org.apache.commons.beanutils.BeanUtils




class AdminLteTagLib {

    static namespace = "adminLte"
    static defaultEncodeAs = [taglib:'raw']


    static templatePath = "/templates/taglib/adminLte"



    def tag = { attrs, body ->

        if(attrs?.datasource != null){

            //println "Tem Datasource ! ${attrs.datasource}"

            def _datasource = null

            if(attrs?.datasource instanceof Node) {
                _datasource = attrs?.datasource.attributes()
            }else{
                _datasource = attrs?.datasource
            }

            //attrs.text = BeanUtils.getProperty(_datasource, "text")
            //attrs.href = BeanUtils.getProperty(_datasource, "href")
            //attrs.icon = BeanUtils.getProperty(_datasource, "icon")

        }

        // Infere qual o nome da TAG
        String tagName = ((attrs?.tagName != null)&&(!attrs?.tagName.isEmpty())) ? attrs.tagName : "div"
        attrs.remove('tagName')

        // Infere qual o TEMPLATE da TAG
        String template = attrs?.template ? attrs?.template : "${templatePath}/tag"

        out << g.render( template: template , model: [tagName: tagName, attrs: attrs, tagBody: body()])

    }




    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def navbarMenu = {attrs, body ->

        if(attrs?.datasource != null){

            println "Tem Datasource ! ${attrs.datasource}"

            def _datasource = null

            if(attrs?.datasource instanceof Node) {
                _datasource = attrs?.datasource.attributes()
            }else{
                _datasource = attrs?.datasource
            }

            attrs.text = BeanUtils.getProperty(_datasource, "text")
            attrs.href = BeanUtils.getProperty(_datasource, "href")
            attrs.icon = BeanUtils.getProperty(_datasource, "icon")

        }

        String defaultTemplate = "${templatePath}/navbar/navbarMenu"
        if(attrs?.template == null) {
            attrs.template = defaultTemplate
        }

        out << tag (attrs,body())
        //out << g.render( template: "${templatePath}/navbar/navbarMenu" , model: [attrs: attrs, tbody: body()])

    }

    def navbarMenuItem = { attrs, body ->
        attrs?.disabled = attrs?.disabled != null ? "disabled" : ""

        String defaultTemplate = "${templatePath}/navbar/navbarMenuItem"
        if(attrs?.template == null) {
            attrs.template = defaultTemplate
        }

        out << tag (attrs,body())

        //out << g.render( template: "${templatePath}/navbar/navbarMenuItem" , model: [attrs: attrs, tbody: body()])
    }

    def navbarDividerItem = { attrs, body ->

        String defaultTemplate = "${templatePath}/navbar/navbarMenuDivider"
        if(attrs?.template == null) {
            attrs.template = defaultTemplate
        }

        out << tag (attrs,body())

        //out << g.render( template: "${templatePath}/navbar/navbarMenuDivider" , model: [attrs: attrs])
    }



    def sidebarMenu = { attrs, body ->

        String defaultTemplate = "${templatePath}/main-sidebar/sidebar-submenu"
        if(attrs?.template == null) {
            attrs.template = defaultTemplate
        }

        out << tag (attrs,body())

        //out << g.render( template: "${templatePath}/navbar/navbarMenuDivider" , model: [attrs: attrs])
    }

    def sidebarMenuHeader = { attrs, body ->

        String defaultTemplate = "${templatePath}/main-sidebar/sidebar-submenu-header"
        if(attrs?.template == null) {
            attrs.template = defaultTemplate
        }

        out << tag (attrs,body())

        //out << g.render( template: "${templatePath}/navbar/navbarMenuDivider" , model: [attrs: attrs])
    }

    def sidebarMenuItem = { attrs, body ->

        String defaultTemplate = "${templatePath}/main-sidebar/sidebar-submenu-item"
        if(attrs?.template == null) {
            attrs.template = defaultTemplate
        }

        out << tag (attrs,body())

        //out << g.render( template: "${templatePath}/navbar/navbarMenuDivider" , model: [attrs: attrs])
    }

    def sidebarMenuItemLabel = { attrs, body ->

        String defaultTemplate = "${templatePath}/main-sidebar/sidebar-submenu-itemLabel"
        if(attrs?.template == null) {
            attrs.template = defaultTemplate
        }

        out << tag (attrs,body())

        //out << g.render( template: "${templatePath}/navbar/navbarMenuDivider" , model: [attrs: attrs])
    }
}
