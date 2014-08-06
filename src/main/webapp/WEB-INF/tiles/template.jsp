<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Flight Reservation System Created in Angular.js">
		<meta name="author" content="Sanjay Kumar">
		<link rel="icon" type="image/ico" href="assets/img/favicon.ico">
		 

		<title><tiles:insertAttribute name="title" ignore="true" /></title>

		<!-- Bootstrap core CSS -->
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="assets/css/style.css" rel="stylesheet">
		<link href="assets/css/font-awesome.min.css" rel="stylesheet">
		<link href="assets/css/semantic.min.css" rel="stylesheet">
		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js" type="text/javascript"></script>
			
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	  </head>
	<body>	
		<!-- Header Section -->
		<div class="container">
			<header>
				<tiles:insertAttribute name="header" ignore="true" />
			</header>
		</div>

		<!-- Menu Section -->	
			<tiles:insertAttribute name="menu" ignore="true" />
		
		<!-- Body Section -->
		<section>
			<div class="row">
				
				<!-- Sidebar Section  -->
				<section>
					<tiles:insertAttribute name="sidebar" ignore="true" />
				</section>

				<!-- Main Body Section  -->
				<div class="body margin-left-right"> 	
					<tiles:insertAttribute name="body" ignore="true" />
				</div>
			</div>
		</section>
		
		<!-- Footer Section  -->
		<footer>
			<tiles:insertAttribute name="footer" ignore="true" />
		</footer>

		        <!-- Bootstrap core JavaScript
    	================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="assets/js/semantic.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

	</body>

</html>