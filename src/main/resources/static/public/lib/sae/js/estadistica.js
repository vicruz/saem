var elPath = $("#elPath").val();
var chartTotal;
var chartGrados;
$(document).ready(function() {
	
	//datos del chart de pagos y adeudos
	$.get(elPath + "/estadisticas/now/chart/true",//URL
			function(data, status){
				document.getElementById('lbTotalMount').innerHTML = 'Monto total: $' + data.total;
				chartTotal = new Morris.Donut({
					  element: 'totalchart',
					  data: data.data
					  //colors: data.colors
					});
			});
	
	//datos del chart de pagos por grupo
	$.get(elPath + "/estadisticas/now/chart/false",//URL
			function(data, status){
				document.getElementById('lbGruposMount').innerHTML = 'Pagos total: $' + data.total;
				chartGrados = new Morris.Donut({
					  element: 'gruposchart',
					  data: data.data
					  //colors: data.colors
					});
			});
	
} );

function actualizaCharts(){
	var dateInicio = document.getElementById('datepickerInicio').value;
	var dateFin = document.getElementById('datepickerFin').value;
	
	if(dateInicio == ''){
		alert("Error");
		return;
	}
	
	//datos del chart de pagos y adeudos
	$.post(elPath + "/estadisticas/between/chart/true",//URL
			{//datos
				inicio: dateInicio,
				fin: dateFin
			},
			function(data, status){
				document.getElementById('lbTotalMount').innerHTML = 'Monto total: $' + data.total;
				chartTotal.setData(data.data);
			});
	
	//datos del chart de pagos por grupo
	$.post(elPath + "/estadisticas/between/chart/false",//URL
			{//datos
				inicio: dateInicio,
				fin: dateFin
			},
			function(data, status){
				document.getElementById('lbGruposMount').innerHTML = 'Pagos total: $' + data.total;
				chartGrados.setData(data.data);
			});
	
}

$( function() {
    $( "#datepicker" ).datepicker({format: "yyyy-mm-dd", todayHighlight: true, minViewMode: 1, language: "es", autoclose: true});
  } );