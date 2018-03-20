// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require node_modules/jquery/dist/jquery
//= require fontawesome-5.0.8/js/fontawesome-all
//= require node_modules/fastclick/lib/fastclick
//= require node_modules/bootstrap/dist/js/bootstrap
//= require node_modules/select2/dist/js/select2
//= require node_modules/select2/dist/js/i18n/pt-BR
//= require datatables/datatables

//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });


        $('.select2').select2({
                                   width: 'resolve' // need to override the changed default
                               });


        $('.datatable').DataTable({
        "language": {

                        "decimal":        ",",
                        "emptyTable":     "Nenhum registro encontrado",
                        "info":           "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                        "infoEmpty":      "Mostrando 0 até 0 de 0 registros",
                        "infoFiltered":   "(Filtrados de _MAX_ registros)",
                        "infoPostFix":    "",
                        "thousands":      ".",
                        "lengthMenu":     "_MENU_ resultados por página",
                        "loadingRecords": "Carregando...",
                        "processing":     "Processando...",
                        "search":         "Pesquisar",
                        "zeroRecords":    "Nenhum registro encontrado",
                        "paginate": {
                            "first":      "Primeiro",
                            "last":       "Último",
                            "next":       "Próximo",
                            "previous":   "Anterior"
                        },
                        "aria": {
                            "sortAscending":  ": ordem ascendente",
                            "sortDescending": ": ordem descendente"
                        }
                        ,
                        select: {
                            rows: {
                               _: "Você selecionou %d linhas",
                               0: "",
                               1: ""
                        }
             }

                }
                ,
                select: {
                   style: 'single'
                },
                fixedHeader: true,
                colReorder: true,
                responsive: true,
                "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todos"]],
                dom: 'B<"clear">lfrtip',
                buttons: {
                name: 'hhhprimary',
                buttons: [
                        { extend: 'copy', text: 'Copiar', className: 'btn btn-primary' },
                        { extend: 'excel', text: 'Salvar como XLS' },
                        { extend: 'pdf', text: 'Salvar como PDF' },
                        {
                            text: 'My button', className: 'btn btn-primary',
                            action: function ( dt ) {
                                console.log( 'My custom button!' );
                            }
                        }
                ]}
        });

    })(jQuery);
}
