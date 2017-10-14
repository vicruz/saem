$(document).ready(function() {
	var idAlumno= $("#id").val();
	var elPath = $("#elPath").val();
	var urlRest= elPath +"/pagosRest/"+$("#id").val();
	var isAdmin= $("#isAdmin").val();
	
	/////
	var saldo= parseFloat($("#saldo").val());
	var montoPago;
	var descripcionPago;
	
	var alumnoPagosTable = $('#alumnoPagos').DataTable( {
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
			"type": "POST"
		},
		"columns": [
		            { "data": "id", visible: false, searchable: false },
		            { "data": "idAlumno", visible: false, searchable: false },
		            { "data": "concepto" },
		            { "data": "monto", render: $.fn.dataTable.render.number( ',', '.', 2, '$' ) },
		            { "data": "fechaLimite",
                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    		if(oData.estatus.includes("Pagado")){
                    			$(nTd).html(oData.fechaLimite)
                    		}else{
                    			$(nTd).html(oData.fechaLimite+"&nbsp;" +
                    					"<a href=\"#\" >" +
                    			"<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>");                    			
                    		}
                    		
                        }
		            },
		            { "data": "pago", render: $.fn.dataTable.render.number( ',', '.', 2, '$' ) },
		            { "data": "fecha" },
		            { "data": "estatus" },
		            { "data": "editar" }
		            ]
	} );
	
	function cambiaMonto($value){
		alert($value);
	}
	
	//El link de modificar la fecha de pago
	$('#alumnoPagos tbody').on( 'click', 'a', function () {
		var objTabla = $(this).parents('tr');
        var values = alumnoPagosTable.row( objTabla ).data();
		console.log( values );
		var htmlText;
		
		htmlText = '<p><b>Actualizar Fecha L&iacute;mite de Pago</b></p>'+
		'<p>Fecha Limite Actual: '+values.fechaLimite.substring(0, 10)+'</br>'+
		'Fecha Limite Nueva: <input type="text" id="datepicker" class="datepicker" name="fecha_limite" data-date-format="mm/dd/yyyy" placeholder="dd-mm-yyyy"></p>';
		
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
    								url: elPath +"/pagosRest/fechaLimite/"+values.id,
    								data: {
    									fechaLimite: f.fecha_limite
    								},
    								success: function( data, textStatus, jQxhr ){
    									console.log("ajax.data: "+data);
    									
    									if(data.estatus.includes("Pagado")){
    										values.fechaLimite = f.fecha_limite;
    									}else{
    										values.fechaLimite = f.fecha_limite + "&nbsp;<a href=\"#\" ><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>";
    									}
    									values.monto = data.monto;
    									values.estatus = data.estatus;
    									alumnoPagosTable.row( objTabla ).data(values).draw();
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
		
		$( "#datepicker" ).datepicker({todayHighlight: true, format: "dd-mm-yyyy",weekStart: 0,language: "es",
		    daysOfWeekDisabled: "0,6",
		    autoclose: true });
		
	});
	
	//Al ser ( 'click', 'button', solo responde a los eventos de los botones
	$('#alumnoPagos tbody').on( 'click', 'button', function () {
		var objTabla = $(this).parents('tr');
        var values = alumnoPagosTable.row( objTabla ).data();
        var idx = alumnoPagosTable.row( objTabla ).index();
        var htmlText;
        var ejecuta = 1;
        
        //Si ya esta pagado ya no muestra el pop up
        if(values.estatus.includes("Pagado")){
        	return true;
        }
        
        console.log(idx);
        montoPago = parseFloat(values.monto);
        descripcionPago = values.concepto;
        console.log("xx: "+montoPago+","+descripcionPago);
        montoAPagar = montoPago - values.pago;
        
        ///
        htmlText = '<p><b>'+descripcionPago+'</b></p>'+
		'<p>Monto total: $'+montoPago+'</br>'+
		'Monto por pagar: $'+montoAPagar+'</p>';
        
        if(saldo>0){
        	htmlText = htmlText + '<p>'+
        	'<input type="checkbox" name="check_saldo" id="check_saldo" value="1"> &nbsp;'+
        	'Aplicar saldo a favor ($' + saldo +')'+
        	'</p>';
        }
        
        htmlText = htmlText + 
		'<p>Monto a pagar: '+
		'<input autofocus type="text" onkeypress=\"return ((event.charCode >= 48 && event.charCode <= 57)||(event.charCode == 46))\" name="monto_pago" id="monto_pago" value="" placeholder="Monto a pagar" />' +
		'</p>';
        
        if(isAdmin==1){
        	htmlText = htmlText + 
    		'<p>Fecha de Pago: '+
    		'<input type="text" id="datepicker" class="datepicker" name="fecha_pago" id="fecha_pago" data-date-format="mm/dd/yyyy" placeholder="dd-mm-yyyy"></p>';
        }

        var updateMontoBox =  {
    			state0: {
    				title: 'Pago',
    				html: htmlText,
    				buttons: { Cancelar: 0, Pagar: 1 },
    				//focus: 0,
    				submit:function(e,v,m,f){
    					//boton cancelar
    					if(v==0)
    						$.prompt.close()
    					else{
    						//Valida que el monto de pago no sea mayor al monto a pagar
    						if(montoAPagar < f.monto_pago){
    							ejecuta = 0;
    							$.prompt("El monto de pago no puede ser mayor al monto a pagar",{
    								title: "ERROR!"
    							});
    						}
    						else{
    							//Si el checkbox de saldo esta marcado y se ha puesto un monto, 
    							//se valida que la suma de estos no sea mayor al monto por pagar
    							if((f.monto_pago!=null && f.monto_pago>0) 
    									&& f.check_saldo==1){
    								var total = parseFloat(f.monto_pago) + parseFloat(saldo);
    								if(montoAPagar < total){
    									ejecuta = 0;
    	    							$.prompt("El monto de pago no puede ser mayor al monto a pagar. \nSi el saldo cubre el monto total, no se debe poner un monto a pagar",{
    	    								title: "ERROR!"
    	    							});
    	    						}
    							}
    							
    							if(ejecuta==1){
    								//Actualiza el monto de pago
        							$.ajax({
        								type: "POST",
        								url: elPath +"/pagosRest/update/"+values.id,
        								data: {
        									pago: f.monto_pago,
        									userId: 1, //no sirve de nada
        									checked: f.check_saldo,
        									//saldo: saldo
        									fechaPago: f.fecha_pago
        								},
        								success: function( data, textStatus, jQxhr ){
        									console.log("ajax.data: "+data);
        									values.monto = data.monto;
        									values.pago = data.pago;
        									values.fecha = data.fecha;
        									values.estatus = data.estatus;
        									values.editar = data.editar;
        									alumnoPagosTable.row( objTabla ).data(values).draw();
        									document.getElementById("divSaldo").textContent= data.saldo;
        									$.prompt("El monto de pago se ha actualizado",{
        										title: "Actualizado!"
        									});
        								}
        							});	
    							}			
    						}
    						
    					} 
    				}				
    			}
    		}
        
        $.prompt(updateMontoBox,{
        	close: function(e,v,m,f){
				if(v !== undefined ){
					var str;
					$.each(f,function(i,obj){
						str = obj;
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
        
        $( "#datepicker" ).datepicker({todayHighlight: true, format: "dd-mm-yyyy",weekStart: 0,language: "es",
		    daysOfWeekDisabled: "0,6",
		    autoclose: true });
        
    } );
	
} );
