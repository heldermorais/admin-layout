<!-- template: /layouts/adminlte/_main-header.gsp -->

<header class="main-header">

    <!-- Logo -->
    <a href="#" class="logo" data-toggle="push-menu">

        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><asset:image src="${g.pageProperty([name: "meta.app-mini-logo"])}" alt="Logo" height="32" width="32" /></span>

        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><asset:image src="${g.pageProperty([name: "meta.app-logo"])}" alt="Logo" height="32" /></span>

    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle visible-xs" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-menu" aria-expanded="true">
                <span class="sr-only">Toggle navigation</span>
                <i class="fa fa-bars" aria-hidden="true"></i>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>




        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
               <g:pageProperty name="page.main-menu-right" />
            </ul>
        </div>


        <div class="collapse navbar-collapse" id="main-menu">
            <ul class="nav navbar-nav" >
                <g:pageProperty name="page.main-menu"/>
            </ul>
        </div>

    </nav>
</header>
