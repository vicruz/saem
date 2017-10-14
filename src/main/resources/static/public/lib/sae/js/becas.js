var elPath = $("#elPath").val();
var becaDataTable;
var descuentoDataTable;

var buttonBaja = "<button type=\"button\" id=\"deactivateAlumno\" class=\"btn btn-danger pull-right\ " +
		"onclick=\"bajaAlumnos()\" > <span class=\"glyphicon glyphicon-ban-circle\"></span> Baja</button>;";
var buttonAlta = "<button type=\"button\" id=\"deactivateAlumno\" class=\"btn btn-success pull-right\" " +
		"onclick=\"altaAlumnos()\" > <span class=\"glyphicon glyphicon-ok-circle\"></span> Alta</button>";

$(document).ready(function() {
	var urlRest= elPath +"/beca/alumno/"+$("#id").val();
	var urlRestDescuento= elPath +"/descuento/alumno/"+$("#id").val();
	
	becaDataTable = $('#becasTable').DataTable( {
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
                    { "data": "porcentaje" },
                    { "data": "inicio" },
                    { "data": "fin" },
                    { "data": "urlBorrar",
                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    		$(nTd).html("<a href='#'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;");
                        } 
                    }
                ]
    } );
	
	//Evento de eliminar beca
	$('#becasTable tbody').on( 'click', 'a', function () {
    	var objTabla = $(this).parents('tr');
        var values = becaDataTable.row( objTabla ).data();
        var idx = becaDataTable.row( objTabla ).index();
        console.log(values);
        
        $.prompt("Desea eliminar la beca del alumno?", {
    		title: "Eliminar Beca",
    		buttons: { "SI": true, "NO": false },
    		submit: function(e,v,m,f){
    			if(v){
    				 $.ajax({
    					 url: elPath + values.urlBorrar,
    					 type: "GET",
    					 success: function( data, textStatus, jQxhr ){
            					console.log("becaDataTable: "+data);
            					becaDataTable.ajax.reload();
            					$.prompt("La beca se ha eliminado",{
            						title: "Actualizado!"
            					});
            			}
            		});
    			}
    		}
    	});
        
        
    } );
	
	
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
        	"url": urlRestDescuento,
        	"type": "GET"
        },
        "columns": [
                    { "data": "id" },
                    { "data": "descuento" },
                    { "data": "inicio" },
                    { "data": "fin" },
                    { "data": "urlBorrar",
                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    		$(nTd).html("<a href='#'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;");
                        } 
                    }
                ]
    } );
	
	//Evento de eliminar descuento
	$('#descuentosTable tbody').on( 'click', 'a', function () {
    	var objTabla = $(this).parents('tr');
        var values = descuentoDataTable.row( objTabla ).data();
        var idx = descuentoDataTable.row( objTabla ).index();
        console.log(values);
        
        $.prompt("Desea eliminar el descuento del alumno?", {
    		title: "Eliminar Descuento",
    		buttons: { "SI": true, "NO": false },
    		submit: function(e,v,m,f){
    			if(v){
    				 $.ajax({
    					 url: elPath + values.urlBorrar,
    					 type: "GET",
    					 success: function( data, textStatus, jQxhr ){
            					console.log("descuentoDataTable: "+data);
            					descuentoDataTable.ajax.reload();
            					$.prompt("El descuento se ha eliminado",{
            						title: "Actualizado!"
            					});
            			}
            		});
    			}
    		}
    	});
        
        
    } );
		
} );


$( function() {
    $( "#datepicker" ).datepicker({format: "dd-mm-yyyy", todayHighlight: true, language: "es", autoclose: true, weekStart: 0});
  } );

$( function() {
    $( "#datepickerD" ).datepicker({format: "dd-mm-yyyy", todayHighlight: true, language: "es", autoclose: true, weekStart: 0});
  } );

/* Evento que da de baja a todos los alumnos registrados */
function bajaAlumnos(){
	$.prompt("Desea dar de baja al alumno?", {
		title: "Baja Alumno",
		buttons: { "SI": true, "NO": false },
		submit: function(e,v,m,f){
			if(v){
				 $.ajax({
					 url: elPath +"/alumnoRest/bajaById/"+$("#id").val(),
					 type: "GET",
					 success: function( data, textStatus, jQxhr ){
        					console.log("ajax.data: "+data);
        					$('#divAltaBaja').html(buttonAlta);
        					$.prompt("El alumno se han dado de baja",{
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
	$.prompt("Desea dar de alta al alumno?", {
		title: "Alta Alumno",
		buttons: { "SI": true, "NO": false },
		submit: function(e,v,m,f){
			if(v){
				 $.ajax({
					 url: elPath +"/alumnoRest/altaById/"+$("#id").val(),
					 type: "GET",
					 success: function( data, textStatus, jQxhr ){
        					console.log("ajax.data: "+data);
        					$('#divAltaBaja').html(buttonBaja);
        					$.prompt("El alumno se ha dado de alta",{
        						title: "Actualizado!"
        					});
        			}
        		});
			}
		}
	});
}

function gotToPayments(){
	var elPath = $("#elPath").val();
	var url = elPath + "/alumnos/"+$("#id").val()+"/pagos";
	window.open(url,"_self");
}