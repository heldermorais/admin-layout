<g:if test="${it?.sortable == 'true'}">
<g:if test="${it?.params}">
<g:sortableColumn property="${it.property}"  title="${it.headerText}" params="${it.params}" />
</g:if>
<g:else>
<g:sortableColumn property="${it.property}"  title="${it.headerText}"  />
</g:else>
</g:if>
<g:else>
<th>${it.headerText}</th>
</g:else>