<!-- Su controller se encuentra en EstadisticasController -->
<%@include file="includes/header.jsp"%>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
	<link rel="stylesheet" href="${conPath}/public/lib/datepicker/css/bootstrap-datepicker3.min.css">
<%@include file="includes/header_html.jsp"%>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Reportes</div>
	</div>
	<div class="clearfix"></div>
</div>


<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Periodo</h3>
		</div>
		
		<div class="panel-body">
			<div class="input-daterange input-group" id="datepicker">
    			<input type="text" id="datepickerInicio" class="form-control" name="start" placeholder="DD-MM-AAAA" readonly/>
    			<span class="input-group-addon">A</span>
    			<input type="text" id="datepickerFin" class="form-control" name="end" placeholder="DD-MM-AAAA" readonly/>
			</div>
			<button type="button" onclick="generaReporte()" class="btn btn-info pull-right">Generar Reporte</button>
		</div>
	</div>

</div>


<!-- <spring:message code="hello"/> -->
<%@include file="includes/footer.jsp"%>
 	<script type="text/javascript" src="${conPath}/public/lib/datepicker/js/bootstrap-datepicker.min.js"></script>
 	<script type="text/javascript" src="${conPath}/public/lib/datepicker/locales/bootstrap-datepicker.es.min.js"></script>
 	<script type="text/javascript" src="${conPath}/public/lib/sae/js/reportes.js"></script>
<%@include file="includes/footer_bottom.jsp"%>