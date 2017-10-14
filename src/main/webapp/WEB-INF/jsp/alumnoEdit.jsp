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
<sec:authentication property='principal.user.rol_id' var="adminValue"/>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Alumno</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">

	<form:form modelAttribute="alumnoForm" role="form">
	
		<input type="hidden" name="id" id="id"
					value='<c:out value="${alumnoForm.id}" />' />
		<input type="hidden" name="activo" id="activo"
					value='<c:out value="${alumnoForm.activo}" />' />
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Alumno</h3>
			</div>
			<div class="panel-body">
				<div>
					<%-- <form:hidden path="id" id="id"/> --%>
					<form:errors />
					
					<dl class="dl-horizontal">
			
						<dt>Ap. Paterno:</dt>
						<dd>
							<form:input path="apPaterno" type="apPaterno" class="form-control" placeholder="Apellido Paterno" />
							<form:errors cssClass="error" path="apPaterno"></form:errors>
						</dd>
						<br/>
						<dt>Ap. Materno:</dt>
						<dd>
							<form:input path="apMaterno" type="apMaterno" class="form-control" placeholder="Apellido Materno" />
							<form:errors cssClass="error" path="apMaterno"></form:errors>
						</dd>
						<br/>
						<dt>Nombre:</dt>
						<dd>
							<form:input path="nombre" type="nombre" class="form-control" placeholder="Nombre del Alumno" />
							<form:errors cssClass="error" path="nombre"></form:errors>
						</dd>
						<br/>
						<dt>Grado:</dt>
						<dd>
							<form:select class="form-control" path="gradoId" id="gradoId">
								<c:choose>
									<c:when test="${alumnoForm.gradoId==1}"> <option selected="selected" value="1">Kinder 1</option> </c:when>
									<c:otherwise> <option value="1">Kinder 1</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==2}"> <option selected="selected" value="2">Kinder 2</option> </c:when>
									<c:otherwise> <option value="2">Kinder 2</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==3}"> <option selected="selected" value="3">Kinder 3</option> </c:when>
									<c:otherwise> <option value="3">Kinder 3</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==4}"> <option selected="selected" value="4">1° Primaria</option> </c:when>
									<c:otherwise> <option value="4">1° Primaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==5}"> <option selected="selected" value="5">2° Primaria</option> </c:when>
									<c:otherwise> <option value="5">2° Primaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==6}"> <option selected="selected" value="6">3° Primaria</option> </c:when>
									<c:otherwise> <option value="6">3° Primaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==7}"> <option selected="selected" value="7">4° Primaria</option> </c:when>
									<c:otherwise> <option value="7">4° Primaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==8}"> <option selected="selected" value="8">5° Primaria</option> </c:when>
									<c:otherwise> <option value="8">5° Primaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==9}"> <option selected="selected" value="9">6° Primaria</option> </c:when>
									<c:otherwise> <option value="9">6° Primaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==10}"> <option selected="selected" value="10">1° Secundaria</option> </c:when>
									<c:otherwise> <option value="10">1° Secundaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==11}"> <option selected="selected" value="11">2° Secundaria</option> </c:when>
									<c:otherwise> <option value="1">2° Secundaria</option> </c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${alumnoForm.gradoId==12}"> <option selected="selected" value="12">3° Secundaria</option> </c:when>
									<c:otherwise> <option value="12">3° Secundaria</option> </c:otherwise>
								</c:choose>
							</form:select>
						</dd>
					</dl>
				</div>
				<div align="right">
					<button type="submit" class="btn btn-primary pull-right" >
						 Guardar
					</button>
					<c:if test = "${adminValue eq 1 }">
						<span id="divAltaBaja">
							<c:choose>
	    						<c:when test="${alumnoForm.activo==true}">
	        						<button type="button" id="deactivateAlumno"
										class="btn btn-danger pull-right" onclick="bajaAlumnos()" >
										<span class="glyphicon glyphicon-ban-circle"></span> Baja
									</button> 
	    						</c:when>    
	    						<c:otherwise>
	    							<button type="button" id="deactivateAlumno"
										class="btn btn-success pull-right" onclick="altaAlumnos()" >
										<span class="glyphicon glyphicon-ok-circle"></span> Alta
									</button>
	    						</c:otherwise>
							</c:choose>
						</span>
					</c:if>
				</div>
				
				<!-- link a 'Pagos' -->
	 			<div align="left">
					<a onclick="gotToPayments()" href="#">Pagos</a>
	 			</div>
			</div>
		</div>
	</form:form>
	
	<!-- ---------------------------------------------------------------------------------------------------------------------------- -->
	<!-- Agregar Beca -->
	<!-- ---------------------------------------------------------------------------------------------------------------------------- -->
	<!-- Modal -->
	<div class="modal fade" id="modalBeca" tabindex="-1" role="dialog"
		aria-labelledby="modalBecaLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Agregar Beca</h4>
				</div>
				<div class="modal-body">

					<form:form modelAttribute="becaForm" action="${conPath}/alumnos/${alumnoForm.id}/editar/beca" role="form">

						<form:errors />

						<div class="row">
							<div class="col-md-6">
								
								<div class="form-group">
									<form:label path="porcentaje">Porcentaje: </form:label>
									<form:input path="porcentaje" type="porcentaje" class="form-control" 
										onkeypress="return ((event.charCode >= 48 && event.charCode <= 57)||(event.charCode == 46))" />
								</div>
								
								<div class="input-daterange input-group" id="datepicker">
    								<input type="text" id="fechaInicio" class="form-control" name="fechaInicio" placeholder="DD-MM-AAAA" readonly/>
    								<span class="input-group-addon">A</span>
    								<input type="text" id="fechaFin" class="form-control" name="fechaFin" placeholder="DD-MM-AAAA" readonly/>
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
			<h3 class="panel-title">Beca</h3> 
			
		</div>
		<div class="panel-body">
			<button type="button" id="addBeca"
				class="btn btn-primary pull-right" data-toggle="modal"
				data-target="#modalBeca">
				<span class="glyphicon glyphicon-plus"></span> Beca
			</button>
<br/>
<br/>
			<table id="becasTable" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>%</th>
						<th>Inicio</th>
						<th>Fin</th>
						<th>Borrar</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>#</th>
						<th>%</th>
						<th>Inicio</th>
						<th>Fin</th>
						<th>Borrar</th>
					</tr>
				</tfoot>
			</table>
		
		
		</div>
	</div>
	
	
	<!-- ---------------------------------------------------------------------------------------------------------------------------- -->
	<!-- Agregar Descuentos -->
	<!-- ---------------------------------------------------------------------------------------------------------------------------- -->
	<!-- Modal -->
	<div class="modal fade" id="modalDescuento" tabindex="-1" role="dialog"
		aria-labelledby="modalDescuentoLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Agregar Descuento</h4>
				</div>
				<div class="modal-body">

					<form:form modelAttribute="descuentoForm" action="${conPath}/alumnos/${alumnoForm.id}/editar/descuento" role="form">

						<form:errors />

						<div class="row">
							<div class="col-md-6">
								
								<div class="form-group">
									<form:label path="monto">Descuento: </form:label>
									<form:input path="monto" type="monto" class="form-control" 
										onkeypress="return ((event.charCode >= 48 && event.charCode <= 57)||(event.charCode == 46))" />
								</div>
								
								<div class="input-daterange input-group" id="datepickerD">
    								<input type="text" id="fechaInicio" class="form-control" name="fechaInicio" placeholder="DD-MM-AAAA" readonly/>
    								<span class="input-group-addon">A</span>
    								<input type="text" id="fechaFin" class="form-control" name="fechaFin" placeholder="DD-MM-AAAA" readonly/>
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
			<h3 class="panel-title">Descuento</h3> 
			
		</div>
		<div class="panel-body">
			<button type="button" id="addDescuento"
				class="btn btn-primary pull-right" data-toggle="modal"
				data-target="#modalDescuento">
				<span class="glyphicon glyphicon-plus"></span> Descuento
			</button>
<br/>
<br/>
			<table id="descuentosTable" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Cantidad</th>
						<th>Inicio</th>
						<th>Fin</th>
						<th>Borrar</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>#</th>
						<th>Cantidad</th>
						<th>Inicio</th>
						<th>Fin</th>
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
 	<script type="text/javascript" src="${conPath}/public/lib/sae/js/becas.js"></script>
<%@include file="includes/footer_bottom.jsp"%>