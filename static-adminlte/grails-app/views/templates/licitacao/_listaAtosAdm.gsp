<tcmpa:gridcolumn property="nome" headerText="Nome" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data.statusLicitacao, field:"nome")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data.statusLicitacao, field:"nome")}
	</g:else>
</tcmpa:gridcolumn>

<tcmpa:gridcolumn property="dataInicioValidade" headerText="Dt. Início Validade" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"dataInicioValidade")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"dataInicioValidade")}
	</g:else>
</tcmpa:gridcolumn>
<tcmpa:gridcolumn property="dataFimValidade" headerText="Dt. Fim Validade" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"dataFimValidade")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"dataFimValidade")}
	</g:else>
</tcmpa:gridcolumn>

<tcmpa:gridcolumn property="justificativa" headerText="Justificativa" sortable="false">
	<g:if test="${data.ativo == 'N'}">
		<span style="color:red">${fieldValue(bean:data, field:"justificativa")}</span>
	</g:if>
	<g:else>
		${fieldValue(bean:data, field:"justificativa")}
	</g:else>
</tcmpa:gridcolumn>
				

