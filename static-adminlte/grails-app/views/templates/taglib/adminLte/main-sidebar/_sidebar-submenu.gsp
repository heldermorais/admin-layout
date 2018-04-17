<li class="treeview">
    <a href="#">
        <i class="${attrs?.cssClass} ${attrs?.icon}"></i> <span>${attrs?.text}</span>
        <span class="pull-right-container">
                       <i class="fa fa-angle-left pull-right"></i>
                    </span>
    </a>
    <ul class="treeview-menu">
        <%= tagBody %>
    </ul>
</li>