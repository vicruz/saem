$(document).ready(function() {
	var elPath = $("#elPath").val();
    var tabla = $('#grados').DataTable( {
    	        "language": {
    	        	"url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
    	        },
    	        "pageLength": 20,
    	    	"dom": "Bfrtip",
    	        "buttons": [
    	                    "excel", "pdf", "print"
    	                ],
    	    	"processing": true,
    	        //"serverSide": true,
    	        "ajax": {
    	        	"url": elPath + "/gradoRest",
    	        	"type": "POST"
    	        },
    	        "columns": [
    	                    { "data": "idGrado" },
    	                    { "data": "grado" },
    	                    { "data": "urlEditar",
    	                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
    	                            $(nTd).html("<a href='"+elPath+oData.urlEditar+"'><span class=\"glyphicon glyphicon-edit\" aria-hidden=\"true\"></span>&nbsp;Editar</a>");
    	                        }
    	                    }
    	                ]
    	    } );
    tabla.page.len(20).draw();
    	} );