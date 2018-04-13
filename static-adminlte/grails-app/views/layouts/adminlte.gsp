<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>
        <g:layoutTitle default="AdminLTE Grails3"/>
    </title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <asset:stylesheet src="application.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">


    <g:layoutHead/>

</head>
<body class="hold-transition skin-blue-light sidebar-mini">
<div class="wrapper">

    <g:render template="/layouts/adminlte/main-header" />

    <g:render template="/layouts/adminlte/main-sidebar" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <g:pageProperty name="page.content-header"/>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">
            <g:layoutBody/>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <g:render template="/layouts/adminlte/main-sidebar" />


</div>
<!-- ./wrapper -->

<asset:javascript src="application.js"/>

</body>
</html>