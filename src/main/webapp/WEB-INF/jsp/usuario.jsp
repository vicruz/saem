<%@include file="includes/header.jsp" %>
<%@include file="includes/header_html.jsp"%>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Usuarios</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

	<div class="page-content">
	
	<div id="addButton" >
		<div class="panel-body">
			<button type="button" id="addAlumno" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myUsuario" >
				<span class="glyphicon glyphicon-plus"></span> Agregar Usuario</button>
		</div>
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="myUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Agregar Usuario</h4>
      </div>
      <div class="modal-body">
        <form:form modelAttribute="userForm" role="form">
		
			<form:errors/>
		
			<div class="form-group">
				<form:label path="email">Correo: </form:label>
				<form:input path="email" type="email" class="form-control" placeholder="Correo Electronico"/>
				<form:errors cssClass="error" path="email"></form:errors>
			</div>
			
			<div class="form-group">
				<form:label path="name">Nombre: </form:label>
				<form:input path="name" type="name" class="form-control" placeholder="Nombre"/>
				<form:errors cssClass="error" path="name"></form:errors>
			</div>
		
			<div class="form-group">
				<form:label path="password">Password: </form:label>
				<form:input path="password" type="password" class="form-control" placeholder="Contraseña"/>
				<form:errors cssClass="error" path="password"></form:errors>
			</div>
			
			<div class="form-group" align="right">
				<form:label path="admin" >Administrador: </form:label>    			
				<form:checkbox path="admin" id="cbAdmin" name="cbAdmin"/>  
    		</div>
		
			<div class="form-group">
				<!-- <button type="submit" class="btn btn-default">Submit</button> -->
	        	<button type="submit" class="btn btn-primary pull-right">Guardar</button>
				<button type="button" class="btn btn-danger pull-right" data-dismiss="modal">Cerrar</button>

			</div>
		
		
		</form:form>
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>


<table id="usuarios" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>#</th>
                  <th>Usuario</th>
                  <th>Correo</th>
  				  <th>Acción</th>
                </tr>
                
                
                
                </thead>
               
                <tfoot>
                <tr>
                  <th>#</th>
                  <th>Usuario</th>
                  <th>Correo</th>
                  <th>Acción</th>
                </tr>
                </tfoot>
              </table>
		
	</div>
	
	

	<!-- <spring:message code="hello"/> -->
<%@include file="includes/footer.jsp" %>
<script src="${conPath}/public/lib/sae/js/userTable.js"></script>
<%@include file="includes/footer_bottom.jsp"%>