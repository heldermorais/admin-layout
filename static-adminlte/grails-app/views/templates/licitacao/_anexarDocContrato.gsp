<g:if test="${contratoInstance?.id}">
	<div>
		<tcmpa:form action="save" class="form-horizontal" enctype="multipart/form-data">	
			<div id="anexaDoc" class="control-group fieldcontain ${hasErrors(bean: contratoArquivoInstance, field: 'tipoDocumento.id', 'error')}">
					<label for="contatado.id" class="control-label">
					<g:message code="contratoArquivo.label" default="Documento: " /><span class="required-indicator">*</span></label>
					
				   <div  id="anexoDocDiv" class="controls">
						<g:select 
						   id="tipoDocumento.id" 
	            		   name="tipoDocumento.id" 
						   value="${contratoArquivoInstance?.tipoDocumento?.id}"
						   from="${listDocObrigratorioPendente}"
						   optionKey="id" 
						   optionValue="nome"
						   class="inline"
						   required="true"
						   noSelection="${['':'Selecione o Documento...']}"						   
				   		/>		
					</div>
			</div>	
			
			<div class="control-group fieldcontain ${hasErrors(bean: contratoArquivoInstance, field: 'nome', 'error')} required">
				<label for="nome" class="control-label"><g:message code="contratoArquivoInstance.pdfArquivo.label" default="Arquivo" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<div id="FileUpload">
    					<input type="file" size="24" id="BrowserHidden" name="BrowserHidden" onchange="getElementById('FileField').value = getElementById('BrowserHidden').value;" />
    					<div id="BrowserVisible"><input type="text" id="FileField" name="FileField" /></div>
					</div>
					<span class="help-inline">${hasErrors(bean: contratoArquivoInstance, field: 'pdfArquivo', 'error')}</span>
					<tcmpa:dica chave="contratoArquivoInstance.pdfArquivo"/>
				</div>
			</div>
		</tcmpa:form>   
	</div>
</g:if>