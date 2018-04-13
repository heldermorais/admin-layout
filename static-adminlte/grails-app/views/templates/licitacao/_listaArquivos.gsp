<tcmpa:gridcolumn property="nome" headerText="Nome" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"nome")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"nome")}
	</g:else>
</tcmpa:gridcolumn>

<tcmpa:gridcolumn property="dateCreated" headerText="Dt. Inclusão" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"dateCreated")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"dateCreated")}
	</g:else>
</tcmpa:gridcolumn>

<tcmpa:gridcolumn property="tipoDoc.nome" headerText="Tipo de Documento" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"tipoDoc.nome")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"tipoDoc.nome")}
	</g:else>
</tcmpa:gridcolumn>
				
<tcmpa:gridcolumn property="arquivo" headerText="Arquivo" sortable="false">
	<g:if test="${data.urlArquivo}">
	    <a href="${data.urlArquivo}" target="_blank">${fieldValue(bean: data, field: "arquivo")}</a>
	</g:if>
	<g:else>
		<g:if test="${(data.arquivoLicitacaoAvaliado != null) && (data.arquivoLicitacaoAvaliado.valido == 0)}">
			<h6><font color="red">ARQUIVO CORROMPIDO NO MOMENTO DO ENVIO.</font>  </h6>${fieldValue(bean: data, field: "arquivo")}
		</g:if>
		<g:else>
			<a href="${createLink(action:'abrirArquivo', controller:'arquivoLicitacao', id:data.id, params: ['licitacao.id': licitacaoInstance.id])}" target="_blank">
				${fieldValue(bean: data, field: "arquivo")}
			</a>	
		</g:else>	
	</g:else>		
</tcmpa:gridcolumn>

<tcmpa:gridcolumn property="usuarioCorrigiuArquivo.username" headerText="Usuario Corrigiu" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"usuarioCorrigiuArquivo.username")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"usuarioCorrigiuArquivo.username")}
	</g:else>
</tcmpa:gridcolumn>

<tcmpa:gridcolumn property="dataCorrecaoArquivo" headerText="Data Correção" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"dataCorrecaoArquivo")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"dataCorrecaoArquivo")}
	</g:else>
</tcmpa:gridcolumn>
		
