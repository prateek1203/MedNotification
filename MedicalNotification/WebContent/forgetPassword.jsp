<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<link rel="shortcut icon"
	href="http://sstatic.net/stackoverflow/img/favicon.ico">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>
<script src="medical/prescriptionList.js" type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.js"></script>

</head>
<body>
<div ng-app="patientApp" ng-controller="sentMailCtrl">
		<div class="container" style="padding-left: 14%;">
			<div class="col-md-6">
				<div id="logbox">
					<form id="signup" method="post">
						<h1>Forget Password</h1>
						<br> <br>
						<center>
							<span style="color: red;" ng-bind="responseMsg"></span>
						</center>
						<select name="usertype" ng-model="usertype" class="input pass">
							<option value="Doctor">Doctor</option>
							<option value="Patient">Patient</option>
						</select>
						 <input type="email" class="input pass" placeholder="Enter Email" data-ng-model="email" autofocus required>
						<input type="submit"
							value="Submit" class="inputButton" data-ng-click="sendMail()" />
							<a href="login.jsp">Click here for Login</a>
					</form>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>