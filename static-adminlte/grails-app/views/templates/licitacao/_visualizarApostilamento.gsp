<div>
	<tcmpa:rowDiv>
				<section id="visulaiza-extincao" class="first">
				<h1>Visualizar Informações do Apostilamento do Contrato</h1>
			
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
							<tr class="prop">
								<td valign="top" style="color:red" class="name"><g:message code="contratoApostInstance.numero.label" default="Nº Apostilamento" /></td>
								<td valign="top" style="color:red" class="value">${contratoApostInstance?.numero}</td>
							</tr>
							<tr class="prop">
								<td valign="top" style="color:red" class="name"><g:message code="contratoApostInstance.dataApostilamento.label" default="Data Apostilamento" /></td>
								<td valign="top" style="color:red" class="value"><g:formatDate date="${contratoApostInstance?.dataApostilamento}" format="dd/MM/yyyy" /></td>
							</tr>
							<tr class="prop">
								<td valign="top" style="color:red" class="name"><g:message code="contratoApostInstance.justificativa.label" default="Justificativa" /></td>
								<td valign="top" style="color:red" class="value">${contratoApostInstance?.justificativa}</td>
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
