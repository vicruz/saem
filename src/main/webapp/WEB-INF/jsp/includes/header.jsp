<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="conPath" value="${req.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>.: Gandhi :.</title>
	
	<!-- Css 
	<link href="${conPath}/public/css/styles.css" rel="stylesheet"> -->
	<link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/jquery-ui-1.10.4.custom.min.css">
    <!-- <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/font-awesome.min.css">-->
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/bootstrap.min.css">
    <!-- Font Awesome -->
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">

  	 <!-- Ionicons -->
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/animate.css">
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/all.css">
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/main.css">
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/style-responsive.css">
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/zabuto_calendar.min.css">
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/pace.css">
    <link type="text/css" rel="stylesheet" href="${conPath}/public/lib/kadmin/styles/jquery.news-ticker.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">

	<!-- Data tables -->
	<link rel="stylesheet" type="text/css" href="${conPath}/public/lib/datatable/css/jquery.dataTables.css">
    <link rel="stylesheet"  href="${conPath}/public/lib/datatable/css/dataTables.bootstrap.css">
    <link rel="stylesheet"  href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css">
    
   

