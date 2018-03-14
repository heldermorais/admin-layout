package common.backend.breadcrumbs.plugins

import common.backend.bean.ProxiedBean
import common.backend.breadcrumbs.MenuItem



@ProxiedBean
class MenuDefinitionService  {

	static scope = 'session'

    def loadMenuDefinition() {
		def menus = []

		menus << new MenuItem(name : "page-one", message:"page.one.demo", controller: "breadcrumbsDemo", action: "pageOne")
		menus << new MenuItem(name : "page-two", message:"page.two.demo", controller: "breadcrumbsDemo", action: "pageTwo")
		MenuItem menuThree = new MenuItem(name : "page-three", message:"page.three.demo", controller: "breadcrumbsDemo", action : "pageThree" )
		menuThree <<  new MenuItem(name : "page-three-one", message:"page.three.one.demo", controller: "breadcrumbsDemo", action:"pageThreeOne")
		MenuItem menuThreeTwo = new MenuItem(name : "page-three-two", message:"page.three.two.demo", controller: "breadcrumbsDemo", action:"pageThreeTwo")
		menuThreeTwo << new MenuItem(name : "page-three-two", message:"page.three.two.one.demo", controller: "breadcrumbsDemo", action:"pageThreeTwoOne")
		menuThreeTwo << new MenuItem(name : "page-three-two", message:"page.three.two.two.demo", controller: "breadcrumbsDemo", action:"pageThreeTwoTwo")
		menuThree << menuThreeTwo

		menus << menuThree
		MenuItem menuCreateSomething = new MenuItem(name : "page-something", message:"page.something.demo")
		menuCreateSomething <<  new MenuItem(name : "page-createsomething", message:"page.create.something.demo", controller: "breadcrumbsDemo", action:"createsomething")
		menus << menuCreateSomething
		menus
    }
	
	//@Caching()
	def getHomeItem(){
		def message = "breadcrumbs/menu_home.png"
		def home  = new MenuItem(name:"page-home", message: message);
		home
	}
}
