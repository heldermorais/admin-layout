<a href="" data-toggle="popover" 
           rel="popover_${dica?.id}" 
		   data-original-title="${dica?.nome}" 
		   data-trigger="${trigger}"
           >
  <i class="${iconName}"></i>
</a>
	
<div id="popover_content_wrapper_${dica?.id}" style="display: none">
<g:if test="${dica?.descricao}">
	<b>O que é isso?</b> ${dica?.descricao}
	<br/>
</g:if>

<g:if test="${dica?.exemplo}">
	<b>Exemplos:</b> ${dica?.exemplo}
	<br/>
</g:if>

<g:if test="${dica?.restricao}">
	<b>Restrições: </b> ${dica?.restricao}
	<br/>
</g:if>
</div>
	
<script>  
		$(function(){
	    	$('[rel=popover_${dica?.id}]').popover({ 
	    		html : true, 
	    		placement: "${position}",
	    		content: function() {
	      			return $('#popover_content_wrapper_${dica?.id}').html();
	    		} 	
			});
		});
</script>
