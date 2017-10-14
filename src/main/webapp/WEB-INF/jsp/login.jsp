<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="es">
<head>
    <title>.: GANDHI :.</title>
    <!--<meta charset="utf-8">-->
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/icons/favicon.ico">
    <link rel="apple-touch-icon" href="images/icons/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="images/icons/favicon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="images/icons/favicon-114x114.png">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,700">
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,700,300">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/jquery-ui-1.10.4.custom.min.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/animate.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/all.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/main.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/style-responsive.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/zabuto_calendar.min.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/pace.css">
    <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/jquery.news-ticker.css">
</head>
<body>
    <div>
       
        
        
        
            
            <div id="page-wrapper">
           
                <!--BEGIN CONTENT-->
                <div class="page-content">
                
                <c:if test="${param.error != null}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
				Usuario y/o password invalido
			</div>
		</c:if>
		
		<c:if test="${param.logout != null }">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
				La sesion se ha cerrado
			</div>
		</c:if>
                
                    <div id="tab-general">
                        <div class="row mbl">
                            <div class="col-lg-12">
                                <div class="col-md-12">
                                    
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-4">
                                        
                                        
                                    </div>
<!-- ------------------------------------------------------------- -->                                    
                                    <div class="col-lg-4">
                                        <div class="panel panel-yellow">
                                            <div class="panel-heading">
                                                Inicio de sesión</div>
                                            <div class="panel-body pan">
                                            <form:form role="form" method="post" class="form-horizontal">
                                                <div class="form-body pal">
                                                    <div class="form-group">
                                                        <label for="inputName" class="col-md-3 control-label">
                                                            Usuario:</label>
                                                        <div class="col-md-9">
                                                            <div class="input-icon right">
                                                                <i class="fa fa-user"></i>
                                                                <input id="username" name="username" type="text" class="form-control" placeholder="Usuario" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="inputPassword" class="col-md-3 control-label">
                                                            Password</label>
                                                        <div class="col-md-9">
                                                            <div class="input-icon right">
                                                                <i class="fa fa-lock"></i>
                                                                <input type="password" id="password" name="password" class="form-control" placeholder="Password" />
                                                            </div>
                                                            <!-- <span class="help-block mbn"><a href="#"><small>Forgot password?</small> </a></span> -->
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-actions pal">
                                                    <div class="form-group mbn">
                                                        <div class="col-md-offset-3 col-md-6">
                                                            <!-- <a href="#" class="btn btn-primary">Register</a>&nbsp;&nbsp;  -->
                                                            <button type="submit" class="btn btn-primary">
                                                                Iniciar</button>
                                                        </div>
                                                    </div>
                                                </div>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
<!-- ------------------------------------------------------------- -->                            
                                    <div class="col-lg-4">
                                       
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--END CONTENT-->
                <!--BEGIN FOOTER-->
                <div id="footer">
                    <div class="copyright">
                        <a href="http://themifycloud.com"></a></div>
                </div>
                <!--END FOOTER-->
            </div>
            <!--END PAGE WRAPPER-->
        
    </div>
   
    
    <!--CORE JAVASCRIPT-->
    <script src="public/lib/script/main.js"></script>
    <script>        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
            m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
        ga('create', 'UA-145464-12', 'auto');
        ga('send', 'pageview');
</script>
</body>
</html>
