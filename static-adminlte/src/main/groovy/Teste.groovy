import groovy.xml.MarkupBuilder

class Teste {

    static void main(String[] args){
        def writer = new StringWriter()
        def html = new MarkupBuilder(writer)

        html.html {
            head {
                title: "Creating html document with groovy"
                script: "alert('hello');"
            }
            body(id: "main") {
                h2 id: "book-mark",  "Tutorial on how to generate html with groovy"
                p {
                    mkp.yield "Mixing text with"
                    strong "strong"
                    mkp.yield " elements."
                }
                a href: "http://www.leveluplunch.com/java/tutorials/", "Java tutorials"
            }
        }

        println writer


        def builder = new NodeBuilder()

        def studentlist = builder.userlist(text: "texto", href: "#", icon:"fa-gear") {
            user(id: '1', studentname: 'John', Subject: 'Chemistry')
            user(id: '2', studentname: 'Joe', Subject: 'Maths')
            user(id: '3', studentname: 'Mark', Subject: 'Physics')
        }


        Map attributes = studentlist.attributes()

        println(studentlist)

        println(attributes)


        String home = System.getProperty("user.home");
        System.out.println("User Home Directory: " + home);


    }
}
