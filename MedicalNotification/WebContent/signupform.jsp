<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUpForm</title>
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
		<div style="padding-left: 14%;">
			<div class="col-md-6">
				<div id="logbox">
					<form id="signup" method="post" ng-submit="createUserId()">
						<h1>Create an Account</h1>
						<br>
						<center>
							<span style="color: red;" ng-bind="validUser"></span>
						</center>
						<select name="usertype" ng-model="usertype" class="input pass">
							<option value="Doctor">Doctor</option>
							<option value="Patient">Patient</option>
						</select> <input type="text" placeholder="Name" class="input pass"
							ng-model="user" name="user" autofocous required /> <input
							name="email" type="email" placeholder="Email address"
							class="input pass" ng-model="email" name="email" autofocous
							required /> <input name="password" type="password"
							placeholder="Choose a password" ng-model="password"
							required="required" class="input pass" /> <input name="phone"
							type="text" placeholder="Phone No" class="input pass"
							ng-model="phone" required /> <input type="submit"
							value="Sign me up!" class="inputButton" />
					</form>
					<center>
						Already Have an Account <a href="login.jsp">Login here</a>
					</center>
				</div>
			</div>
		</div>
	</div>
</body>
</html>