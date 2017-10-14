</head>
<body>
	<input type="hidden" name="isAdmin" id="isAdmin" value="<sec:authentication property='principal.user.rol_id' />">
	<div>
		<!--BEGIN THEME SETTING-->
		<div id="theme-setting">
			<div class="content-theme-setting">
				<select id="list-style" class="form-control">
					<option value="style1">Flat Squared style</option>
					<option value="style2">Flat Rounded style</option>
					<option value="style3" selected="selected">Flat Border
						style</option>
				</select>
			</div>
		</div>
		<!--END THEME SETTING-->
		<!--BEGIN BACK TO TOP-->
		<a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
		<!--END BACK TO TOP-->
		<!--BEGIN TOPBAR-->
		<div id="header-topbar-option-demo" class="page-header-topbar">
			<nav id="topbar" role="navigation" style="margin-bottom: 0;"
				data-step="3" class="navbar navbar-default navbar-static-top">
				<div class="navbar-header">
					<button type="button" data-toggle="collapse"
						data-target=".sidebar-collapse" class="navbar-toggle">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a id="logo" href="index.html" class="navbar-brand"><span
						class="fa fa-rocket"></span><span class="logo-text">Gandhi</span><span
						style="display: none" class="logo-text-icon">µ</span></a>
				</div>
				<div class="topbar-main">
					<a id="menu-toggle" href="#" class="hidden-xs"><i
						class="fa fa-bars"></i></a>
					<ul class="nav navbar navbar-top-links navbar-right mbn">
						<li class="dropdown topbar-user"><a data-hover="dropdown"
							href="#" class="dropdown-toggle">&nbsp;<span
								class="hidden-xs"><sec:authentication
										property='principal.user.usuario' /></span>&nbsp;<span class="caret"></span></a>
							<ul class="dropdown-menu dropdown-user pull-right">
								<li><c:url var="logoutUrl" value="/logout" /> <form:form
										id="logoutForm" action="${logoutUrl}" method="post"></form:form>
									<a href="#"
									onclick="document.getElementById('logoutForm').submit()"><span
										class="fa fa-key"></span> Log out</a></li>
							</ul></li>
					</ul>
				</div>
			</nav>


			<!--END TOPBAR-->
			<div id="wrapper">
				<!--BEGIN SIDEBAR MENU-->
				<nav id="sidebar" role="navigation" data-step="2" data-intro=""
					data-position="right" class="navbar-default navbar-static-side">
					<div class="sidebar-collapse menu-scroll">
						<ul id="side-menu" class="nav">

							<div class="clearfix"></div>
							<li><a href="<c:url value='/alumnos' />"><span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									<div class="icon-bg bg-orange"></div> </i><span class="menu-title">Alumnos</span></a></li>


							<div class="clearfix"></div>
							<li><a href="<c:url value='/usuario' />"><span
									class="glyphicon glyphicon-user" aria-hidden="true"></span>
									<div class="icon-bg bg-orange"></div> </i><span class="menu-title">Usuarios</span></a></li>
									
							<div class="clearfix"></div>
							<li><a href="<c:url value='/grados' />"><span class="glyphicon glyphicon-education" aria-hidden="true"></span>
									<div class="icon-bg bg-orange"></div> </i><span class="menu-title">Grados</span></a></li>

							<div class="clearfix"></div>
							<li><a href="<c:url value='/getPagos' />"><span
									class="glyphicon glyphicon-usd"></span>
									<div class="icon-bg bg-orange"></div> </i><span class="menu-title">Catalogo
										Pagos</span></a></li>
<!--
							<div class="clearfix"></div>
							<li><a href="<c:url value='/pagoGrado' />"><span
									class="glyphicon glyphicon-credit-card"></span>
									<div class="icon-bg bg-orange"></div> </i><span class="menu-title">Relaci&oacute;n
										pagos con grado</span></a></li>
-->
							<div class="clearfix"></div>
							<li><a href="<c:url value='/estadisticas' />"><span
									class="glyphicon glyphicon-list-alt"></span>
									<div class="icon-bg bg-orange"></div> </i><span class="menu-title">Estad&iacute;sticas</span></a></li>
							
							<div class="clearfix"></div>
							<li><a href="<c:url value='/reportes' />"><span
									class="glyphicon glyphicon-usd"></span>
									<div class="icon-bg bg-orange"></div> </i><span class="menu-title">Reportes</span></a></li>

						</ul>

					</div>
				</nav>


				<div id="page-wrapper">

					<c:if test="${not empty flashMessage}">
						<div class="alert alert-${flashKind} ">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							${flashMessage}

						</div>
					</c:if>