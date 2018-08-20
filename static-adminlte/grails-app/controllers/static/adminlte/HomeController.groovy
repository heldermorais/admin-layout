package static.adminlte

import org.springframework.core.env.MapPropertySource
import org.springframework.core.env.MutablePropertySources
import org.springframework.core.env.PropertySourcesPropertyResolver



class HomeController {

    def index() {

        MutablePropertySources propSources = new MutablePropertySources()

        HashMap<String,String> mapProps    = new HashMap<String,String>()
        mapProps.put("key1", "Ops! Achei")

        MapPropertySource propSource1      = new MapPropertySource("props01", mapProps)

        propSources.addFirst(propSource1)


        PropertySourcesPropertyResolver resolver = new PropertySourcesPropertyResolver(propSources)

        String value1 = resolver.getProperty("key1")



        println "Key1: ${value1}"

    }

}
