<div <g:if test="${id}">id="${id}"</g:if> class="alert alert-${type} alert-block ${extraClasses}">
  <g:if test="${(!closeButton) || (closeButton == 'true')}"><button type="button" class="close" data-dismiss="alert">&times;</button></g:if>
  <g:if test="${title}"><h4>${title}</h4></g:if>
  ${content}
  <%= externalBody %>
</div>