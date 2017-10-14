var elPath = $("#elPath").val();
var becaDataTable;


$(document).ready(function() {
	var urlRest= elPath +"/pagosRest/grado/"+$("#gradoId").val();
	
	gradoPagosTable = $('#gradoPagosTable').DataTable( {
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
                    { "data": "pagoId" },
                    { "data": "concepto" },
                    { "data": "monto", render: $.fn.dataTable.render.number( ',', '.', 2, '$' )  },
                    { "data": "mes" },
                    { "data": "anio" },
                    { "data": "fechaLimite",
                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    			$(nTd).html(oData.fechaLimite+"&nbsp;" +
                    					"<a href=\"#\" >" +
                    			"<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>");                    			
                        }
                    },
                    { "data": "urlBorrar",
                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    		$(nTd).html("<button type=\"button\" class=\"btn btn-link\" id=\"deletePago\"><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></button>&nbsp;&nbsp;");
                        } 
                    }
                ]
    } );
	
	//El link de modificar la fecha de pago
	$('#gradoPagosTable tbody').on( 'click', 'a', function () {
		var objTabla = $(this).parents('tr');
        var values = gradoPagosTable.row( objTabla ).data();
		console.log( values );
		var htmlText;
		
		htmlText = '<p><b>Actualizar Fecha L&iacute;mite de Pago</b></p>'+
		'<p>Fecha Limite Actual: '+values.fechaLimite.substring(0, 10)+'</br>'+
		'Fecha Limite Nueva: <input type="text" id="datepickerFL" class="datepickerFL" name="fecha_limite" data-date-format="mm/dd/yyyy" placeholder="DD-MM-AAAA"></p>';
		
		var updateFechaBox =  {
			state0: {
				title: 'Fecha',
				html: htmlText,
				buttons: { Cancelar: 0, Aceptar: 1 },
					//focus: "input[name='fname']",
					submit:function(e,v,m,f){
						console.log(f);
						if(v==0)
    						$.prompt.close()
    					
    					else{
    						//values.fechaLimite = f.fecha_limite + "&nbsp;<a href=\"#\" ><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>";
    						//alumnoPagosTable.row( objTabla ).data(values).draw();
    						if(f.fecha_limite==""){
    							$.prompt.close();
    							$.prompt("No existe fecha para actualizar",{
									title: "ERROR!"
								});
    						}
    						else{
    							$.ajax({
    								type: "POST",
    								url: elPath +"/pagosRest/grado/fechaLimite/"+values.idPagoGrado,
    								data: {
    									fechaLimite: f.fecha_limite
    								},
    								success: function( data, textStatus, jQxhr ){
    									//console.log("ajax.data: "+data);
    									values.fechaLimite = f.fecha_limite + "&nbsp;<a href=\"#\" ><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>";
    									values.urlBorrar = "<a href='#'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;"
    									gradoPagosTable.row( objTabla ).data(values).draw();
    									$.prompt("La fecha limite se ha actualizado",{
    										title: "Actualizado!"
    									});
    								},
    								error: function(  jqXHR, textStatus, errorThrown ){
    									$.prompt("Error al actualizar la fecha" + textStatus,{
    										title: "ERROR!"
    									});
    								}
    							});
    						}
    						
    					}
					}
				}
		}
		
		$.prompt(updateFechaBox,{
        	close: function(e,v,m,f){
				if(v !== undefined ){
					var str;
					$.each(f,function(i,obj){
						str = obj;
						console.log(str);
					});	
				}
			},
			classes: {
				box: '',
				fade: '',
				prompt: '',
				close: '',
				title: 'lead',
				message: '',
				buttons: '',
				button: 'btn',
				defaultButton: 'btn-primary'
			}
        }); 
		
		$( "#datepickerFL" ).datepicker({todayHighlight: true, format: "dd-mm-yyyy",weekStart: 0,language: "es",
		    daysOfWeekDisabled: "0,6",
		    autoclose: true });
		
	});
	
	
	$('#gradoPagosTable tbody').on( 'click', 'button', function () {
        var objTabla = $(this).parents('tr');
        var values = gradoPagosTable.row( objTabla ).data();
		console.log( values );
		
		$.prompt("Desea eliminar el pago '"+ values.concepto +"' de " + values.mes + " de " + values.anio + " del grado?", {
			title: "Eliminar pago?",
			buttons: { "Si": true, "No": false },
			submit: function(e,v,m,f){
				console.log("Value clicked was: "+ v);
				if(v){
					$.ajax({
						type: "POST",
						url: elPath +"/pagosRest/borrar/pago/grado/"+values.idPagoGrado,
						data: {
							fechaLimite: f.fecha_limite
						},
						success: function( data, textStatus, jQxhr ){
							console.log("ajax.data: "+data);
							
							if(data=="ok"){
								$.prompt("El pago '"+ values.concepto +"' de " + values.mes + " de " + values.anio + " se ha eliminado",{
									title: "Eliminado!"
								});
								
								gradoPagosTable.ajax.reload();
								
								
							}else{
								$.prompt(data + "\n"+ values.concepto +" de " + values.mes + " de " + values.anio,{
									title: "ERROR!"
								});
							}
							
//							values.fechaLimite = f.fecha_limite + "&nbsp;<a href=\"#\" ><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>";
//							values.urlBorrar = "<a href='#'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;"
							
							
						},
						error: function(  jqXHR, textStatus, errorThrown ){
							$.prompt("Error al eliminar el pago: " + textStatus,{
								title: "ERROR!"
							});
						}
					});
				}else{
					$.prompt.close()					
				}			
			}
		});
		
        
    } );
	
	
	
} );

$( "#datepicker" ).datepicker({format: "yyyy-mm", startView: 1, minViewMode: 1, language: "es", autoclose: true});
