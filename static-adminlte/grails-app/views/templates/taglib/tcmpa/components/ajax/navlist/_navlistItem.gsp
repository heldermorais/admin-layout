<li class="<g:if test="${active}">active</g:if> ${extraClasses}">
   <a data-ajax="true" 
      data-ajax-url="${url ? url : '#'}"
	  data-ajax-mode="replace">
	  <g:if test="${icon}"><i class="icon-${icon}"></i></g:if> ${label}
   </a>
</li>