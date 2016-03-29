<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PrescriptionDetails</title>
<link rel="stylesheet" type="text/css" href="css/welcomeDoctor.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
<script src="http://code.angularjs.org/1.2.0-rc.2/angular-route.min.js"></script>
<link rel="shortcut icon"
	href="http://sstatic.net/stackoverflow/img/favicon.ico">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="medical/med.js" type="text/javascript"></script>
</head>
<body>
<%
		if (null == session.getAttribute("User") || session.getAttribute("User").equals(" ")) {
			out.print("u r nt logged in");
	%>
	<a data-ng-href="login.jsp"> login here </a>
	<%
		} else {
	%>
	<div  ng-app="myPresApp">
		
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" >${User}</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a class="dropdown-toggle" data-toggle="dropdown" href="#/home">Home</a></li>
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span>
							LogOut</a></li>
				</ul>
			</div>
		</div>
	</nav>
		<div ng-view></div>
	</div>
	<%} %>
</body>
</html>