<tcmpa:navlist>

		  <tcmpa:navlistHeader label="Menu Licitação"  />
		  <tcmpa:navlistItem   label="Página Inicial" icon="home" url="${createLink(controller: 'home', action:'index')}" />
		  <tcmpa:navlistItem   label="Site do TCM/PA" icon="off" url="http://www.tcm.pa.gov.br" />

		  <tcmpa:navlistHeader label="Licitação"  />
		  
  		  <!-- DETALHE -->
  		  <g:if test="${licitacaoInstance?.orgao?.id == usuarioSessao?.orgao?.id}">
	  	  	<tcmpa:navlistItem   label="Ver Detalhes" url="${createLink(controller: 'licitacao', action:'show', id: licitacaoInstance.id )}"  icon="zoom-in" />
	  	  </g:if>
		  
		  <!-- CRIAR -->
		  <shiro:authenticated>
		  	<tcmpa:navlistItem   label="Nova Licitação" url="${createLink(controller: 'licitacao', action:'create')}"  icon="plus" />
		  </shiro:authenticated>
		  <!-- LISTAR -->
		  <tcmpa:navlistItem   label="Listar" url="${createLink(controller: 'licitacao', action:'list')}"  icon="th-list" />

		  <li class="divider"></li>

		  <li class="divider"></li>

</tcmpa:navlist>