<div>
	<tcmpa:rowDiv>
				<section id="visulaiza-extincao" class="first">
				<h1>Visualizar Informações da Extinção do Contrato</h1>
			
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
						
												
						<%--<g:if test="${contratoInstance?.contrato}">	
							<tr class="prop">
								<td valign="top" style="color:green" class="name"><g:message code="contrato.tipoAditivo.label" default="Tipo Aditivo" /></td>
								<td valign="top" style="color:green" class="value">${contratoInstance?.tipoAditivo?.nome}</td>
							</tr>
							<tr class="prop">
								<td valign="top" style="color:green" class="name"><g:message code="contrato.contrato.label" default="Contrato Aditivado" /></td>
								<td valign="top" style="color:green" class="value"><g:link action="show" params="['id': contratoInstance?.contrato?.id]">${contratoInstance?.contrato?.numero}</g:link></td>
							</tr>
						</g:if>						
						
					--%></tbody>
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
