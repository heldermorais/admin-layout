<div class="alert">
	<strong>Nº da Licitação: </strong>${licitacaoInstance?.numeroDocumento}<br/>
	<strong>Nº do Processo Adm.: </strong>${licitacaoInstance?.numeroProcessoAdm}<br/>
	<strong>Data de Abertura: </strong><g:formatDate format="dd/MM/yyyy" date="${licitacaoInstance?.dataAbertura}"/><br/>
	<strong>Modalidade: </strong>${licitacaoInstance?.modalidade.nome}<br/>
	<strong>Critério Avaliação: </strong>${licitacaoInstance?.criterioAvaliacao == 1 ? 'Por ltem' : 'Por lote'}<br/>
	<strong>Credenciamento: </strong>${licitacaoInstance?.credenciamento == 0 ? 'Não' : 'Sim'}<br/>
	<strong>Situação: </strong>${licitacaoInstance?.statusLicitacao.nome}<br/>
	<g:if test="${licitacaoInstance?.atoAdmJudicial}">         	
		<strong>Ato Adm. Judicial:: </strong><g:link controller="atoAdmJudicial" action="show" params="['id': licitacaoInstance?.atoAdmJudicial?.id]"> <g:message code="default.statusLicitacao.label"  args="['']" default="${licitacaoInstance?.atoAdmJudicial?.statusLicitacao?.nome}"/></g:link><br/>			
	</g:if>
	<!-- strong>Valor: </strong><br/ -->
</div>

<g:link controller="licitacao" action="show" params="['id': licitacaoInstance.id]"><i class="icon-arrow-left"></i> <g:message code="default.show.label"  args="['Licitacao']"/></g:link>
<g:if test="${!publicado}">
    <g:link controller="contrato" action="list" params="['id': licitacaoInstance.id]"><i class="icon-arrow-left"></i> <g:message code="default.list.label" args="['Contratos']"/></g:link>
</g:if>


<%--<tcmpa:rowDiv>



		<tcmpa:div span="12" extraClasses="well">
   
	   <div class="span6">
	    
			<strong>Orgão Licitação: </strong>${licitacaoInstance?.orgao.nomeExibicao}<br/>
			
			<strong>Nº da Licitação: </strong>${licitacaoInstance?.numeroDocumento}<br/>
			<strong>Nº do Processo Adm.: </strong>${licitacaoInstance?.numeroProcessoAdm}<br/>
			<strong>Data de Abertura: </strong><g:formatDate format="dd/MM/yyyy" date="${licitacaoInstance?.dataAbertura}"/><br/>
	   </div>
	   
		<div class="span6">
			<strong>Objeto: </strong>${licitacaoInstance?.objeto}<br/>
		</div>
			
   
</tcmpa:div>
</tcmpa:rowDiv>
<g:link controller="licitacao" action="show" params="['id': licitacaoInstance.id]"><i class="icon-arrow-left"></i> <g:message code="default.show.label"  args="['Licitacao']"/></g:link>
<g:if test="${!publicado}">
    <g:link controller="contrato" action="list" params="['id': licitacaoInstance.id]"><i class="icon-arrow-left"></i> <g:message code="default.list.label" args="['Contratos']"/></g:link>
</g:if>--%>