<table class="table table-striped table-focus table-hover table-bordered table-responsive">
  <thead>
	<tr>
	  <g:render template="${templatePath}/components/grid/clistviewHeaders" collection="${attrs?.columns}"/>
	  <g:if test="${!attrs.noLink}">
      <th>Ações</th>
      </g:if>
	</tr>
  </thead>
  <tbody>
	<g:each in="${attrs.dataprovider}" status="i" var="registro">
	  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

		<g:each in="${attrs?.columns}" var="column">
		  <g:if test="${column.externalBody}">
		  	<td><!-- com external Body -->
		    	<% 
				  column.externalBody.data = registro
		    	  out << column.externalBody()
		    	%>  
		    </td>
		  </g:if>		
	      <g:else>
		     <g:render template="${templatePath}/components/grid/clistviewBody" 
		                  model="${[ 
						           data: registro, 
						           columnName: column.property, 
						           usertemplate: column?.template
				   		           ]}" /> 
          </g:else>						        
		</g:each>

		<g:if test="${!attrs.noLink}">
		<td>
		  <g:if test="${attrs.controller}">
			<div id="user-toolbar-options" >
				<g:link class="btn blue" action="show" id="${registro.id}" controller="${attrs.controller}" data-toggle="tooltip" rel="tooltip" title="Detalhes" params="${attrs.showParams}"><i class="fas fa-search"></i></g:link>&nbsp;&nbsp;
				<g:if test="${attrs.editable}">
					<g:link class="btn blue" action="edit" id="${registro.id}" controller="${attrs.controller}" data-toggle="tooltip" rel="tooltip" title="Editar"><i class="fas fa-edit"></i></g:link>&nbsp;&nbsp;
				<g:if test="${!attrs.nodelete}">																
					<g:link class="btn red" action="excluir" id="${registro.id}" controller="${attrs.controller}" data-toggle="tooltip" rel="tooltip" title="Excluir"><i class="fas fa-trash-alt"></i></g:link>
				</g:if>
				</g:if>	
				<g:if test="${attrs.deletable}">
					<g:link class="btn red" action="delete" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Excluir"><i class="fas fa-trash-alt"></i></g:link>
				</g:if>	
							
								
			</div>	
	      </g:if>		
	      <g:else>
			<div id="user-toolbar-options" >
				<shiro:notAuthenticated>
				<g:link class="btn blue" action="show" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Detalhes" params="${attrs.showParams}"><i class="fas fa-search"></i></g:link>&nbsp;&nbsp;
				</shiro:notAuthenticated>
				<shiro:authenticated>
				<g:if test="${registro.hasProperty('orgao')}">
				<g:if test="${registro?.orgao?.id == usuarioSessao?.orgao?.id}">
				<g:link class="btn blue" action="show" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Detalhes" params="${attrs.showParams}"><i class="fas fa-search"></i></g:link>&nbsp;&nbsp;
				</g:if>
				</g:if>
				<g:if test="${!registro.hasProperty('orgao')}">
				<g:link class="btn blue" action="show" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Detalhes" params="${attrs.showParams}"><i class="fas fa-search"></i></g:link>&nbsp;&nbsp;
				</g:if>
				</shiro:authenticated>
				
				<g:if test="${attrs.contrato && registro?.statusLicitacao?.id == 4 || attrs.contrato && registro?.statusLicitacao?.id == 2}">
				   <g:link class="btn blue" action="list" controller="contrato" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Contratos"><i class="fas fa-file-pdf"></i></g:link>&nbsp;&nbsp;
				</g:if>		
				
				<g:if test="${attrs.aditivo}">
				   <g:link class="btn blue" action="createAditivo" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Aditivos"><i class="fas fa-file-medical"></i></g:link>&nbsp;&nbsp;
				</g:if>		
									
				<g:if test="${attrs.editable}">
					<g:link class="btn blue" action="edit" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Editar"><i class="fas fa-edit"></i></g:link>&nbsp;&nbsp;
				<g:if test="${!attrs.nodelete}">																
					<g:link class="btn red" action="excluir" id="${registro.id}" data-toggle="tooltip" rel="tooltip" title="Excluir"><i class="fas fa-trash-alt"></i></g:link>
				</g:if>
				</g:if>
				
			</div>
	      </g:else>
		</td>
		</g:if>

      </tr>
	</g:each>
</tbody>
</table>