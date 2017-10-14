function cambiaMonto(){
	var selectedValue= $("#selectConcepto").val();
	var elPath = $("#elPath").val();
	var url = elPath + "/catpagos/getMonto/"+selectedValue;
	$.get(url,function(respuesta){
		$("#monto").val(respuesta);
	})
}

//$( function() {
//    $( "#datepicker" ).datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
//  } );

function cambiaAnio(){
	var elPath = $("#elPath").val();
	var url = elPath + "/catpagos/getAnio";
	$.get(url,function(respuesta){
		$("#anio").val(respuesta);
	})
}

////////////////////////////////////////
//Formulario para el reporte de pago
////////////////////////////////////////
function creaReporte(){
	
	var idAlumno = document.getElementById('id').value;
	var elPath = $("#elPath").val();
	console.log(idAlumno);
	
	var htmlText = '<p><b>Periodo de pago</b></p>' + 
		'<div class="input-daterange input-group" id="datepickerPeriodo">'+
		'<input type="text" id="datepickerInicio" class="form-control" name="start" placeholder="MM-AAAA" readonly/>'+
		'<span class="input-group-addon">A</span>'+
		'<input type="text" id="datepickerFin" class="form-control" name="end" placeholder="MM-AAAA" readonly/>'+
		'</div>';
	
	
	var updateReporte =  {
			state0: {
				title: 'Reporte de Pagos',
				html: htmlText,
				buttons: { Cancelar: 0, Aceptar: 1 },
				//focus: "input[name='fname']",
				submit:function(e,v,m,f){
					console.log(f);
					if(v==0)
						$.prompt.close()
						
						else{
							if(f.start==""){
								$.prompt.close();
								$.prompt("No existe fecha para obtener reporte",{
									title: "ERROR!"
								});
							}
							else{
								console.log(f.start);
								console.log(f.end);
								
								var link = document.createElement("a");
							    link.download = 'reporte';
							    link.href = elPath +"/reporte/alumno/pagos/"+idAlumno+"/"+f.start+"/"+f.end;
							    link.click();
								
								/*$.ajax({
									type: "GET",
									url: elPath +"/reporte/alumno/pagos/"+idAlumno+"/"+f.start+"/"+f.end,
									success: function( data, textStatus, jQxhr ){
										console.log("ok");
									},
									error: function(  jqXHR, textStatus, errorThrown ){
										$.prompt("Error al actualizar generar el reporte: " + textStatus,{
											title: "ERROR!"
										});
									}
								});*/
							}
							
						}
				}
			}
	}
	
	$.prompt(updateReporte,{
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
	
	/*$( "#datepickerPeriodo" ).datepicker({startView: 1, minViewMode: 1, todayHighlight: true, format: "dd-mm-yyyy",weekStart: 0,language: "es",
	    daysOfWeekDisabled: "0,6",
	    autoclose: true});
	*/
	/*$( "#datepickerPeriodo" ).datepicker({format: "yyyy-mm-dd", todayHighlight: true, minViewMode: 1, language: "es", autoclose: true});*/
	$( "#datepickerPeriodo" ).datepicker({startView: 1, minViewMode: 1, todayHighlight: true, autoclose: true, language: "es", format: "dd-mm-yyyy" });
}

function gotToEdit(){
	var elPath = $("#elPath").val();
	var idAlumno = document.getElementById('id').value;
	var url = elPath + "/alumnos/"+idAlumno+"/editar";
	window.open(url,"_self");
}
