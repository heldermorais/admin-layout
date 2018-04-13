<div class="input-prepend" id="statusDiv">
	<span class="add-on">Status</span>
	<g:select 
		id="statusLicitacao.id" 
		name="statusLicitacao.id" 
		value="${statusLicitacao?.id}"
		from="${statusLicitacaoList}" 
		optionKey="id" 
		optionValue="nome"
		noSelection="['':'Todos']"
		class="inline" />
</div>

<div class="input-prepend" id="statusDiv">
	<span class="add-on">Modalidade</span>
	<g:select 
		id="modalidade.id" 
		name="modalidade.id" 
		value="${modalidade?.id}"
		from="${modalidades}" 
		optionKey="id" 
		optionValue="nome"
		noSelection="['':'Todos']"
		class="inline" />
</div>

<div class="input-prepend" id="statusDiv">
	<span class="add-on">Tipo</span>
	<g:select 
		id="tipo.id" 
		name="tipo.id" 
		value="${tipo?.id}"
		from="${tipos}" 
		optionKey="id" 
		optionValue="nome"
		noSelection="['':'Todos']"
		class="inline" />
</div>

<div class="input-prepend">
	<span class="add-on">Nº do Processo Administrativo</span>
	<input 
		name="numeroProcessoAdm" 
	  	value="${params?.numeroProcessoAdm}"
	  	class="span5" 
	  	id="prependedInput" 
	  	type="text" 
	  	placeholder="Nº do Processo Administrativo" >
</div>

<div class="input-prepend">
	<span class="add-on">Nº do Edital</span>
	<input 
		name="numeroDocumento" 
	  	value="${params?.numeroDocumento}"
	  	class="span5" 
	  	id="prependedInput" 
	  	type="text" 
	  	placeholder="Nº do Edital" >
</div>

<div class="input-prepend">
	<span class="add-on">Objeto</span>
	<input 
		name="objeto" 
	  	value="${params?.objeto}"
	  	class="span5" 
	  	id="prependedInput" 
	  	type="text" 
	  	placeholder="Objeto" >
</div>

