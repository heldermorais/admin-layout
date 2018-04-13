package static.adminlte

class AdminLteTagLib {

    static namespace = "adminLte"
    static defaultEncodeAs = [taglib:'raw']

    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def navbarMenu = {attrs, body ->
        out << g.render( template: "/templates/taglib/adminLte/navbar/navbarMenu" , model: [attrs: attrs, tbody: body()])
    }

    def navbarMenuItem = { attrs, body ->
        attrs?.disabled = attrs?.disabled != null ? "disabled" : ""
        out << g.render( template: "/templates/taglib/adminLte/navbar/navbarMenuItem" , model: [attrs: attrs, tbody: body()])
    }

    def navbarDividerItem = { attrs, body ->
        out << "<li role=\"separator\" class=\"divider\"></li>"
    }

}
