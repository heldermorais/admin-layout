<div>
	<tcmpa:rowDiv>
				<section id="visulaiza-contrato" class="first">
				<h1>Visualizar Informações do Contrato</h1>
			
				<ul id="Menu" class="nav nav-pills">
			
					<g:set var="entityName" value="${message(code: params.controller+'.label', default: params.controller.substring(0,1).toUpperCase() + params.controller.substring(1).toLowerCase())}" />
					
				</ul>
				<g:hasErrors bean="${contratoInstance}">
					<div class="alert alert-error">
						<g:renderErrors bean="${contratoInstance}" as="list" />
					</div>
				</g:hasErrors>			
				<table class="table">
					<tbody>
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.tipoDocumento.label" default="Tipo Documento" /></td>
							<td valign="top" class="value">${contratoInstance.tipoDocumento.nome}</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.numero.label" default="Número" /></td>
							<td valign="top" class="value">${fieldValue(bean: contratoInstance, field: "numero")}</td>
						</tr>
						
						<g:if test="${contratoInstance?.contrato != null}">	
							<tr class="prop">
								<td valign="top" style="color:green" class="name"><g:message code="contrato.tipoAditivo.label" default="Tipo Aditivo" /></td>
								<td valign="top" style="color:green" class="value">${contratoInstance?.tipoAditivo?.nome}</td>
							</tr>
							<tr class="prop">
								<td valign="top" style="color:green" class="name"><g:message code="contrato.contrato.label" default="Contrato Aditivado" /></td>
								<td valign="top" style="color:green" class="value"><g:link action="show" params="['id': contratoInstance?.contrato?.id]">${contratoInstance?.contrato?.numero}</g:link></td>
							</tr>							
						</g:if>						
					
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.dataInicioVigencia.label" default="Início da Vigência" /></td>
							<td valign="top" class="value"><g:formatDate date="${contratoInstance?.dataInicioVigencia}" format="dd/MM/yyyy" /></td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.dataFimVigencia.label" default="Fim da Vigência" /></td>
							<td valign="top" class="value"><g:formatDate date="${contratoInstance?.dataFimVigencia}" format="dd/MM/yyyy" /></td>
						</tr>
					
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.participanteVencedor.label" default="Vencedor Homologado" /></td>
							<td valign="top" class="value">${contratoInstance?.participanteVencedor?.cpfCnpj} - ${contratoInstance?.participanteVencedor?.nomeRazaoSocial}</td>						
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.contratado.label" default="Contratado" /></td>
							<td valign="top" class="value">${contratoInstance?.contratado?.cpfCnpj} - ${contratoInstance?.contratado?.nomeRazaoSocial}</td>						
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.valorContrato.label" default="Valor Contrato" /></td>
							<td valign="top" class="value"> <g:formatNumber number="${contratoInstance?.valorContrato}" type="currency"  maxFractionDigits="4" currencyCode="BRL" /></td>
						</tr>
						<g:if test="${contratoInstance.participanteVencedor != contratoInstance.contratado}">		
							<tr class="prop">
								<td valign="top" class="name"><g:message code="contrato.justificativaRecontrato.label" default="Justificativa Recontrato" /></td>
								<td valign="top" class="value">${contratoInstance?.justificativaRecontrato}</td>
							</tr>
						</g:if>	
						<g:if test="${contratoInstance?.tipoExtincaoContrato != null}">	
							<tr class="prop">
								<td valign="top" style="color:red" class="name"><g:message code="contrato.tipoExtincaoContrato.label" default="Tipo de Extinção" /></td>
								<td valign="top" style="color:red" class="value">${contratoInstance?.tipoExtincaoContrato?.nome}</td>
							</tr>
							<tr class="prop">
								<td valign="top" style="color:red" class="name"><g:message code="contrato.dataDestrato.label" default="Data Extinção" /></td>
								<td valign="top" style="color:red" class="value"><g:formatDate date="${contratoInstance?.dataDestrato}" format="dd/MM/yyyy" /></td>
							</tr>
							<tr class="prop">
								<td valign="top" style="color:red" class="name"><g:message code="contrato.justificativaDestrato.label" default="Justificativa Extinção" /></td>
								<td valign="top" style="color:red" class="value">${contratoInstance?.justificativaDestrato}</td>
							</tr>							
						</g:if>						
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.dateCreated.label" default="Data de Criação" /></td>
							<td valign="top" class="value"><g:formatDate date="${contratoInstance?.dateCreated}" format="dd/MM/yyyy HH:mm" /></td>
						</tr>
					
						<tr class="prop">
							<td valign="top" class="name"><g:message code="contrato.lastUpdated.label" default="Última Atualização" /></td>
							<td valign="top" class="value"><g:formatDate date="${contratoInstance?.lastUpdated}" format="dd/MM/yyyy HH:mm" /></td>
						</tr>
						
					</tbody>
				</table>
				<g:if test="${listaDocArquivo}">
				    <h4>Documento(s) Anexado(s)</h4>
					<table class="table table-striped table-focus table-hover table-bordered table-responsive">
						<thead> 
							<tr>
								<th>Documento </th>
								<th>Url</th>								
							</tr>
						</thead>
						<tbody>
		  					<g:each status="i" in="${listaDocArquivo}" var="doc">
 									<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
	      							<td>${doc.nome}</td>
									<td>${doc.nomeArquivo}</td>		      						
			    				</tr>					  				
			    		   </g:each>  
						</tbody>      
	 				</table>
				</g:if>
			</section>
	</tcmpa:rowDiv>

</div>
