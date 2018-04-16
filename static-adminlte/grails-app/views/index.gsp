<!doctype html>
<html>
<head>

    <title>Welcome to Grails</title>


    <meta name="layout"         content="adminlte"/>
    <meta name="app-mini-logo"  content="grails.svg"/>
    <meta name="app-logo"       content="logo_TCMPA2018.png"/>

</head>
<body>


<content tag="content-header">

    <h1>
        Dashboard
        <small>Version 2.0</small>
    </h1>

    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
    </ol>

</content>


<content tag="sidebar-user-menu">

    <adminLte:sidebarMenuHeader text="User Navigation" />

    <adminLte:sidebarMenu text="My Dashboard" icon="fa fa-dashboard">

        <adminLte:sidebarMenuItem text="Minha Dashboard" icon="fa fa-circle text-success" />

        <adminLte:sidebarMenuItem text="Item com Label" icon="fa fa-circle">
            <adminLte:sidebarMenuItemLabel text="4" />
        </adminLte:sidebarMenuItem>

        <li><a href="#"><i class="fa ga-gear"></i> Dashboard v1</a></li>
        <li><a href="#"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>

    </adminLte:sidebarMenu>

</content>



<content tag="sidebar-user-content">

    <div class="user-panel">
        <div class="pull-left image">
            <asset:image src="adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image"/>
        </div>
        <div class="pull-left info">
            <p>Helder Morais</p>
            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
    </div>


    <!-- search form -->
    <div class="content-fluid">
        <form action="#" method="POST" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="searchForThis" class="form-control" placeholder="Procurar por...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
    </div>
    <!-- /.search form -->

</content>


<content tag="main-menu">
    <%

       def nodeBuilder = new NodeBuilder()
       def ds = nodeBuilder.menu(text: "Menu2", icon: "fa fa-gear", href: "#" )

       def ds2 = [text: "Menu3", icon: "fa fa-trash", href: "#"]

       //def ds3 = {text: "Menu3", icon: "fa fa-trash", href: "#"}

    %>

        <adminLte:navbarMenu text="Meu Menu" href="#" icon="fa fa-gear" datasource="${ds2}" >
            <adminLte:navbarMenuItem href="#" icon="fa fa-gear" text="MenuItem1" disabled="true"/>
            <adminLte:navbarDividerItem />
            <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
        </adminLte:navbarMenu>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
                <li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
                <li><a href="#">App version:
                    <g:meta name="info.app.version"/></a>
                </li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Grails version:
                    <g:meta name="info.app.grailsVersion"/></a>
                </li>
                <li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
                <li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
                <li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
                <li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
                <li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                    <li><a href="#">${plugin.name} - ${plugin.version}</a></li>
                </g:each>
            </ul>
        </li>
</content>






    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
        </div>
    </div>





    <div id="content" role="main">

            <h1>Welcome to Grails</h1>
            <adminLte:tag tagName="h1">
                Welcome to Grails II
            </adminLte:tag>
            <p>
                Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display
                whatever content you may choose. Below is a list of controllers that are currently deployed in
                this application, click on each to execute its default action:
            </p>

            <div id="controllers" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>

    </div>

</body>
</html>
