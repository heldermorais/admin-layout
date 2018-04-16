<li id="${attrs?.id}"
    class="${attrs?.class}">
    <a id="link_${attrs?.id}" class="${attrs?.linkClass}" href="${ attrs?.href ? attrs?.href : '#' }"><i class="${attrs?.icon}"></i> ${attrs?.text}
        <%= tagBody %>
    </a></li>