<div>
		<g:if test="${vencedor}">
			<!-- Justificativa do Recontrato -->
			<div id="ttt" class="control-group fieldcontain ${hasErrors(bean: contrato, field: 'contrato.justificativaRecontrato', 'error')} ">
				<label for="contrato.justificativaRecontrato" class="control-label"><g:message code="contrato.justificativaRecontrato.label" default="Justificativa do Recontrato" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:textArea name="contrato.justificativaRecontrato" value="${contrato?.justificativaRecontrato}" required="true"/>
					<span class="help-inline">${hasErrors(bean: contrato, field: 'contrato.justificativaRecontrato', 'error')}</span>
					<tcmpa:dica chave="contrato.justificativaRecontrato"/>
				</div>
			</div>		
		
		</g:if>	
</div>
