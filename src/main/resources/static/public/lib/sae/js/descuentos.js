var elPath = $("#elPath").val();
var descuentoDataTable;

$(document).ready(function() {
	var urlRest= elPath +"/descuento/alumno/"+$("#id").val();
	
	descuentoDataTable = $('#descuentosTable').DataTable( {
        "language": {
        	"url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        },
    	"dom": "Bfrtip",
        "buttons": [
                    "excel", "pdf", "print"
                ],
    	"processing": true,
        //"serverSide": true,
        "ajax": {
        	"url": urlRest,
        	"type": "GET"
        },
        "columns": [
                    { "data": "id" },
                    { "data": "monto" },
                    { "data": "inicio" },
                    { "data": "fin" },
                    { "data": "urlBorrar",
                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    		$(nTd).html("<a href='#'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;");
                        } 
                    }
                ]
    } );
	
	
	
} );


$( function() {
    $( "#datepickerDescuento" ).datepicker({format: "dd-mm-yyyy", todayHighlight: true, language: "es", autoclose: true});
  } );
