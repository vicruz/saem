var alumnosTable;
$(document).ready(function() {
	var elPath = $("#elPath").val();
    alumnosTable = $('#alumnos').DataTable( {
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
    	        	"url": elPath + "/alumnoRest",
    	        	"type": "POST"
    	        },
    	        "columns": [
    	                    { "data": "id" },
    	                    { "data": "apPaterno" },
    	                    { "data": "apMaterno" },
    	                    { "data": "nombre" },
    	                    { "data": "grado" },
    	                    { "data": "semaforo" },
    	                    { "data": "url",
    	                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
    	                            $(nTd).html("<a href='"+elPath+oData.url+"'><span class=\"glyphicon glyphicon-credit-card\" aria-hidden=\"true\"></span>&nbsp;pagos</a>");
    	                        }
    	                    },
    	                    { "data": "urlEditar",
    	                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
    	                            $(nTd).html("<a href='"+elPath+oData.urlEditar+"'><span class=\"glyphicon glyphicon-edit\" aria-hidden=\"true\"></span>&nbsp;Editar</a>");
    	                        }
    	                    },
    	                    { "data": "activo" }
    	                ]
    	    } );
    	} );

/* Evento que da de baja a todos los alumnos registrados */
function bajaAlumnos(){
	console.log("bajaAlumnos");
	var elPath = $("#elPath").val();
	$.prompt("Desea dar de baja a todos los alumnos?", {
		title: "Baja Alumnos",
		buttons: { "SI": true, "NO": false },
		submit: function(e,v,m,f){
			if(v){
				 $.ajax({
					 url: elPath +"/alumnoRest/baja",
					 type: "POST",
					 success: function( data, textStatus, jQxhr ){
        					console.log("ajax.data: "+data);
        					alumnosTable.ajax.reload();
        					$.prompt("Los alumnos se han dado de baja",{
        						title: "Actualizado!"
        					});
        			}
        		});
			}
		}
	});
}

/* Evento que da de baja a todos los alumnos registrados */
function altaAlumnos(){
	var elPath = $("#elPath").val();
	$.prompt("Desea dar de alta a todos los alumnos?", {
		title: "Alta Alumnos",
		buttons: { "SI": true, "NO": false },
		submit: function(e,v,m,f){
			if(v){
				 $.ajax({
					 url: elPath +"/alumnoRest/alta",
					 type: "POST",
					 success: function( data, textStatus, jQxhr ){
        					console.log("ajax.data: "+data);
        					alumnosTable.ajax.reload();
        					$.prompt("Los alumnos se han dado de alta",{
        						title: "Actualizado!"
        					});
        			}
        		});
			}
		}
	});
}