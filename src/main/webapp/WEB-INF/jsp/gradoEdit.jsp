<%@include file="includes/header.jsp"%>
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
  	<link rel="stylesheet" href="${conPath}/public/lib/datepicker/css/bootstrap-datepicker3.min.css">
<%@include file="includes/header_html.jsp"%>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Grado</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">

	<form:form modelAttribute="gradoForm" role="form">
	
		<input type="hidden" name="gradoId" id="gradoId"
					value='<c:out value="${gradoForm.gradoId}" />' />
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Grado</h3>
			</div>
			<div class="panel-body">
				<div>
					<form:errors />
					<dl class="dl-horizontal">
						<dt>Grado:</dt>
						<dd>
							<form:input path="nombre" type="nombre" class="form-control" placeholder="Grado" />
							<form:errors cssClass="error" path="nombre"></form:errors>
						</dd>
					</dl>
				</div>
				<div align="right">
					<button type="submit" class="btn btn-primary pull-right" >
						 Guardar
					</button>
				</div>
			</div>
		</div>
	</form:form>
	
	<!-- Modal -->
	<div class="modal fade" id="modalPagos" tabindex="-1" role="dialog"
		aria-labelledby="modalPagoLabel">
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

					<form:form modelAttribute="gradoPagoForm" action="${conPath}/grados/${gradoForm.gradoId}/editar/pago" role="form">

						<form:errors />

						<div class="row">
							<div class="col-md-6">
								
								<div class="form-group">
									<form:label path="pagoId">Pago: </form:label>
									<form:select id="selectConcepto" path="pagoId" class="form-control" onchange="cambiaMonto(value)">
										<form:option value="0" label="--- Selecciona ---" />
										<form:options items="${conceptos}" />
									</form:select>
								</div>
								
								<div class="form-group">
									<form:label path="fechaInicio">Periodo: </form:label><br/>
									<div class="input-daterange input-group" id="datepicker">
	    								<input type="text" id="fechaInicio" class="form-control" name="fechaInicio" placeholder="AAAA-MM" readonly/>
	    								<span class="input-group-addon">A</span>
	    								<input type="text" id="fechaFin" class="form-control" name="fechaFin" placeholder="AAAA-MM" readonly/>
									</div>
								</div>
								
								<div class="form-group">
									<form:label path="limite">D&iacute;a L&iacute;mite de pago: </form:label>
									<form:select class="form-control" path="limite" id="limite">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5" selected="selected">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
									</form:select>
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
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pagos</h3> 
		</div>
		<div class="panel-body">
			<button type="button" id="addPago"
				class="btn btn-primary pull-right" data-toggle="modal"
				data-target="#modalPagos">
				<span class="glyphicon glyphicon-plus"></span> Pago
			</button>
<br/>
<br/>
			<table id="gradoPagosTable" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Concepto</th>
						<th>Monto</th>
						<th>Mes</th>
						<th>A&ntilde;o</th>
						<th>Fecha L&iacute;mite</th>
						<th>Borrar</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>#</th>
						<th>Concepto</th>
						<th>Monto</th>
						<th>Mes</th>
						<th>A&ntilde;o</th>
						<th>Fecha L&iacute;mite</th>
						<th>Borrar</th>
					</tr>
				</tfoot>
			</table>
		
		
		</div>
	</div>
</div>


<%@include file="includes/footer.jsp"%>
	<script type="text/javascript" src="${conPath}/public/lib/datepicker/js/bootstrap-datepicker.min.js"></script>
 	<script type="text/javascript" src="${conPath}/public/lib/datepicker/locales/bootstrap-datepicker.es.min.js"></script>
 	<script type="text/javascript" src="${conPath}/public/lib/alerts/jquery-impromptu.js"></script>
 	<script type="text/javascript" src="${conPath}/public/lib/sae/js/gradoPagos.js"></script>
<%@include file="includes/footer_bottom.jsp"%>