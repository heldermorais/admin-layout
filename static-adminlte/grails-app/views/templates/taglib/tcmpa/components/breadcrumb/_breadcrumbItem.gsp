<g:if test="${(!active) || (active =='false')}">
  <li><a <g:if test="${url}">href="${url}"</g:if><g:else>href="#"</g:else>>${label}</a> <span class="divider">/</span></li>
</g:if>  
<g:if test="${(active == 'true')}">  
  <li class="active">${label}</li>
</g:if>