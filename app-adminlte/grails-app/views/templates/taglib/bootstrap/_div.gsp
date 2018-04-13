<!-- template: /templates/taglib/bootstrap/_div.gsp -->
<div class="card" style="width: 18rem;">
  <div class="card-header">
    Featured
  </div>
  <div class="card-body">
    <h5 class="card-title">Title : ${attrs?.title}</h5>
    <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
    <%= tbody %>
  </div>
</div>