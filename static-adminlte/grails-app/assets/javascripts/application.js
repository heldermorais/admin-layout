// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require node_modules/jquery/dist/jquery.min
//= require node_modules/intercooler/dist/intercooler
//= require node_modules/bootstrap/dist/js/bootstrap.min
//= require node_modules/fastclick/lib/fastclick
//= require adminlte/js/adminlte.min
//= require node_modules/jquery-sparkline/dist/jquery.sparkline.min
//= require adminlte/plugins/jvectormap/jquery-jvectormap-1.2.2.min
//= require adminlte/plugins/jvectormap/jquery-jvectormap-world-mill-en
//= require node_modules/chart.js/Chart
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}
