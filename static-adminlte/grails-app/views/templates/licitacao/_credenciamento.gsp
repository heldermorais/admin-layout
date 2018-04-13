<div>
		<g:if test="${licitacaoInstance?.podeTerCredenciamento()}"><%--
	
		<g:if test="${licitacaoInstance?.modalidade?.id == 51 || licitacaoInstance?.modalidade?.id == 11 || licitacaoInstance?.modalidade?.id == 12 || licitacaoInstance?.modalidade?.id == 13 }">			
			--%><div class="control-group fieldcontain ${hasErrors(bean: licitacaoInstance, field: 'credenciamento', 'error')} ">
				<label for="credenciamento" class="control-label"><g:message code="licitacao.credenciamento.label" default="Credenciamento" /></label>
				<div class="controls">
					
					<g:radioGroup name="credenciamento" value="${licitacaoInstance.credenciamento}" id="credenciamento"
					disabled="${licitacaoInstance?.statusLicitacao.id != 1 || licitacaoInstance?.modalidade?.obrigaPublicidade == 0}"
					        labels="['Não','Sim']"
					        values="['0','1']">
					
					    <label>
					            <span class="radioSpan">${it.radio}</span>
					            ${it.label}
					    </label>
					
					</g:radioGroup>
			
					<span class="help-inline">${hasErrors(bean: licitacaoInstance, field: 'credenciamento', 'error')}</span>
					<tcmpa:dica chave="licitacao.credenciamento"/>
				</div>
			</div>
			
			<!-- Data Inicio credenciamento -->
			<div class="control-group fieldcontain ${hasErrors(bean: licitacaoInstance, field: 'dataInicioCredenc', 'error')} ">
				<label for="dataInicioCredenc" class="control-label"><g:message code="licitacao.dataInicioCredenc.label" default="Data Início Credenciamento" /></label>
				<div class="controls">
			
								<tcmpa:datePicker name="dataInicioCredenc" precision="day" data-date-language="pt-BR" default="none" value="${licitacaoInstance?.dataInicioCredenc}"
								            disabled="${licitacaoInstance?.statusLicitacao.id != 1 || licitacaoInstance?.modalidade?.obrigaPublicidade == 0}" />					  
								<span class="help-inline">${hasErrors(bean: licitacaoInstance, field: 'dataInicioCredenc', 'error')}</span>
								<tcmpa:dica chave="licitacao.dataInicioCredenc"/>
					<span class="help-inline">${hasErrors(bean: licitacaoInstance, field: 'dataInicioCredenc', 'error')}</span>
					<tcmpa:dica chave="licitacao.dataInicioCredenc"/>
				</div>
			</div>
			
			<!-- Data Fim credenciamento -->
			<div class="control-group fieldcontain ${hasErrors(bean: licitacaoInstance, field: 'dataFimCredenc', 'error')} ">
				<label for="dataFimCredenc" class="control-label"><g:message code="licitacao.dataFimCredenc.label" default="Data Término Credenciamento" /></label>
				<div class="controls">
								<tcmpa:datePicker name="dataFimCredenc" precision="day" data-date-language="pt-BR" default="none" value="${licitacaoInstance?.dataFimCredenc}"  
								            disabled="${licitacaoInstance?.statusLicitacao.id != 1 || licitacaoInstance?.modalidade?.obrigaPublicidade == 0}" />
								<span class="help-inline">${hasErrors(bean: licitacaoInstance, field: 'dataFimCredenc', 'error')}</span>
								<tcmpa:dica chave="licitacao.dataFimCredenc"/>
					<span class="help-inline">${hasErrors(bean: licitacaoInstance, field: 'dataFimCredenc', 'error')}</span>
					<tcmpa:dica chave="licitacao.dataFimCredenc"/>
				</div>
			</div>
		
		</g:if>	
</div>
