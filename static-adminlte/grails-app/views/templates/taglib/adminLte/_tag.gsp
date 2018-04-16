<${tagName}
<% for(String attrName in attrs.keySet()) {  %>
   ${attrName}="${attrs[attrName]}"
<% } %>
<%= ">" %>
  <%= tagBody %>
</${tagName}>