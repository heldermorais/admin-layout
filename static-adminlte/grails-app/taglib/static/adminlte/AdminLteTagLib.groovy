package static.adminlte


import org.apache.commons.beanutils.BeanUtils




class AdminLteTagLib {

    static namespace = "adminLte"
    static defaultEncodeAs = [taglib:'raw']


    static templatePath = "/templates/taglib/adminLte"



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

        out << g.render( template: "${templatePath}/navbar/navbarMenu" , model: [attrs: attrs, tbody: body()])

    }

    def navbarMenuItem = { attrs, body ->
        attrs?.disabled = attrs?.disabled != null ? "disabled" : ""
        out << g.render( template: "${templatePath}/navbar/navbarMenuItem" , model: [attrs: attrs, tbody: body()])
    }

    def navbarDividerItem = { attrs ->
        out << g.render( template: "${templatePath}/navbar/navbarMenuDivider" , model: [attrs: attrs])
    }

}
