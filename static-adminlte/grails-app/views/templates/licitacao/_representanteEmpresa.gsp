<g:if test="${contratoInstance?.contratado?.tipoPessoa == 'J'}">
	<div>
            <div>
                Quando um contrato é realizado entre um órgão público e uma pessoa jurídica, é possível cadastrar uma pessoa física que exercerá o papel de representante da empresa.
                O contrato poderá ser assinado, assim, pelo eCNPJ da empresa ou pelo eCPF do representante.
            </div>

                <!-- REPRESENTANTE -->
                <div class="control-group fieldcontain ${hasErrors(bean: contratoInstance, field: 'cpfRepresentante', 'error')} required">
                    <label for="cpf" class="control-label">
                    <g:message code="contrato.numero.label" default="CPF do Representante: " /></label>
                    <div class="controls">
                        <input id="cpfRepresentante" name="contratoInstance.cpfRepresentante" value="${contratoInstance?.cpfRepresentante}" />
                        <span class="help-inline">${hasErrors(bean: contratoInstance, field: 'cpfRepresentante', 'error')}</span>
                    </div>
                </div>
                
                <div class="control-group fieldcontain ${hasErrors(bean: contratoInstance, field: 'nomeRepresentante', 'error')} required">
                    <label for="nome" class="control-label">
                    <g:message code="contrato.numero.label" default="Nome do Representante: " /></label>
                    <div class="controls">
                            <input id="nomeRepresentante" name="contratoInstance.nomeRepresentante" value="${contratoInstance?.nomeRepresentante}" />
                        <span class="help-inline">${hasErrors(bean: contratoInstance, field: 'nomeRepresentante', 'error')}</span>
                    </div>
                </div>

              <%--<div>
                    Para que o representante seja cadastrado, é necessário inserir um arquivo com uma <b>Procuração Pública</b> que concede à pessoa física o poder de assinar o contrato em nome da empresa.
                    O arquivo da procuração deve ser no formato PDF e deve ser assinado digitalmente pelo eCNPJ do órgão público contratante.
                </div>
            --%>   
	</div>
</g:if>