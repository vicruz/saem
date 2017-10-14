$(document).ready(function() {
	var elPath = $("#elPath").val();
    	    $('#pagogrado').DataTable( {
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
    	        	"url": elPath + "/pagoGradoRest",
    	        	"type": "POST"
    	        },
    	        "columns": [
    	                    { "data": "idPagoGrado" },
    	                    { "data": "concepto" },
    	                    { "data": "monto" },
    	                    { "data": "grado" },
    	                    { "data": "mes" },
    	                    { "data": "anio" },
    	                    { "data": "fechaLimite" }
    	                ]
    	    } );
    	} );

function cambiaMonto(){
	var selectedValue= $("#selectConcepto").val();
	var elPath = $("#elPath").val();
	var url = elPath + "/catpagos/getMonto/"+selectedValue;
	$.get(url,function(respuesta){
		$("#monto").val(respuesta);
	})
}

$( function() {
    $( "#datepicker" ).datepicker({format: "yyyy-mm-dd", weekStart: 0, todayHighlight: true, daysOfWeekDisabled: "0,6", language: "es", autoclose: true});
  } );
