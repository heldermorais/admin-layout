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
//= require node_modules/jszip/dist/jszip
//= require node_modules/pdfmake/buil/pdfmake
//= require node_modules/datatables.net/js/jquery.dataTables
//= require node_modules/datatables.net-buttons/js/buttons.colVis
//= require node_modules/datatables.net-buttons/js/buttons.html5
//= require node_modules/datatables.net-buttons/js/buttons.print
//= require node_modules/datatables.net-buttons/js/dataTables.buttons
//= require node_modules/datatables.net-colreorder/js/dataTables.colReorder
//= require adminlte/js/adminlte.min
//= require node_modules/jquery-sparkline/dist/jquery.sparkline.min
//= require adminlte/plugins/jvectormap/jquery-jvectormap-1.2.2.min
//= require adminlte/plugins/jvectormap/jquery-jvectormap-world-mill-en
//= require node_modules/chart.js/Chart
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {

$( document ).ready(function() {

            console.log( "ready!" );

            console.log('Ajaxstart setup.');
            $(document).ajaxStart(function() {
                $('#spinner').fadeIn();
            }).ajaxStop(function() {
                $('#spinner').fadeOut();
            });

            console.log('Datatables.net setup.');
            $('.datagrid').DataTable( {
                    columnDefs: [ {
                        orderable: false,
                        className: 'select-checkbox',
                        targets:   0
                    } ],
                    select: {
                        style:    'os',
                        selector: 'td:first-child'
                    },
                    order: [[ 1, 'asc' ]],
                    language: {
                                                processing:     "Processando...",
                                                search:         "Pesquisar:",
                                                lengthMenu:    "_MENU_ resultados por página",
                                                info:           "Mostrando os registros de _START_ até _END_ ( Total: _TOTAL_ )",
                                                infoEmpty:      " 0 registros",
                                                infoFiltered:   "(Filtrados _MAX_ registros)",
                                                infoPostFix:    "",
                                                loadingRecords: "Carregando...",
                                                zeroRecords:    "Nenhum registro encontrado",
                                                emptyTable:     "Nenhum registro encontrado",
                                                paginate: {
                                                    first:      "Primeiro",
                                                    previous:   "Anterior",
                                                    next:       "Próximo",
                                                    last:       "Último"
                                                },
                                                aria: {
                                                    sortAscending:  ": Ordenar colunas de forma ascendente",
                                                    sortDescending: ": Ordenar colunas de forma descendente"
                                                }
                    }
            } );

});


}
