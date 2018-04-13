<div>
	<tcmpa:rowDiv>
				<section id="visulaiza-extincao" class="first">
				<h2>Visualizar Informações do Ato Administrativo/Judicial</h2>
			
				<ul id="Menu" class="nav nav-pills">
			
					<g:set var="entityName" value="${message(code: params.controller+'.label', default: params.controller.substring(0,1).toUpperCase() + params.controller.substring(1).toLowerCase())}" />
					
				</ul>
				<g:hasErrors bean="${contratoApostInstance}">
					<div class="alert alert-error">
						<g:renderErrors bean="${contratoApostInstance}" as="list" />
					</div>
				</g:hasErrors>			
				<table class="table">
					<tbody>
						<!-- TIPO DE ATO -->
						<tr class="prop">
							<td valign="top" class="name"><g:message code="atoAdmJudicialInstance.statusLicitacao.label" default="tipo de Ato" /></td>
							<td valign="top" class="value">${fieldValue(bean: atoAdmJudicialInstance, field: "statusLicitacao.nome")}</td>							
						</tr>
					
						<!-- DATA INICIO VALIDADE -->
						<tr class="prop">
							<td valign="top" class="name"><g:message code="atoAdmJudicialInstance.dataInicioValidade.label" default="Data Início Validade" /></td>
							<td valign="top" class="value"><g:formatDate date="${atoAdmJudicialInstance?.dataInicioValidade}" format="dd/MM/yyyy HH:mm" /></td>
							
						</tr>

						<!-- DATA TÉRMINO VALIDADE -->
						<tr class="prop">
							<td valign="top" class="name"><g:message code="atoAdmJudicialInstance.dataFimValidade.label" default="Data Término Validade" /></td>
							<td valign="top" class="value"><g:formatDate date="${atoAdmJudicialInstance?.dataFimValidade}" format="dd/MM/yyyy HH:mm" /></td>
						</tr>

						<!-- JUSTIFICATIVA -->
						<tr class="prop">
							<td valign="top" class="name"><g:message code="atoAdmJudicialInstance.justificativa.label" default="Justificativa" /></td>
							<td valign="top" class="value">${fieldValue(bean: atoAdmJudicialInstance, field: "justificativa")}</td>
						</tr>
												
					 </tbody>
				</table>
				
				<g:if test="${listaDocArquivo}">
				    <h4>Documento(s) Anexado(s)</h4>
					<table class="table table-striped table-focus table-hover table-bordered table-responsive">
						<thead> 
							<tr>
								<th>Tipo Documento </th>
								<th>Arquivo</th>								
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
