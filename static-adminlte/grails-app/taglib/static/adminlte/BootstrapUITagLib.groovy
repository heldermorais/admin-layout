package static.adminlte

import org.springframework.context.MessageSource

import java.text.DateFormatSymbols


class BootstrapUITagLib {

    static namespace = "tcmpa"

    static templatePath = "/templates/taglib/tcmpa"

    static defaultEncodeAs = [taglib:'raw']

    MessageSource messageSource

    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def containerDiv = { attrs, body ->
        //def id           = attrs?.id
        //def extraClasses = attrs?.extraClasses
        //def externalBody = body
        out << render ( template:"${templatePath}/layout/container", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, externalBody: body() ] )
    }


    def rowDiv = { attrs, body ->
        out << render ( template:"${templatePath}/layout/row", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, externalBody: body() ] )
    }


    def div = { attrs, body ->
        out << render ( template:"${templatePath}/layout/div", model: [ id: attrs?.id, span: attrs?.span , offset: attrs?.offset , extraClasses: attrs?.extraClasses, externalBody: body() ] )
    }


    def form = { attrs, body ->
        def i = 0;
        if(attrs?.action){ i++ }
        if(attrs?.controller){ i++ }
        if(attrs?.id){ i++ }
        if(attrs?.fragment){ i++ }
        if(attrs?.mapping){ i++ }
        if(attrs?.params){ i++ }
        if(attrs?.url){ i++ }
        if(attrs?.absolute){ i++ }
        if(attrs?.base){ i++ }
        if(attrs?.name){ i++ }
        if(attrs?.useToken){ i++ }
        if(attrs?.labelAlignment == "left"){
            attrs.class = "${attrs?.class} form-horizontal"
        }
        attrs.class = "${attrs?.class} ${attrs?.extraClasses}";
        attrs.i = i
        def script = "<script type=\"text/javascript\">\$(document).ready(function(){ jQuery('form').preventDoubleSubmit(); });  </script> "

        if (!attrs.doubleSubmit) {
            out.println script
        }
        out << g.form ( attrs, body )
    }

    def remoteform = { attrs, body ->
        def i = 0;
        if(attrs?.action){ i++ }
        if(attrs?.controller){ i++ }
        if(attrs?.id){ i++ }
        if(attrs?.fragment){ i++ }
        if(attrs?.mapping){ i++ }
        if(attrs?.params){ i++ }
        if(attrs?.url){ i++ }
        if(attrs?.absolute){ i++ }
        if(attrs?.base){ i++ }
        if(attrs?.name){ i++ }
        if(attrs?.useToken){ i++ }
        if(attrs?.labelAlignment == "left"){
            attrs.class = "${attrs?.class} form-horizontal"
        }
        attrs.class = "${attrs?.class} ${attrs?.extraClasses}";
        attrs.i = i
        out << g.formRemote ( attrs, body )
    }


    def hiddenField = { attrs, body ->
        out << g.hiddenField ( attrs, body )
    }

    def formItem = { attrs, body ->
        out << render ( template:"${templatePath}/form/formItem", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, label: attrs?.label, helpText: attrs?.helpText, externalBody: body ] )
    }


    def fieldText = { attrs, body ->
        out << render ( template:"${templatePath}/form/fieldText", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, helpText: attrs?.helpText ] )
    }


    def dropdownMenu = { attrs, body ->
        out << render ( template:"${templatePath}/menu/dropdownMenu", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, label: attrs?.label ,ngController: attrs?.ngController, externalBody: body ] )
    }

    def dropdownItem = { attrs, body ->
        out << render ( template:"${templatePath}/menu/dropdownItem", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, label: attrs?.label, onclick: attrs?.onclick ,ngEvent: attrs?.ngEvent, icon: attrs?.icon ] )
    }

    def dropdownDivider = { attrs, body ->
        out << render ( template:"${templatePath}/menu/dropdownDivider", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses] )
    }





    def alertBox = { attrs, body ->
        def closeButton = attrs?.closeButton
        out << render ( template:"${templatePath}/components/alert/alert", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, title: attrs?.title, type: attrs?.type , content: attrs?.text, externalBody: body, closeButton: closeButton])
    }


    def errorBox = { attrs, body ->
        def closeButton = attrs?.closeButton
        out << render ( template:"${templatePath}/components/alert/alert", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, title: attrs?.title, type: "error" , content: attrs?.text, externalBody: body, closeButton: closeButton])
    }

    def infoBox = { attrs, body ->
        def closeButton = attrs?.closeButton
        out << render ( template:"${templatePath}/components/alert/alert", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, title: attrs?.title, type: "info" , content: attrs?.text, externalBody: body, closeButton: closeButton])
    }

    def successBox = { attrs, body ->
        def closeButton = attrs?.closeButton
        out << render ( template:"${templatePath}/components/alert/alert", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, title: attrs?.title, type: "success" , content: attrs?.text, externalBody: body, closeButton: closeButton])
    }



    def breadcrumb = { attrs, body ->
        out << render ( template:"${templatePath}/components/breadcrumb/breadcrumb", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, externalBody: body])
    }

    def breadcrumbItem = { attrs, body ->
        out << render ( template:"${templatePath}/components/breadcrumb/breadcrumbItem", model: [ id: attrs?.id, extraClasses: attrs?.extraClasses, url: attrs?.url, label: attrs?.label, active: attrs?.active])
    }


    def progressBar = { attrs, body ->
        out << render ( template:"${templatePath}/components/progressbar/progressbar", model: [ attrs: attrs])
    }


    def button = { attrs, body ->
        out << render ( template:"${templatePath}/components/button/button", model: [ attrs: attrs])
    }



    def navlist = { attrs, body ->
        out << render ( template:"${templatePath}/components/navlist/navlist", model: [ attrs: attrs , internalBody: body()])
    }

    def navlistItem = { attrs, body ->
        if(attrs?.ajax) {
            out << render ( template:"${templatePath}/components/navlist/navlistItemAjax", model: [ attrs: attrs])
        }
        else {
            out << render ( template:"${templatePath}/components/navlist/navlistItem", model: [ attrs: attrs])
        }
    }

    def navlistHeader = { attrs, body ->
        out << render ( template:"${templatePath}/components/navlist/navlistHeader", model: [ attrs: attrs])
    }



    def datagrid = { attrs, body ->
        println attrs.naoTemArquivo
        // we'll be using this to keep track of our children
        def gridcolumns = []
        this.pageScope.gridcolumns = gridcolumns
        // this will execute the body, so that the children are
        // appended.  obviously, anything other than child
        // tags will not render correctly.
        body()

        attrs.columns = this.pageScope.gridcolumns
        attrs.action  = attrs.action ? attrs.action : "show"


        out << render ( template:"${templatePath}/components/grid/clistview", model: [ attrs: attrs, templatePath: templatePath ])
    }

    def clistview = { attrs, body ->

        // we'll be using this to keep track of our children
        def gridcolumns = []
        this.pageScope.gridcolumns = gridcolumns
        // this will execute the body, so that the children are
        // appended.  obviously, anything other than child
        // tags will not render correctly.
        body()

        attrs.columns = this.pageScope.gridcolumns
        attrs.action  = attrs.action ? attrs.action : "show"

        out << render ( template:"${templatePath}/components/grid/clistview", model: [ attrs: attrs, templatePath: templatePath ])
    }


    def gridcolumn = { attrs, body ->

        attrs.sortable = attrs.sortable ? attrs.sortable : "true"

        if (body) {
            attrs.externalBody = body
        }

        this.pageScope.gridcolumns << attrs
    }


    def select = { attrs ->
        out << render ( template:"${templatePath}/components/select/select", model: [ attrs: attrs])
    }

    Closure datePicker = { attrs ->
        def dateLanguage = attrs.'data-date-language'
        def out = out // let x = x ?
        def xdefault = attrs['default']
        if (xdefault == null) {
            xdefault = new Date()
        }
        else if (xdefault.toString() != 'none') {
            if (xdefault instanceof String) {
                xdefault = DateFormat.getInstance().parse(xdefault)
            }
            else if (!(xdefault instanceof Date)) {
                throwTagError("Tag [datePicker] requires the default date to be a parseable String or a Date")
            }
        }
        else {
            xdefault = null
        }
        def years = attrs.years
        def relativeYears = attrs.relativeYears
        if (years != null && relativeYears != null) {
            throwTagError 'Tag [datePicker] does not allow both the years and relativeYears attributes to be used together.'
        }

        if (relativeYears != null) {
            if (!(relativeYears instanceof IntRange)) {
                // allow for a syntax like relativeYears="[-2..5]". The value there is a List containing an IntRage.
                if ((!(relativeYears instanceof List)) || (relativeYears.size() != 1) || (!(relativeYears[0] instanceof IntRange))){
                    throwTagError 'The [datePicker] relativeYears attribute must be a range of int.'
                }
                relativeYears = relativeYears[0]
            }
        }
        def value = attrs.value
        if (value.toString() == 'none') {
            value = null
        } else if (!value) {
            value = xdefault
        }
        def name = attrs.name
        def id = attrs.id ?: name

        def noSelection = attrs.noSelection
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }

        final PRECISION_RANKINGS = ["year": 0, "month": 10, "day": 20, "hour": 30, "minute": 40]
        def precision = (attrs.precision ? PRECISION_RANKINGS[attrs.precision] :
                (grailsApplication.config.grails.tags.datePicker.default.precision ?
                        PRECISION_RANKINGS["${grailsApplication.config.grails.tags.datePicker.default.precision}"] :
                        PRECISION_RANKINGS["minute"]))

        def day
        def month
        def year
        def hour
        def minute
        def dfs = new DateFormatSymbols(RCU.getLocale(request))

        def c = null
        if (value instanceof Calendar) {
            c = value
        }
        else if (value != null) {
            c = new GregorianCalendar()
            c.setTime(value)
        }

        if (c != null) {
            day = c.get(GregorianCalendar.DAY_OF_MONTH)
            month = c.get(GregorianCalendar.MONTH) + 1		// add one, as Java stores month from 0..11
            year = c.get(GregorianCalendar.YEAR)
            hour = c.get(GregorianCalendar.HOUR_OF_DAY)
            minute = c.get(GregorianCalendar.MINUTE)
        }

        if (years == null) {
            def tempyear
            if (year == null) {
                // If no year, we need to get current year to setup a default range... ugly
                def tempc = new GregorianCalendar()
                tempc.setTime(new Date())
                tempyear = tempc.get(GregorianCalendar.YEAR)
            }
            else {
                tempyear = year
            }
            if (relativeYears) {
                if (relativeYears.reverse) {
                    years = (tempyear + relativeYears.toInt)..(tempyear + relativeYears.fromInt)
                } else {
                    years = (tempyear + relativeYears.fromInt)..(tempyear + relativeYears.toInt)
                }
            } else {
                years = (tempyear - 100)..(tempyear + 100)
            }
        }

        booleanToAttribute(attrs, 'disabled')
        booleanToAttribute(attrs, 'readonly')

        // get the localized format for dates. NOTE: datepicker only uses Lowercase syntax and does not understand hours, seconds, etc. (it uses: dd, d, mm, m, yyyy, yy)
        //def messageSource = ApplicationHolder.application.mainContext.getBean('messageSource')
        String dateFormat = messageSource.getMessage("default.date.datepicker.format",null,null,LocaleContextHolder.locale )
        if (!dateFormat) { // if date.datepicker.format is not used use date.format but remove characters not used by datepicker
            dateFormat = messageSource.getMessage("default.date.format",null,'mm/dd/yyyy',LocaleContextHolder.locale )\
				.replace('z', '').replace('Z', '')\
				.replace('h', '').replace('H', '')\
				.replace('k', '').replace('K', '')\
				.replace('w', '').replace('W', '')\
				.replace('s', '').replace('S', '')\
				.replace('m', '').replace('a', '').replace('D', '').replace('E', '').replace('F', '').replace('G', '').replace(':', '')\
				.replace('MMM', 'MM').replace('ddd', 'dd')\
				.trim()\
				.toLowerCase()
        }
        String formattedDate = g.formatDate(format: dateFormat.replace('m', 'M'), date: c?.getTime())
        out.println "	<input id=\"${id}\" name=\"${name}\" class=\"date\" size=\"16\" type=\"text\" value=\"${formattedDate}\" data-date-format=\"${dateFormat}\"  data-date-language=\"${dateLanguage ? dateLanguage : 'pt-BR'}\" >"
    }

    private void booleanToAttribute(def attrs, String attrName) {
        def attrValue = attrs.remove(attrName)
        // If the value is the same as the name or if it is a boolean value,
        // reintroduce the attribute to the map according to the w3c rules, so it is output later
        if (Boolean.valueOf(attrValue) ||
                (attrValue instanceof String && attrValue?.equalsIgnoreCase(attrName))) {
            attrs.put(attrName, attrName)
        } else if (attrValue instanceof String && !attrValue?.equalsIgnoreCase('false')) {
            // If the value is not the string 'false', then we should just pass it on to
            // keep compatibility with existing code
            attrs.put(attrName, attrValue)
        }
    }

}
