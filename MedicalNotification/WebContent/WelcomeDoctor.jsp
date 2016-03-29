<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="medical/welcomeDoctor.css">
<link rel="stylesheet" type="text/css"
	href="http://getbootstrap.com/dist/css/bootstrap.min.css">
<link rel="shortcut icon"
	href="http://sstatic.net/stackoverflow/img/favicon.ico">
<script type="text/javascript" src="medical/angular.js"></script>
<script type="text/javascript" src="medical/angular-route.min.js"></script>

<script src="medical/myApp.js" type="text/javascript"></script>

<!-- <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico" /> -->
</head>
<body data-ng-app="myApp" data-ng-controller="validateCtrl">
<h4 style="color: purple;">
	<%
		if (null == session.getAttribute("User") || session.getAttribute("User").equals(" ")) {
			out.print("u r nt logged in");
	%>
	<a href="login.jsp"> login here <%
		} else {
	%>
	</a>

	<div class="container" >
		<div style="float: right; font-size: 15px;">
			{{ date | date : 'fullDate'}}<font style="color: blue;" size=5px> <a href="logout.jsp">Logout</a></font>
		</div>
		<font style="color: blue;" size=5px>welcome:-</font> Dr.${User} <br>
		<br> <br>
		<form action="#" method="post" class="form-horizontal"
			name="welcomeForm">
			<div style="height: 100%; width: 40%; float: left;">
				<p>
					<span data-ng-bind="alertMessage" style="color: red;"></span><br> <input
						type="text" name="PatientName" data-ng-model="PatientName"
						placeholder="Search Patient By Name" class="input pass"
						id="inputSuccess3" aria-describedby="inputSuccess3Status" required>
					<!-- <span style="color: red" ng-show="welcomeForm.PatientName.$dirty">
						<span ng-show="welcomeForm.PatientName.$error.required">*required.</span>
					</span> -->
					<input type="button" name="findPatient" value="Find"
						ng-click="showPatientInfo(PatientName)" class="inputButton">
						<span style="color:red;" ng-bind="AlertMsg"></span>
				<h4 style="color: purple;">		
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>No.</th>
							<th>Email-Id</th>
							<th>name</th>
						</tr>
					</thead>
					<tfoot>
						<tr ng-repeat="x in names">
							<th>{{$index+1}}</th>
							<th ng-click="showPatientId(x)"><a
								ng-href="#/{{x.userEmail}}">{{ x.userEmail }}</a></th>
							<th>{{ x.name }}</th>
						</tr>
					</tfoot>
				</table></h4>


			</div>
			<div ng-if="patientEmail"
				style="height: 100%; width: 60%; float: right; padding-left: 5%; padding-right: 0%;">

				<p>
				<h1>Prescription.</h1>
				<span data-ng-bind="patientInfo" style="color: red;"></span>
				</p>

				<input type="hidden" name="patientEmail" ng-bind="patientEmail"
					ng-model="patientEmail">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Prescription Id</th>
							<th>Details</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr ng-repeat="y in prescription">

							<th ng-click="showPatientMedicines(y)"><a
								ng-href="#/{{y.prescriptioId}}">{{y.prescriptioId}}</th>
							<th>{{y.prescriptionDetails}}</th>
							<th><input type="button" value="Del" class="forminputButton"
								ng-click="removeRow(y.prescriptioId)" ng-model="remove"
								confirm="Are you sure?" confirm-title="Please verify, {{name}}?"
								confirm-ok="Yes" confirm-cancel="No" /></th>
						</tr>
					</tfoot>
				</table>
				<br> <input type="button" name="Add Prescription"
					value="Add Prescription" class="inputButton"
					ng-click="showPrescriptionForm()" ng-model="showForm">
				<div ng-if="showForm">
					<textarea class="input pass" rows="3" ng-model="message"
						name="message" ng-bind="PresMessage" value="{{message}}"></textarea>
					<input type="Button" value="Save" class="inputButton"
						ng-click="addNewPrescription(message)"> <input
						type="Button" value="Cancel" class="inputButton"
						ng-click="clear()">
				</div>
				<div nf-if="preId">
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
				</p>
			</div>
		</form>
	</div>
	<%
		}
	%>
	</h4>
</body>
</html>