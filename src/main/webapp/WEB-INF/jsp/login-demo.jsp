<%@include file="includes/header.jsp" %>

<div class="panel panel-primary">
	
	<div class="panel-heading">
		<h3 class="panel-title">Sign in</h3>
	</div>
	
	<div class="panel-body">
		
		<c:if test="${param.error != null}">
			<div class="alert alert-danger">
				Usuario y/o password invalido
			</div>
		</c:if>
		
		<c:if test="${param.logout != null }">
			<div class="alert alert-danger">
				La sesion se ha cerrado
			</div>
		</c:if>
		
		<form:form role="form" method="post">
		
			<div class="form-group">
				<label for="username">Usuario</label>
				<input id="username" name="username" type="text" class="form-control" placeholder="Usuario" />
				<p class="help-block">Ingresa tu Email.</p>
			</div>
			
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" />
				<form:errors cssClass="error" path="password" />
			</div>
			
			<button type="submit" class="btn btn-primary">Sign In</button>
			
		</form:form>
		
		
	</div>
	
	
</div>
	
<%@include file="includes/footer.jsp" %>