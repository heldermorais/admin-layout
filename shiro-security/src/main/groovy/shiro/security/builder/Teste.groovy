package shiro.security.builder

import shiro.security.ShiroApp

class Teste {

    public static void main(String[] args) {

        def nodeBuilder = new NodeBuilder()

        def userlist = nodeBuilder.userlist {
            user(id: '1', firstname: 'John', lastname: 'Smith') {
                address(type: 'home', street: '1 Main St.', city: 'Springfield', state: 'MA', zip: '12345')
                address(type: 'work', street: '2 South St.', city: 'Boston', state: 'MA', zip: '98765')
            }
            user(id: '2', firstname: 'Alice', lastname: 'Doe')
        }




        def builder = new ObjectGraphBuilder()
        builder.classLoader = this.class.classLoader
        builder.classNameResolver = "shiro.security"

        def acme = builder.shiroApp(name: 'ACME') {

            role(name: "ROLE1") {
                permission("outro:*")
            }

        }

        assert acme != null
        assert acme instanceof ShiroApp
        assert acme.name == 'ACME'
        assert acme.roles.size() == 3
        //def employee = acme.employees[0]
        //assert employee instanceof Employee
        //assert employee.name == 'Drone 0'
        //assert employee.address instanceof Address
    }
}
