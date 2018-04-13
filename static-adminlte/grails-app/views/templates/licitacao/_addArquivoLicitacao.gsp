<div>
				    <input type="hidden" value="${licitacaoInstance?.id}" name="licitacao.id" xmlns="http://www.w3.org/1999/html"/>
					<input type="hidden" value="${atoAdmJudicialInstance?.id}" name="id" />		

					<g:if test="${listDocObrigratorioPendente}">				
						<fieldset class="form">
							<div id="anexaDoc" class="control-group fieldcontain ${hasErrors(bean: arquivoLicitacao, field: 'tipoDoc.id', 'error')}">
									<label for="atoAdmJudicialInstance.id" class="control-label">
									<g:message code="arquivoLicitacao.label" default="Documento: " /><span class="required-indicator">*</span></label>
									
								   <div  id="anexoDocDiv" class="controls">
										<g:select 
										   id="tipoDoc.id" 
				            			   name="tipoDoc.id" 
										   value="${arquivoLicitacao?.tipoDoc?.id}"
										   from="${listDocObrigratorioPendente}"
										   optionKey="id" 
										   optionValue="nome"
										   class="inline"
										   required="true"
										   noSelection="${['':'Selecione o Documento...']}"						   
								   		/>		
									</div>
							</div>	
						
							<div class="control-group fieldcontain ${hasErrors(bean: arquivoLicitacao, field: 'arquivo', 'error')} required">
								<label for="arquivo" class="control-label"><g:message code="arquivoLicitacao.arquivo.label" default="Arquivo" /><span class="required-indicator">*</span></label>
								<div class="controls">
									<div id="FileUpload">
			    						<input type="file" size="24" id="BrowserHidden" name="BrowserHidden" onchange="getElementById('FileField').value = getElementById('BrowserHidden').value;" />
			    						<div id="BrowserVisible"><input type="text" id="FileField" name="FileField" /></div>
									</div>
									<span class="help-inline">${hasErrors(bean: arquivoLicitacao, field: 'arquivo', 'error')}</span>
									<tcmpa:dica chave="arquivoLicitacao.arquivo"/>
								</div>
							</div>		
							
							<div class="control-group fieldcontain ${hasErrors(bean: arquivoLicitacao, field: 'nome', 'error')} required">
								<label for="nome" class="control-label"><g:message code="arquivoLicitacao.nome.label" default="Nome" /><span class="required-indicator">*</span></label>
								<div class="controls">
									<g:textField name="nome" maxlength="150" required="" value="${arquivoLicitacao?.nome}"/>
									<span class="help-inline">${hasErrors(bean: arquivoLicitacao, field: 'nome', 'error')}</span>
									<tcmpa:dica chave="arquivo.nome"/>
								</div>
							</div>
															
						</fieldset>
					</g:if>

</div>
