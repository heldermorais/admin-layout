 <select ${attrs.multiple ? 'multiple="multiple"' : ''} id="${attrs.id ? attrs.id : attrs.name}" 
 		name="${attrs.name}" ${attrs.required ? 'required="true"' : ''}
 		style="width: ${attrs.width ? attrs.width: 220}px; visibility: hidden" data-placeholder="Selecione"
 		${attrs.onchange ? 'onchange="'+attrs.onchange+'"' : ''} 
 		>
 	<option></option>
 	<g:each  in="${attrs.dataprovider}" status="i" var="registro">
 		<option value="${registro.id ? registro?.id : attrs.value}" ${registro?.id == attrs.value ? 'selected' : ''}>
 			${attrs.registroToString ? registro : registro.nome}
 		</option>
 	</g:each>
 </select>
 <script>
$(document).ready(function() { $("#${attrs.id ? attrs.id : attrs.name}").select2({
	 width: "resolve", allowClear: true
	}); 
});
</script>