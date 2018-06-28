<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Controller</title>
</head>
<body>


<content tag="nav">
<li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
    <div class="dropdown-menu">
      <a class="dropdown-item" href="#">Action</a>
      <a class="dropdown-item" href="#">Another action</a>
      <a class="dropdown-item" href="#">Something else here</a>
      <div class="dropdown-divider"></div>
      <a class="dropdown-item" href="#">Separated link</a>
    </div>
  </li>

        <li class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
            <div class="dropdown-menu">

                <a class="dropdown-item" href="#">Environment: ${grails.util.Environment.current.name}</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">App profile: ${grailsApplication.config.grails?.profile}</a>
                <a class="dropdown-item" href="#">App version:
                    <g:meta name="info.app.version"/>
                </a>

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Grails version:
                    <g:meta name="info.app.grailsVersion"/>
                </a>

                <a class="dropdown-item" href="#">Groovy version: ${GroovySystem.getVersion()}</a>
                <a class="dropdown-item" href="#">JVM version: ${System.getProperty('java.version')}</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a>
            </div>
        </li>
</content>

<div class="content">
  <h1>Home Controller !!!!</h1>
  <hr/>
  <p>Action: ${actionName}</p>
  <p>${actionDescription}</p>
  <ul>
  <g:each var="breadcrumb" in="${breadcrumbs}">
     <li><g:link controller="${breadcrumb.controllerName}" action="${breadcrumb.actionName}" >${breadcrumb.label}</g:link></li>
  </g:each>
  </ul>


  <boot:card id="meu">
    <h7> isto esta no body() </h7>
    <p class="card-text">Isto é somente um texto</p>
  </boot:card>


    <div class="alert">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <strong>Title!</strong> Alert body ...
    </div>

</div>

</body>