package static.adminlte

import org.apache.commons.beanutils.BeanUtils



class Bootstrap337TagLib {

    static namespace = "bs"
    static defaultEncodeAs = [taglib:'raw']


    static templatePath = "/templates/taglib/bootstrap337"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]


    def tag = {attrs, body ->

        if(attrs?.data != null){

            log.debug "Tem Datasource ! ${attrs.data}"

            def _data = null

            if(attrs?.data instanceof Node) {
                _data = attrs?.data.attributes()
            }else{
                _data = attrs?.data
            }

            //attrs.text = BeanUtils.getProperty(_data, "text")
            //attrs.href = BeanUtils.getProperty(_data, "href")
            //attrs.icon = BeanUtils.getProperty(_data, "icon")

        }

        //attrs.cssClass = "container ${attrs.cssClass}"
        //attrs.tagBody = body()

        String tagName = attrs.tagName
        attrs.remove('tagName')

        String tagTemplate = attrs.template ? attrs.template : "${templatePath}/tag"
        out << g.render( template: tagTemplate , model: [ tagName: tagName, attrs: attrs , tagBody: body()])

    }


    def div = { attrs, body ->
        attrs.tagName = 'div'
        out << tag (attrs,body)
    }

    def divContainer = { attrs, body ->
        attrs.tagName = 'div'
        attrs['class'] = "${attrs?.class} container"
        out << tag (attrs,body)
    }
}
