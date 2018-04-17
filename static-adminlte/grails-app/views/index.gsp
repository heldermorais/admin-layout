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
       def ds = nodeBuilder.
                menu (text: "Menu2", icon: "fa fa-gear", href: "#" ){

                }

       def ds2 = [text: "Menu3", icon: "fa fa-trash", href: "#"]



    %>

        <adminLte:navbarMenu datasource="${ds}" >

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


<content tag="main-menu-right">

    <!-- Messages: style can be found in dropdown.less-->
    <li class="dropdown messages-menu">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-envelope-o"></i>
            <span class="label label-success">4</span>
        </a>
        <ul class="dropdown-menu">
            <li class="header">You have 4 messages</li>
            <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                    <li><!-- start message -->
                        <a href="#">
                            <div class="pull-left">
                                <asset:image src="adminlte/img/user2-160x160.jpg" class="img-circle"
                                             alt="User Image" />
                            </div>
                            <h4>
                                Support Team
                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                            </h4>
                            <p>Why not buy a new awesome theme?</p>
                        </a>
                    </li>
                    <!-- end message -->
                    <li>
                        <a href="#">
                            <div class="pull-left">
                                <asset:image src="adminlte/img/user3-128x128.jpg" class="img-circle"
                                             alt="User Image" />
                            </div>
                            <h4>
                                AdminLTE Design Team
                                <small><i class="fa fa-clock-o"></i> 2 hours</small>
                            </h4>
                            <p>Why not buy a new awesome theme?</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="pull-left">
                                <asset:image src="adminlte/img/user4-128x128.jpg" class="img-circle"
                                             alt="User Image" />
                            </div>
                            <h4>
                                Developers
                                <small><i class="fa fa-clock-o"></i> Today</small>
                            </h4>
                            <p>Why not buy a new awesome theme?</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="pull-left">
                                <asset:image src="adminlte/img/user3-128x128.jpg" class="img-circle"
                                             alt="User Image" />
                            </div>
                            <h4>
                                Sales Department
                                <small><i class="fa fa-clock-o"></i> Yesterday</small>
                            </h4>
                            <p>Why not buy a new awesome theme?</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="pull-left">
                                <asset:image src="adminlte/img/user4-128x128.jpg" class="img-circle"
                                             alt="User Image" />
                            </div>
                            <h4>
                                Reviewers
                                <small><i class="fa fa-clock-o"></i> 2 days</small>
                            </h4>
                            <p>Why not buy a new awesome theme?</p>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="footer"><a href="#">See All Messages</a></li>
        </ul>
    </li>
    <!-- Notifications: style can be found in dropdown.less -->
    <li class="dropdown notifications-menu">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-bell-o"></i>
            <span class="label label-warning">10</span>
        </a>
        <ul class="dropdown-menu">
            <li class="header">You have 10 notifications</li>
            <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                    <li>
                        <a href="#">
                            <i class="fa fa-users text-aqua"></i> 5 new members joined today
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-warning text-yellow"></i> Very long description here that
                            may not fit into the
                            page and may cause design problems
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-users text-red"></i> 5 new members joined
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-user text-red"></i> You changed your username
                        </a>
                    </li>
                </ul>
            </li>
            <li class="footer"><a href="#">View all</a></li>
        </ul>
    </li>
    <!-- Tasks: style can be found in dropdown.less -->
    <li class="dropdown tasks-menu">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-flag-o"></i>
            <span class="label label-danger">9</span>
        </a>
        <ul class="dropdown-menu">
            <li class="header">You have 9 tasks</li>
            <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                    <li><!-- Task item -->
                        <a href="#">
                            <h3>
                                Design some buttons
                                <small class="pull-right">20%</small>
                            </h3>
                            <div class="progress xs">
                                <div class="progress-bar progress-bar-aqua" style="width: 20%"
                                     role="progressbar"
                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">20% Complete</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- end task item -->
                    <li><!-- Task item -->
                        <a href="#">
                            <h3>
                                Create a nice theme
                                <small class="pull-right">40%</small>
                            </h3>
                            <div class="progress xs">
                                <div class="progress-bar progress-bar-green" style="width: 40%"
                                     role="progressbar"
                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">40% Complete</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- end task item -->
                    <li><!-- Task item -->
                        <a href="#">
                            <h3>
                                Some task I need to do
                                <small class="pull-right">60%</small>
                            </h3>
                            <div class="progress xs">
                                <div class="progress-bar progress-bar-red" style="width: 60%"
                                     role="progressbar"
                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">60% Complete</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- end task item -->
                    <li><!-- Task item -->
                        <a href="#">
                            <h3>
                                Make beautiful transitions
                                <small class="pull-right">80%</small>
                            </h3>
                            <div class="progress xs">
                                <div class="progress-bar progress-bar-yellow" style="width: 80%"
                                     role="progressbar"
                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">80% Complete</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- end task item -->
                </ul>
            </li>
            <li class="footer">
                <a href="#">View all tasks</a>
            </li>
        </ul>
    </li>
    <!-- User Account: style can be found in dropdown.less -->
    <li class="dropdown user user-menu">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <asset:image src="adminlte/img/user2-160x160.jpg" class="user-image" alt="User Image" />
            <!--<span class="hidden-xs">Alexander Pierce</span>-->
        </a>
        <ul class="dropdown-menu">
            <!-- User image -->
            <li class="user-header">
                <asset:image src="adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image" />

                <p>
                    Alexander Pierce - Web Developer
                    <small>Member since Nov. 2012</small>
                </p>
            </li>
            <!-- Menu Body -->
            <li class="user-body">
                <div class="row">
                    <div class="col-xs-4 text-center">
                        <a href="#">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                        <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                        <a href="#">Friends</a>
                    </div>
                </div>
                <!-- /.row -->
            </li>
            <!-- Menu Footer-->
            <li class="user-footer">
                <div class="pull-left">
                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                    <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
            </li>
        </ul>
    </li>

</content>


<div class="content-fluid">

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

</div>

</body>
</html>
