<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<!-- Boostrap -->
	<link href="public/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Css -->
	<link href="public/css/styles.css" rel="stylesheet">
</head>
<body>
	<div class="container">
	
	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
      <sec:authorize access="isAnonymous()">
      
      	<li><a href="<c:url value='/signup' />"><span class="glyphicon glyphicon-log-in"></span> Sign up</a></li>
        <li>
        	<a href="/login">Sign in <span class="glyphicon glyphicon-log-in"></span></a>
        </li>
      
      </sec:authorize>
      
      
      <sec:authorize access="isAuthenticated()">
      	<li class="dropdown">
      		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
      			<span class="glyphicon glyphicon-user"></span>
      		</a>
      		<ul class="dropdown-menu">
      			<li>
      				<c:url var="logoutUrl" value="/logout" />
      				<form:form	id="logoutForm" action="${logoutUrl}" method="post"></form:form>
      				<a href="#" onclick="document.getElementById('logoutForm').submit()"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>
      			</li>
      		</ul>
      	</li>
      </sec:authorize>
        
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<c:if test="${not empty flashMessage}">
	<div class="alert alert-${flashKind} alert-dissmisable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true" >&times;</button>
		${flashMessage}
		
	</div>
</c:if>



<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Please sign up</h3>
	</div>
	
	<div class="panel-body">
	
		<form:form modelAttribute="signupForm" role="form">
		
			<form:errors/>
		
			<div class="form-group">
				<form:label path="email">Email: </form:label>
				<form:input path="email" type="email" class="form-control" placeholder="Ingresar Email"/>
				<form:errors cssClass="error" path="email"></form:errors>
				<p class="help-block">Ingresa un email, este sera tu usuario</p>
			</div>
		
			<div class="form-group">
				<form:label path="name">Nombre: </form:label>
				<form:input path="name" type="name" class="form-control" placeholder="Ingresar un nombre"/>
				<form:errors cssClass="error" path="name"></form:errors>
				<p class="help-block">Display name</p>
			</div>
			
			<div class="form-group">
				<form:label path="password">Password: </form:label>
				<form:input path="password" type="password" class="form-control" placeholder="Password"/>
				<form:errors cssClass="error" path="password"></form:errors>
			</div>
		
			<button type="submit" class="btn btn-default">Submit</button>
		
		</form:form>
	
	</div>

</div>
</div>
	
	<!-- JQuery -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="public/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	
</body>
</html>