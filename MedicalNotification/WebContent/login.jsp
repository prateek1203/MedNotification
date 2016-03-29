<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="medical/angular.js"></script>
<script src="medical/angular-route.min.js"></script>
<link rel="shortcut icon"
	href="http://sstatic.net/stackoverflow/img/favicon.ico">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="medical/jquery.min.js"></script>
<script src="medical/bootstrap.min.js"></script>
<script src="medical/myApp.js" type="text/javascript"></script>
</head>
<body>
	<div ng-app="myApp" ng-controller="validateCtrl">
		<div style="padding-left: 14%; padding-top: 30px;">
			<div class="col-md-6">
				<div id="logbox">
					<form id="signup" method="post" ng-submit="getDataFromServer()">
						<h1>Sign in Here</h1>
						<br> <br>
						<center>
							<span style="color: red;" ng-bind="validUser"></span>
						</center>
						<select name="usertype" ng-model="usertype" class="input pass">
							<option value="Doctor">Doctor</option>
							<option value="Patient">Patient</option>
						</select> <input name="email" type="email" placeholder="Email address"
							class="input pass" ng-model="email" name="email" autofocous
							required /> <input name="password" type="password"
							placeholder="Enter password" ng-model="password"
							required="required" class="input pass" /> <input type="submit"
							value="Sign In" class="inputButton" />
					</form>
					<center>
						<a href="signupform.jsp">Create an Account</a><br>
						<!-- <a href="forgetPassword.jsp">Forgot Password</a> -->
					</center>
				</div>
			</div>
		</div>
	</div>

</body>
</html>