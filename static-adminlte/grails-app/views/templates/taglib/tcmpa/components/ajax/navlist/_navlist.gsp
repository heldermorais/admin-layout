<ul class="nav nav-list">

  <g:each in="${attrs.dataprovider}" status="i" var="item">
      <g:render template="${templatePath}/components/ajax/navlist/navlistItem" model="${[ item: item, templatePath: attrs.templatePath]}" />
  </g:each>
  
</ul>