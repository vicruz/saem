$(document).ready(function() {
	var elPath = $("#elPath").val();
    var tablaPagos = $('#pagos').DataTable( {
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
    	        	"url": elPath + "/catpagos/addConcepto",
    	        	"type": "POST"
    	        },
    	        "columns": [
    	                    { "data": "id" },
    	                    { "data": "concepto" },
    	                    { "data": "monto", render: $.fn.dataTable.render.number( ',', '.', 2, '$' )  },
    	                    { "data": "fecha" },
    	                    { "data": "beca" },
    	                    { "data": "generaAdeudo" },
    	                    { "data": "pagoUnico" },
    	                    { "data": "url",
    	                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
    	                            //$(nTd).html("<a href='"+elPath+oData.url+"'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>&nbsp;Borrar</a>");
    	                            $(nTd).html("<a href='"+elPath+oData.url+"'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;" +
    	                            //"<a data-target=\"#myPago\" data-toggle=\"modal\" id=\"modifyAlumno\" href=\"#myPago\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>");
    	                            "<button type=\"button\" class=\"btn btn-link\" data-target=\"#myPago\" data-toggle=\"modal\" id=\"modifyAlumno\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></button>");
    	                        }
    	                    }
    	                ]
    	    } );
    
    $('#pagos tbody').on( 'click', 'button', function () {
    	var objTabla = $(this).parents('tr');
        var values = tablaPagos.row( objTabla ).data();
        var idx = tablaPagos.row( objTabla ).index();
        document.getElementById('id').value = values.id;
        document.getElementById('concepto').value = values.concepto;
        document.getElementById('monto').value = values.monto;
        if(values.beca.indexOf("glyphicon-ok")){
        	document.getElementById('cbBeca').checked = true;
        }else{
        	document.getElementById('cbBeca').checked = false;
        }
        
        if(values.generaAdeudo.indexOf("glyphicon-ok")){
        	document.getElementById('cbGeneraAdeudo').checked = true;
        }else{
        	document.getElementById('cbGeneraAdeudo').checked = false;
        }
        
        if(values.pagoUnico.indexOf("glyphicon-ok")){
        	document.getElementById('cbPagoUnico').checked = true;
        }else{
        	document.getElementById('cbPagoUnico').checked = false;
        }
    } );
    
} );

