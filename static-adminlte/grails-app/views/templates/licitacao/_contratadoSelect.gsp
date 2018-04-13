<div>

			<div id="contratado" class="control-group fieldcontain ${hasErrors(bean: contratoInstance, field: 'contratado.id', 'error')} required">
					<label for="contatado.id" class="control-label">
					<g:message code="contrato.contratado.label" default="Contratado: " /><span class="required-indicator">*</span></label>
					
				   <div  id="contratadoDiv" class="controls">
						<g:select disabled="${contratoInstance?.contrato != null}"
						   id="contratado.id" 
	            		   name="contratado.id" 
						   value="${contratoInstance?.contratado?.id}"
						   from="${participanteList}"
						   optionKey="id" 
						   optionValue="nomeRazaoSocial"
						   class="inline"
						   required="true"
						   noSelection="${['':'Selecione o Contratado...']}"						   
				   		/>		
					</div>
			</div>			

   
</div>
