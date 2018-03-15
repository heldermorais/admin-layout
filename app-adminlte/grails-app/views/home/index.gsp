<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Controller</title>
</head>
<body>
  <h1>Home Controller !</h1>
  <hr/>
  <p>Action: ${actionName}</p>
  <p>${actionDescription}</p>
  <ul>
  <g:each var="breadcrumb" in="${breadcrumbs}">
     <li><g:link controller="${breadcrumb.controllerName}" action="${breadcrumb.actionName}" >${breadcrumb.label}</g:link></li>
  </g:each>
  </ul>
</body>