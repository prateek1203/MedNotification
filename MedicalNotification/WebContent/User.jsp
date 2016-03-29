<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
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
<script src="medical/prescriptionList.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/welcomeDoctor.css">

</head>
<body data-ng-app="patientApp">
	<%
		if (null == session.getAttribute("User") || session.getAttribute("User").equals(" ")) {
			out.print("u r nt logged in");
	%>
	<a data-ng-href="login.jsp"> login here </a>
	<%
		} else {
	%>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">${User}</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">Home</a></li>
					<li><a class="dropdown-toggle" data-toggle="dropdown"
						href="#/upateProfile">Update Profile </a></li>
					<li><a class="dropdown-toggle" data-toggle="dropdown"
						href="#/setting">Change Password</a></li>
					<li><a class="dropdown-toggle" data-toggle="dropdown"
						href="#/Timing">Time Table</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span>
							LogOut</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- </header>
		</div>
	</div> -->
	<div data-ng-view></div>

	<%
		}
	%>

</body>
</html>