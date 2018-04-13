<div class="control-group">
  <label class="control-label" <g:if test="${id}">for="${id}"</g:if>>${label}</label>
  <div class="controls">   
    <%= externalBody %>
    <g:if test="${helpText}"><span class="help-block">${helpText}</span></g:if>
  </div>
</div>