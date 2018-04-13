<div class="input-prepend">
	<span class="add-on">Município</span>
	<g:select 
		id="municipio.id" 
		name="municipio.id" 
		value="${municipio?.id}"
		from="${municipios}" 
		optionKey="id" 
		noSelection="['':'Todos']"
		disabled="${org.apache.shiro.SecurityUtils.subject?.authenticated}"
		onchange="${remoteFunction(controller:'orgaoAjax',action:'findAllByMunicipio',update:'orgaosDiv',params:'\'municipio.id=\' + escape(this.value)')}"/>
</div>

<div class="input-prepend" id="orgaosDiv">
	<span class="add-on">Órgão</span>
	<g:select 
		id="orgao" 
		name="orgao.id" 
		value="${orgao?.id}"
		from="${orgaos}" 
		optionKey="id" 
		optionValue="nomeCnpj"
		noSelection="['':'Todos']"
		disabled="${org.apache.shiro.SecurityUtils.subject?.authenticated}"
		class="inline" />
</div>

<div class="input-prepend">
	<span class="add-on">Termo de Busca</span>
	<input 
		name="termoBusca" 
	  	value="${params?.termoBusca}"
	  	class="span5" 
	  	id="prependedInput" 
	  	type="text" 
	  	placeholder="Nº do processo, objeto, modalidade etc." >
</div>