var pres = angular.module('myPresApp', [ 'ngRoute', function() {
	console.log("myPresApp module initilized..");
} ]);

pres.config(function($routeProvider) {
	console.log("myPresApp config initilized..");
	$routeProvider.when('/', {
		templateUrl : 'PatientDetail.html',
		controller : 'showPatientCtrl'
	}).when('/userEmail/:userEmail', {
		templateUrl : 'PrescriptionId.html',
		controller : 'showPatientIdCtrl'
	}).when('/id/:presId', {
		templateUrl : 'patient.html',
		controller : 'showPatientMedCtrl'
	}).when('/home', {
		templateUrl : 'PatientDetail.html',
		controller : 'showPatientCtrl'
	})

	.otherwise({
		redirectTo : '/'
	})
});

pres
		.controller(
				'showPatientCtrl',
				function($scope, $http) {
					$scope.showPatientInfo = function(PatientName) {
						console.log('showPatientInfo');
						if (PatientName == undefined) {
							$scope.alertMessage = " *Please Type Name";
						} else {
							$scope.alertMessage = '';
							$http(
									{
										method : 'POST',
										url : '/mednotification/Patient?PatientName='
												+ PatientName
									})
									.success(
											function(response) {
												$scope.AlertMsg = "This is the top 5 results.For better result please provide full name.";
												console
														.log(response.patientList);
												$scope.names = response.patientList;

											});
						}
					}
				});

pres.controller('showPatientIdCtrl', function($scope, $http, $routeParams) {
	$scope.showPatientId = function() {
		console.log($routeParams.userEmail);
		$http(
				{
					method : 'POST',
					url : '/mednotification/history?userEmail='
							+ $routeParams.userEmail
				}).success(function(response) {

			$scope.prescription = response.patientIdList;
		});
	};

	$scope.removeRow = function(presId) {
		console.log(presId);
		/* $window.alert("are u sure u want to delete...??"); */
		$http({
			method : 'POST',
			url : '/mednotification/delete?prescriptionId=' + presId
		}).success(function(response) {
			$scope.patientInfo = response;
			if (response == "Prescription removed") {
				$scope.preId = '';
				var index = -1;
				var comArr = eval($scope.prescription);
				for (var i = 0; i < comArr.length; i++) {
					if (comArr[i].prescriptioId === presId) {
						index = i;
						break;
					}
				}
				if (index === -1) {
					alert("Something gone wrong");
				}
				$scope.prescription.splice(index, 1);

			} else
				alert("Error in deletion");
		})
	};

	$scope.showPrescriptionForm = function() {
		$scope.showForm = 'show';
	};
	$scope.addNewPrescription = function(msg) {
		console.log("addnewPrescription called");
		$http(
				{
					method : 'POST',
					url : '/mednotification/createPrescription?userEmail='
							+ $routeParams.userEmail + '&prescription=' + msg
				}).success(function(response) {
			console.log(response.prescriptioId);
			$scope.prescription.push({
				'prescriptioId' : response,
				'userEmail' : $scope.patientEmail,
				'doctorEmail' : $scope.headoffice,
				'prescriptionDetails' : msg
			});
			$scope.patientInfo = "Prescription Added";
			$scope.clear();
		})
	};
	$scope.clear = function() {
		$scope.showForm = '';
	};
});

pres.controller('showPatientMedCtrl', function($scope, $http, $routeParams) {
	var date = new Date();
	$scope.showPatientMedicines = function() {
		console.log($routeParams.presId);
		$http(
				{
					method : 'POST',
					url : '/mednotification/findMedicine?prescriptionId='
							+ $routeParams.presId
				}).success(function(response) {
			$scope.medicine = response.medicineList;
		})
	};
	$scope.showMedicineDiv = function() {
		$scope.showMedicineForm = 'show';
	};

	$scope.removeMedicineRow = function(name) {
		console.log(name);

		$http(
				{
					method : 'POST',
					url : '/mednotification/deleteMed?medicineName=' + name
							+ '&prescriptionId=' + $routeParams.presId
				}).success(function(response) {
			$scope.patientInfo = response;
			if (response == "Medicine Deleted") {

				var index = -1;
				var comArr = eval($scope.medicine);
				for (var i = 0; i < comArr.length; i++) {
					if (comArr[i].medicineName === name) {
						index = i;
						break;
					}
				}
				if (index === -1) {
					alert("Something gone wrong");
				}
				$scope.medicine.splice(index, 1);

			} else
				alert("Error in deletion");
		})
	};
	$scope.clearMed = function() {
		$scope.showMedicineForm = '';
	};
	$scope.addMedicine = function(medName, dayCounts, days, gap,
			startDate) {
		/*
		 * console.log(presId); console.log(medName); console.log(dayCounts);
		 * console.log(days); console.log(gap);
		 */
		console.log(date.getHours());
		console.log($routeParams.presId);
		console.log(medName);
		console.log(dayCounts);
		console.log(days);
		console.log(gap);
		console.log(startDate);
		if (startDate == undefined)
			$scope.patientInfo = "Please Enter Date";
		else if ($routeParams.presId== undefined)
			$scope.patientInfo = "Please Select Prescription Id";
		else {
			$http(
					{
						method : 'POST',
						url : '/mednotification/addMedicine?prescriptionId='
								+ $routeParams.presId + '&medName=' + medName
								+ '&dayCounts=' + dayCounts + '&days=' + days
								+ '&gap=' + gap + '&startDate=' + startDate
								+ '&CheckUpTime=' + date.getHours()
					}).success(function(response) {
				$scope.patientInfo = "Medicine Added";
				$scope.medicine.push({
					'medicineId' : '',
					'prescriptioId' : $routeParams.presId,
					'medicineName' : medName,
					'doseInADay' : dayCounts,
					'doseDay' : days,
					'gapInMedicine' : gap,
					'startDate' : response
				});
				$scope.clearMed();

			})
		}
	};
});