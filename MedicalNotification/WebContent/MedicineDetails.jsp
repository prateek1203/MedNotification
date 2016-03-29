<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Medicine Details</title>
<link rel="stylesheet" type="text/css" href="medical/welcomeDoctor.css">
<link rel="stylesheet" type="text/css"
	href="http://getbootstrap.com/dist/css/bootstrap.min.css">
<link rel="shortcut icon"
	href="http://sstatic.net/stackoverflow/img/favicon.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script src="medical/angular-material.min.js"></script>
<script src="medical/angular-route.js"></script>

<script src="medical/myApp.js" type="text/javascript"></script>
<script src="medical/myApp-route.js" type="text/javascript"></script>
</head>
<body>
<div>
					<h1>Medicines.</h1>
					<input type="hidden" ng-nodel="preId" ng-bind="preId">
					<table class="table table-striped table-bordered table-condensed"
						ng-if="preId">
						<thead>
							<tr>
								<th>Medicine Name</th>
								<th>Dose In ADay</th>
								<th>Dose Day</th>
								<th>Gap</th>
								<th>Start Date</th>
								<th>Action</th>
							</tr>
						</thead>
						<tfoot>
							<tr ng-repeat="med in medicine">
								<th>{{med.medicineName}}</th>
								<th>{{med.doseInADay}}</th>
								<th>{{med.doseDay}}</th>
								<th>{{med.gapInMedicine}}</th>
								<th>{{med.startDate}}</th>
								<th><input type="button" value="Del"
									class="forminputButton"
									ng-click="removeMedicineRow(med.medicineName,presId)"
									ng-model="remove" /></th>
							</tr>
						</tfoot>
					</table>
					<br> <input type="button" name="Add Medicine"
						value="Add Medicine" class="inputButton"
						ng-click="showMedicineDiv()" ng-model="showMedicineForm"
						ng-if="preId">
					<div ng-if="showMedicineForm" ng-cloak="">

						<table class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>Medicine Name</th>
									<th>Dose In A Day</th>
									<th>Dose Day</th>
									<th>Gap In Medicine</th>
									<th>Start Date</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th><input type="text" class="form-control"
										placeholder="Medicine Name" ng-model="medName"
										ng-bind="medName"></th>
									<th><input type="text" class="form-control"
										placeholder="How many times in a day" ng-model="dayCounts"
										ng-bind="dayCounts"></th>
									<th><input type="text" class="form-control"
										placeholder="How many days" ng-model="days" ng-nind="days"></th>
									<th><input type="text" class="form-control"
										placeholder="Gap in days" ng-model="gap" ng-bind="gap"></th>
									<th><input type="date" id="datevalue" ng-model="datevalue"
										class="form-control"></th>
								</tr>
							</tfoot>
						</table>
						<input type="hidden" ng-model="presId" ng-bind="presId"
							name="presId"> <input type="Button" value="Save"
							class="inputButton"
							ng-click="addMedicine(presId,medName,dayCounts,days,gap,datevalue)">
						<input type="Button" value="Cancel" class="inputButton"
							ng-click="clearMed()">
					</div>
				</div>
		
</body>
</html>