<tcmpa:gridcolumn property="numero"     headerText="#" 			sortable="false"/>
<tcmpa:gridcolumn property="descricao"      headerText="Descrição" 	sortable="false"/>
<tcmpa:gridcolumn property="valorReferencia"      headerText="Valor Referência" 	sortable="false">
	<g:formatNumber number="${data.valorReferencia}" type="currency" maxFractionDigits="4"/>		
</tcmpa:gridcolumn>
<tcmpa:gridcolumn property="itens" headerText="Qtd. Itens" 	sortable="false">
	${data.itens.size()}
</tcmpa:gridcolumn>
<tcmpa:gridcolumn property="valorAdjudicado"      headerText="Valor Adjudicado" 	sortable="false">
	<g:formatNumber number="${data.valorAdjudicado}" type="currency" maxFractionDigits="4"/>		
</tcmpa:gridcolumn>
<tcmpa:gridcolumn property="status.nome" 	headerText="Status" 	sortable="false"/>
