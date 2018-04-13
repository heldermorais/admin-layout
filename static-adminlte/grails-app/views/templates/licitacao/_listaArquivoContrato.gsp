<tcmpa:gridcolumn property="nome" headerText="Nome" sortable="false"/>

<tcmpa:gridcolumn property="tipoDoc.nome" headerText="Tipo de Documento" sortable="false"/>
				
<tcmpa:gridcolumn property="arquivo" headerText="Arquivo" sortable="false">
	<g:if test="${data.urlArquivo}">
	    <a href="${data.urlArquivo}" target="_blank">${fieldValue(bean: data, field: "arquivo")}</a>
	</g:if>
	<g:else>
		<g:if test="${(data.contratoArqAvaliado != null) && (data.contratoArqAvaliado.valido == 0)}">
			<h6><font color="red">ARQUIVO CORROMPIDO NO MOMENTO DO ENVIO.</font>  </h6>${fieldValue(bean: data, field: "arquivo")}
		</g:if>
		<g:else>
			<a href="${createLink(action:'abrirArquivo', controller:'contrato', id:data.id, params: ['contratoArquivo_id': data.id])}" target="_blank">
				${fieldValue(bean: data, field: "arquivo")}
			</a>	
		</g:else>	
	</g:else>		
</tcmpa:gridcolumn>
		<tcmpa:gridcolumn property="usuarioCorrigiuArquivo.username" headerText="Usuario Corrigiu" sortable="false"/>
		<tcmpa:gridcolumn property="dataCorrecaoArquivo" headerText="Data Correção" sortable="false"/>
