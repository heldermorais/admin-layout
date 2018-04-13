<li class="controller">
  <a class="dropdown-toggle ${extraClasses}" data-toggle="dropdown" href="#" <g:if test="${(onclick) && !(ngEvent)}">data-ng-click="${onclick}" </g:if><g:if test="${ngEvent}"> data-ng-click='dispatchEvent("${ngEvent}")'</g:if> ><g:if test="${icon}"><i class="${icon}"></i> </g:if>${label}</a>
</li>               
