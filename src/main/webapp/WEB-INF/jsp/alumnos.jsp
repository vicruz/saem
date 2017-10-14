<%@include file="includes/header.jsp"%>
<%@include file="includes/header_html.jsp"%>
<link rel="stylesheet"  href="${conPath}/public/css/alerts/jquery-impromptu.css">
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Alumnos</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">
<sec:authentication property='principal.user.rol_id' var="adminValue"/>
<div class="page-content">

	<div id="addButton">
		<div class="panel-body">
			<c:if test = "${adminValue eq 1 }">
				<button type="button" id="deactivateAlumno"
					class="btn btn-success pull-right" onclick="altaAlumnos()" >
					<span class="glyphicon glyphicon-ok-circle"></span> Alta de Alumnos
				</button>
				<button type="button" id="deactivateAlumno"
					class="btn btn-danger pull-right" onclick="bajaAlumnos()" >
					<span class="glyphicon glyphicon-ban-circle"></span> Baja de Alumnos
				</button>
			</c:if>
		
		&nbsp;&nbsp;&nbsp;
			<button type="button" id="addAlumno"
				class="btn btn-primary pull-right" data-toggle="modal"
				data-target="#myModal">
				<span class="glyphicon glyphicon-plus"></span> Agregar Alumno
			</button>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalTitle">Agregar Alumno</h4>
				</div>
				<div class="modal-body">
					<form:form modelAttribute="alumnoForm" role="form">

						<form:errors />

						<div class="form-group">
							<form:label path="apPaterno">Ap. Paterno: </form:label>
							<form:input path="apPaterno" type="apPaterno"
								class="form-control" placeholder="Apellido Paterno" />
							<form:errors cssClass="error" path="apPaterno"></form:errors>
						</div>

						<div class="form-group">
							<form:label path="apMaterno">Ap. Materno: </form:label>
							<form:input path="apMaterno" type="apMaterno"
								class="form-control" placeholder="Apellido Materno" />
							<form:errors cssClass="error" path="apMaterno"></form:errors>
						</div>

						<div class="form-group">
							<form:label path="nombre">Nombre: </form:label>
							<form:input path="nombre" type="nombre" class="form-control"
								placeholder="Nombre del Alumno" />
							<form:errors cssClass="error" path="nombre"></form:errors>
						</div>

						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<form:select class="form-control" path="gradoId" id="gradoId">
										<option value="1">Kinder 1</option>
										<option value="2">Kinder 2</option>
										<option value="3">Kinder 3</option>
										<option value="4">1° Primaria</option>
										<option value="5">2° Primaria</option>
										<option value="6">3° Primaria</option>
										<option value="7">4° Primaria</option>
										<option value="8">5° Primaria</option>
										<option value="9">6° Primaria</option>
										<option value="10">1° Secundaria</option>
										<option value="11">2° Secundaria</option>
										<option value="12">3° Secundaria</option>
									</form:select>
								</div>
							</div>

							<%-- <div class="col-md-6">
								<div class="form-group">
									<div class="checkbox-beca">
										<form:label path="becaId">Beca: </form:label>
										<form:checkbox path="becaId" />
									</div>
								</div>
							</div> --%>
						</div>

						<div class="form-group">
							<!-- <button type="submit" class="btn btn-default">Submit</button> -->
							<button type="submit" class="btn btn-primary pull-right">Guardar</button>
							<button type="button" class="btn btn-danger pull-right"	data-dismiss="modal">Cerrar</button>
						</div>
					</form:form>
				</div>
				<div class="modal-footer"></div>

			</div>
		</div>
	</div>

	<table id="alumnos" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Ap. Paterno</th>
				<th>Ap. Materno</th>
				<th>Nombre</th>
				<th>Grado</th>
				<th>Estatus</th>
				<th>Pagos</th>
				<th>Editar</th>
				<th>Activo</th>
			</tr>
		</thead>

		<tfoot>
			<tr>
				<th>#</th>
				<th>Ap. Paterno</th>
				<th>Ap. Materno</th>
				<th>Nombre</th>
				<th>Grado</th>
				<th>Estatus</th>
				<th>Pagos</th>
				<th>Editar</th>
				<th>Activo</th>
			</tr>
		</tfoot>
	</table>

</div>



<!-- <spring:message code="hello"/> -->
<%@include file="includes/footer.jsp"%>
<script type="text/javascript" src="${conPath}/public/lib/alerts/jquery-impromptu.js"></script>
<script src="${conPath}/public/lib/sae/js/alumnoTable.js"></script>
<%@include file="includes/footer_bottom.jsp"%>