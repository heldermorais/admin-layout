<tcmpa:navlist>

		  <tcmpa:navlistHeader label="Menu Licitação"  />
		  <tcmpa:navlistItem   label="Página Inicial" icon="home" url="${createLink(controller: 'home', action:'index')}" />
		  <tcmpa:navlistItem   label="Site do TCM/PA" icon="off" url="http://www.tcm.pa.gov.br" />

		  <tcmpa:navlistHeader label="Licitação"  />
  		  <!-- DETALHE -->
	  	  <tcmpa:navlistItem   label="Ver Detalhes" url="${createLink(controller: 'licitacao', action:'show', id: licitacaoInstance.id )}"  icon="zoom-in" />
		  <!-- CRIAR -->
		  <shiro:authenticated>
		  	<tcmpa:navlistItem   label="Nova Licitação" url="${createLink(controller: 'licitacao', action:'create')}"  icon="plus" />
		  </shiro:authenticated>
		  <!-- LISTAR -->
		  <tcmpa:navlistItem   label="Listar" url="${createLink(controller: 'licitacao', action:'list')}"  icon="th-list" />

		  <li class="divider"></li>

		  <span id="novasOperacoes">
		  	<!-- ALTERAR -->
		  	<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="licitacao:edit">
				<tcmpa:navlistItem   label="Alterar" url="${createLink(controller: 'licitacao', action:'edit', id: licitacaoInstance.id )}"  icon="pencil" />
			</tcmpa:hasPermission>	
			<!-- CANCELAR -->
			<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="licitacao:cancelar">
				<tcmpa:navlistItem   label="Cancelar" url="${createLink(controller: 'licitacao', action:'cancelar', id: licitacaoInstance.id )}"  icon="minus" />
			</tcmpa:hasPermission>
			<!-- REMOVER -->
			<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="licitacao:delete">
				<g:render template="/_common/modals/deleteTextLink" model="[controller: 'licitacao', itemId: licitacaoInstance.id]" />
			</tcmpa:hasPermission>
			<!-- PUBLICAR -->
			<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="licitacao:publicar">
				<tcmpa:navlistItem   label="Publicar" url="${createLink(controller: 'licitacao', action:'publicar', id: licitacaoInstance.id )}"  icon="plus" />
			</tcmpa:hasPermission>
			<!-- GERAR Nº TCM -->
			<%--<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="licitacao:gerarNumeroTCM">
				<tcmpa:navlistItem   label="Gerar Número TCM" url="${createLink(controller: 'licitacaoValidacaoLog', action:'showValidacao', id: licitacaoInstance.id )}"  icon="barcode" />
			</tcmpa:hasPermission>

		  --%></span>
		  
		  <li class="divider"></li>
		  <!-- VER LOTES -->
		  <tcmpa:navlistHeader label="Itens/Lotes"  />
		  	<tcmpa:navlistItem   label="ver Itens" url="${createLink(controller: 'licitacaoItem' , action:'list',  params: ['id': licitacaoInstance.id] )}"  icon="plus" />
		  
 		  <g:if test="${licitacaoInstance?.criterioAvaliacao == 2}">
		  	   <tcmpa:navlistItem   label="ver Lotes" url="${createLink(controller: 'licitacaoLote' , action:'list',  params: ['id': licitacaoInstance.id] )}"  icon="plus" />
		  </g:if>		  
		  	
		  <tcmpa:navlistHeader label="Publicidade"  />
		  <!-- VER PUBLICIDADES -->
           	<tcmpa:navlistItem   label="ver Publicidade" url="${createLink(controller: 'publicidade' , action:'list',  params: ['licitacao.id': licitacaoInstance.id] )}"  icon="plus" />				  
		  
		    <li class="divider"></li>
		  <tcmpa:navlistHeader label="Arquivos"  />
		  <!-- VER ARQUIVOS -->
		  <tcmpa:navlistItem   label="ver Arquivos" url="${createLink(controller: 'arquivoLicitacao' , action:'list',  params: ['licitacao.id': licitacaoInstance.id] )}"  icon="plus" />
		  
		   <g:if test="${licitacaoInstance?.statusLicitacao.id >= 2 || licitacaoInstance?.modalidade?.obrigaPublicidade == 0}">
		  		<li class="divider"></li>		   
		  		<tcmpa:navlistHeader label="Propostas/Julgamento/Adjudicação"  />
		  		<tcmpa:navlistItem   label="ver Participantes" url="${createLink(controller: 'participante' , action:'list',  params: ['licitacao.id': licitacaoInstance.id] )}"  icon="plus" />		  
		  		<tcmpa:navlistItem   label="ver Julgamento" url="${createLink(controller: 'julgamento' , action:'listProposta',  params: ['licitacao.id': licitacaoInstance.id] )}"  icon="plus" />
		  		
				<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="homologacao:list">
			  		<tcmpa:navlistItem   label="ver Adjudicação/Homologação" url="${createLink(controller: 'homologacao' , action:'listHomologados',  params: ['licitacao.id': licitacaoInstance.id] )}"  icon="plus" />
				</tcmpa:hasPermission>
		  		
			</g:if>
		  <g:if test="${licitacaoInstance?.statusLicitacao.id == 2 || licitacaoInstance?.statusLicitacao.id == 4 || licitacaoInstance?.modalidade?.obrigaPublicidade == 0}">

			  <li class="divider"></li>
			  <tcmpa:navlistHeader label="Homologação"  />
			  
				<!-- HOMOLOGAR -->
				<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="licitacao:homologar">
					<tcmpa:navlistItem   label="Homologar" url="${createLink(controller: 'licitacao', action:'homologar', id: licitacaoInstance.id )}"  icon="plus" />
				</tcmpa:hasPermission>

				<!-- CONTRATO -->
				<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="contrato:list">
				    <tcmpa:navlistItem   label="ver Contratos" url="${createLink(controller: 'contrato' , action:'list',  params: ['id': licitacaoInstance.id] )}"  icon="plus" />					
				</tcmpa:hasPermission>
			  		 
		 
		  </g:if>		

		  <g:if test="${licitacaoInstance?.statusLicitacao.id >= 2}">

			  <li class="divider"></li>
			  
			  <tcmpa:navlistHeader label="Atos Administrativo/Judicial"  />			  
				<!-- Supender -->
				<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="atoAdmJudicial:suspender">				
					<tcmpa:navlistItem   label="Suspender" url="${createLink(controller: 'atoAdmJudicial', action:'suspender', id: licitacaoInstance.id)}"  icon="plus" />
				</tcmpa:hasPermission>

				<!-- Revogar -->
				<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="atoAdmJudicial:revogar">
					<tcmpa:navlistItem   label="Revogar" url="${createLink(controller: 'atoAdmJudicial', action:'revogar', id: licitacaoInstance.id )}"  icon="plus" />
				</tcmpa:hasPermission>
			  		 
				<!-- Anular-->
				<tcmpa:hasPermission licitacaoId="${licitacaoInstance.id }" permission="atoAdmJudicial:anular">
					<tcmpa:navlistItem   label="Anular" url="${createLink(controller: 'atoAdmJudicial', action:'anular', id: licitacaoInstance.id )}"  icon="plus" />
				</tcmpa:hasPermission>
		 
		  </g:if>		
		  
		    
		  <li class="divider"></li>
			  <g:if test="${licitacaoInstance?.statusLicitacao?.id > 1}">
		  		<a href="${createLink(action:'imprimirRelatorio', controller:'licitacao', id:licitacaoInstance.id)}" target="_blank">Imprimir Relatório</a>
		 </g:if>
		 

</tcmpa:navlist>