<!-- Template: /templates/taglib/bootstrap/_navbar.gsp -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top ">
  <a class="navbar-brand" href="#"><asset:image src="grails.svg" alt="Grails Logo"/></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse mr-sm-2" id="navbarSupportedContent">

    <ul class="navbar-nav mr-auto">
      <g:pageProperty name="page.nav" />
    </ul>


  </div>
</nav>