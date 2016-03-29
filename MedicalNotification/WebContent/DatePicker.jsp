<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="medical/stylesheet.css">
<link rel="stylesheet" type="text/css"
	href="http://getbootstrap.com/dist/css/bootstrap.min.css">
<link rel="shortcut icon"
	href="http://sstatic.net/stackoverflow/img/favicon.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>

</head>
<body>
	<div ng-app="dateApp" ng-controller="dateController">
		<input type="date" ng-model="datevalue"> 
		<input type="text" value="{{datevalue | date : 'yyyy-MM-dd'}}" />

	</div>


	

	<script>
		var dtApp = angular.module('dateApp', []);

		dtApp.controller('dateController', function($scope) {

		});
	</script>



	
</body>
</html>