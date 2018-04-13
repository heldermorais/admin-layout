<tcmpa:gridcolumn property="numero" headerText="Numero" sortable="false"/>
<tcmpa:gridcolumn property="descricao" headerText="Descricao" sortable="false"/>
<tcmpa:gridcolumn property="quantidade" headerText="Quantidade" sortable="false"/>					  
<tcmpa:gridcolumn property="valorReferencia" headerText="Valor Referencia" sortable="false">
	<g:formatNumber number="${data.valorReferencia}" type="currency" maxFractionDigits="4"/>							
</tcmpa:gridcolumn>
<tcmpa:gridcolumn property="total" headerText="Total" sortable="false">
	<g:formatNumber number="${data.total}" type="currency" maxFractionDigits="4"/>	
</tcmpa:gridcolumn>	
<tcmpa:gridcolumn property="status.nome" headerText="Status" sortable="false"/>	
