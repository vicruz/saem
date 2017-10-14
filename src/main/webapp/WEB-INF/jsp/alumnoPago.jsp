<%@include file="includes/header.jsp"%>
<link rel="stylesheet" href="${conPath}/public/lib/datepicker/css/bootstrap-datepicker3.min.css">
<link rel="stylesheet"  href="${conPath}/public/css/alerts/jquery-impromptu.css">
<!-- Button table -->
    <style type="text/css">
    	.btn-table {
    		background-color: white;
    		border: 2px solid #4CAF50;
    		color: black;
    		font-size: 11px;
			padding: 1px 20px;
			text-align: center;
			display: inline-block;
			border-radius: 4px;
		}
		
		.btn-table:hover {
    		background-color: #4CAF50;
    		color: white;
		}
		
		.disabled-btn-table {
    		opacity: 0.6;
    		cursor: not-allowed;
		}
    </style>
<%@include file="includes/header_html.jsp"%>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Alumno</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Alumno</h3>
		</div>
		<div class="panel-body">
			<div>
				<input type="hidden" name="id" id="id"
					value='<c:out value="${alumno.id}" />' />
				<!--  -->
				<input type="hidden" name="saldo" id="saldo"
					value='<c:out value="${alumno.saldo}" />' />
				
				<dl class="dl-horizontal">
					<dt>Nombre:</dt>
					<dd>
						<c:out value="${alumno.nombre}" />
						&nbsp;
						<c:out value="${alumno.apPaterno}" />
						&nbsp;
						<c:out value="${alumno.apMaterno}" />
					</dd>

					<dt>Grado:</dt>
					<dd>
						<c:out value="${alumno.grado.name}" />
					</dd>
					
					<!--  -->
					<c:if test="${alumno.saldo>0}">
						<dt>A favor:</dt>
						<dd>
							$ 
							<span id="divSaldo">
								<c:out value="${alumno.saldo}" />  
							 </span>
						</dd>
					</c:if>
					
					<c:if test="${alumno.beca>0}">
						<dt>Beca:</dt>
						<dd>
							<c:out value="${alumno.beca}" /> %
						</dd>
					</c:if>
					
					<%-- <c:if test="${alumno.descuento}">
						<dt>Descuento:</dt>
						<dd>
								$ <c:out value="${alumno.descuento}" /> %  
						</dd>
					</c:if> --%>
					
				</dl>
			</div>
	
	 		<!-- Single button -->
	 		<div align="right">
				<div class="btn-group">
	  				<button type="button" id="reporteAlumno" class="btn btn-success pull-right" onclick="creaReporte()" 
	  				aria-haspopup="true" aria-expanded="false">
	     				<span class="glyphicon glyphicon-usd"></span> Reporte de pagos
	  				</button>
				</div>
				
				<div class="btn-group">
	  				<button type="button" id="addAlumno" class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#myModal"> <span class="glyphicon glyphicon-plus"></span> Agregar Pago
					</button>
				</div>	
	 		</div>
	 		
	 		<!-- link a 'Editar' -->
	 		<div align="left">
					<a onclick="gotToEdit()" href="#">Editar</a>
	 		</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Agregar Pago</h4>
				</div>
				<div class="modal-body">

					<form:form modelAttribute="alumnoPagoForm" role="form">

						<form:errors />

						<div class="row">
							<div class="col-md-6">
								<spring:bind path="idPagoGrado">
									<div class="form-group">
										<form:label path="idPagoGrado">Concepto: </form:label>
										<form:select id="selectConcepto" path="idPagoGrado"
											class="form-control" onchange="cambiaMonto(value)">
											<form:option value="0" label="--- Selecciona ---" />
											<form:options items="${conceptos}" />
										</form:select>
									</div>
								</spring:bind>
								<div class="form-group">
									<form:label path="monto">Monto: </form:label>
									<form:input path="monto" type="monto" class="form-control"
										readonly="true" />
								</div>
								<div class="form-group">
									<form:label path="pago">Pago: </form:label>
									<form:input path="pago" type="pago" class="form-control" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-primary pull-right">Guardar</button>
							<button type="button" class="btn btn-danger pull-right"
								data-dismiss="modal">Cerrar</button>
						</div>


					</form:form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<!-- TABLE -->
	<table id="alumnoPagos" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>idAlumno</th>
				<th>Concepto</th>
				<th>Monto a pagar</th>
				<th>Fecha L&iacute;mite</th>
				<th>Pago</th>
				<th>Fecha de pago</th>
				<th>Estatus</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>id</th>
				<th>idAlumno</th>
				<th>Concepto</th>
				<th>Monto a pagar</th>
				<th>Fecha L&iacute;mite</th>
				<th>Pago</th>
				<th>Fecha de pago</th>
				<th>Estatus</th>
				<th>Editar</th>
			</tr>
		</tfoot>
	</table>
</div>


<%@include file="includes/footer.jsp"%>
<script type="text/javascript" src="${conPath}/public/lib/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${conPath}/public/lib/datepicker/locales/bootstrap-datepicker.es.min.js"></script>
<script type="text/javascript" src="${conPath}/public/lib/alerts/jquery-impromptu.js"></script>
<script src="${conPath}/public/lib/sae/js/alumnoPagosTable.js"></script>
<script type="text/javascript" src="${conPath}/public/lib/sae/js/alumnoPagosTableJS.js"></script>
<%@include file="includes/footer_bottom.jsp"%>