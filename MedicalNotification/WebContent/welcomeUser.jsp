<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<!-- <link rel="stylesheet" type="text/css" href="medical/stylesheet.css"> -->
<link rel="stylesheet" type="text/css"
	href="http://getbootstrap.com/dist/css/bootstrap.min.css">
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
	<div class="container" ng-app="patientApp" ng-controller="patientCntrl">
		<%
			if (null == session.getAttribute("User") || session.getAttribute("User").equals(" ")) {
				out.print("u r nt logged in");
		%>
		<a href="login.jsp"> login here <%
			} else {
		%>
			<div style="float: left; width: 50%; color: blue;">welcome:-${User}
			
			</div>
			
			<div
				style="width: 40%; height: 100%; float: left; padding-right: 5%;">
				<h2>My Precriptions</h2>
				
				<button class="btn btn-primary btn-sm" type="button"
					ng-click="showAllPrescription()">Prescriptions</button>
				<br> <br>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Prescription Id</th>
							<th>Prescription details</th>
							<th>Doctor Name</th>
						</tr>
					</thead>
					<tfoot>
						<tr ng-repeat="pres in allPrescriptions">
							<th ng-click="showPatientMedicines(pres)"><a
								ng-href="#/{{y.prescriptioId}}">{{pres.prescriptioId}}</th>
							<th>{{pres.prescriptionDetails}}</th>
							<th>{{pres.Doctor}}</th>
						</tr>
					</tfoot>
				</table>
			</div>
			<div
				style="width: 60%; height: 100%; float: right; padding-left: 3%;">
				<h1>Medicines</h1>
				<br> <br>
				<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Medicine Name</th>
						<th>Dose In ADay</th>
						<th>Dose Day</th>
						<th>Gap In Medicine</th>
						<th>Start Date</th>
						<th>End Date</th>

					</tr>
					</thead>
					<tfoot>
					<tr ng-repeat="med in medicine">
						<th>{{med.medicineName}}</th>
						<th>{{med.doseInADay}}</th>
						<th>{{med.doseDay}}</th>
						<th>{{med.gapInMedicine}}</th>
						<th>{{med.startDate}}</th>
						<th>{{med.finaldate}}</th>
					</tr>
					</tfoot>
				</table>
			</div> <%
 	}
 %>
	</div>
</body>
</html>