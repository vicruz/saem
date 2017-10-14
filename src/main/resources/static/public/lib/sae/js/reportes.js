var elPath = $("#elPath").val();

function generaReporte(){
	var dateInicio = document.getElementById('datepickerInicio').value;
	var dateFin = document.getElementById('datepickerFin').value;
	
	if(dateInicio == ''){
		alert("Selecciona una fecha");
		return;
	}
	
	var link = document.createElement("a");
    link.download = 'x';
    link.href = elPath +"/reporte/pagos/fechas/"+dateInicio+"/"+dateFin;
    link.click();
		
}

$( function() {
	$( "#datepicker" ).datepicker({todayHighlight: true, format: "dd-mm-yyyy",weekStart: 0,language: "es",
	    daysOfWeekDisabled: "0,6",
	    autoclose: true });
  } );