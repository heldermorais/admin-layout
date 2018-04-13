<span class="add-on">Órgão</span>
<g:select 
	id="orgao" 
	name="orgao.id" 
	value="${orgao?.id}"
	from="${orgaos}" 
	optionKey="id" 
	optionValue="nomeCnpj"
	noSelection="['':'Todos']"
	class="inline" />
