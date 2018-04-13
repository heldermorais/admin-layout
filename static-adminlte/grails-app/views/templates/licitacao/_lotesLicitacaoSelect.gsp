<div>

			<div id="contratado" class="control-group fieldcontain ${hasErrors(bean: contratoInstance, field: 'contratado.id', 'error')} required">
					<label for="participante.id" class="control-label">
					<g:message code="contrato.tipoAditivo.label" default="Contratado: " /><span class="required-indicator">*</span></label>
					
				   <div  id="statusDiv" class="controls">
						<g:select 
						   id="contratado.id" 
	            		   name="contratado.id" 
						   value="${contratoInstance?.contratado?.id}"
						   from="${participanteList}"
						   optionKey="id" 
						   optionValue="nomeRazaoSocial"
						   class="inline"
						   required="true"
						   onchange="${remoteFunction(controller:'contrato',action:'verificaContratado',update:'loteLicitacaoDiv',params:'\'participante.id=\' + escape(this.value)')}"
				   		/>		
					</div>
			</div>			

	<g:if test="${!(vencedor.toBoolean())}">
		<!-- Justificativa do Recontrato -->
					
		<div class="control-group fieldcontain ${hasErrors(bean: contrato, field: 'contratoInstance.justificativaRecontrato', 'error')} ">
			<label for="contratoInstance.justificativaRecontrato" class="control-label">
			<g:message code="contratoInstance.justificativaRecontrato.label" default="Justificativa do Recontrato" /><span class="required-indicator">*</span></label>
			<div class="controls">
				<g:textArea name="justificativaRecontrato" value="${contratoInstance?.justificativaRecontrato}" required="true"/>
				<span class="help-inline">${hasErrors(bean: contrato, field: 'contratoInstance.justificativaRecontrato', 'error')}</span>
<%--				<tcmpa:dica chave="contratoInstance.justificativaRecontrato"/>--%>
			</div>
		</div>		
    </g:if>	
   
</div>
