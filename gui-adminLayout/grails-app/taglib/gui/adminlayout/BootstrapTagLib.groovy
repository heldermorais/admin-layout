package gui.adminlayout

class BootstrapTagLib {

    static namespace ="boot"

    static defaultEncodeAs = [taglib:'raw']

    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def card = { attrs, body ->
        /*out << "<div class=\"mydiv\">"
        out << "Title : ${attrs?.title}"
        out << "</div>"*/
        out << g.render (template:"/templates/taglib/bootstrap/div", model:[attrs: attrs, tbody: body()])
    }


}
