var app = angular.module('myApp', [ 'ngRoute' ]);

app.config(function($routeProvider) {
	console.log("config initilized...");
	$routeProvider.when('/', {
		templateUrl : 'UserHome.jsp',
		controller : 'validateCtrl'
	}).when('/upateProfile', {
		templateUrl : 'UpdateProfile.jsp'

	}).otherwise({
		redirectTo : '/'
	})
});

app
		.controller(
				'validateCtrl',
				function($scope, $http, $location, $window) {
					console.log("controller initilised");
					$scope.user = '';
					$scope.email = ' ';
					$scope.usertype = 'Doctor';
					$scope.password = '';
					$scope.prescription = '';
					$scope.remove = '';
					$scope.prescriptionInfo = '';
					$scope.medicine = '';
					$scope.showForm = '';
					$scope.showMedicineForm = '';
					$scope.preId = '';
					$scope.message = "";
					$scope.date = new Date();
					$scope.AlertMsg = '';
					$scope.medName = 'abc';
					$scope.dayCounts = '0';
					$scope.days = '0';
					$scope.gap = '0';
					$scope.getDataFromServer = function() {
						console.log("getDatafrom server called:-");
						$http(
								{
									method : 'post',
									url : '/mednotification/check?usertype='
											+ $scope.usertype + '&email='
											+ $scope.email + '&password='
											+ $scope.password

								})
								.success(
										function(responseData, status, headers,
												config) {
											if (responseData == "Valid Patient.") {

												window.location = "User.jsp";
											} else if (responseData == "Valid Doctor.") {

												window.location = "PrescriptionDetails.jsp";

											} else
												$scope.validUser = responseData;

										})

					};

					$scope.createUserId = function() {
						$http(
								{
									method : 'POST',
									url : '/mednotification/Existence?usertype='
											+ $scope.usertype
											+ '&email='
											+ $scope.email
											+ '&password='
											+ $scope.password
											+ '&name='
											+ $scope.user
											+ '&PhoneNo='
											+ $scope.phone
								}).success(function(msg) {
							$scope.validUser = msg;
						})
					};

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

					$scope.showPatientId = function(x) {
						console.log(x.userEmail);
						$http(
								{
									method : 'POST',
									url : '/mednotification/history?userEmail='
											+ x.userEmail
								}).success(function(response) {

							$scope.prescription = response.patientIdList;
							$scope.patientEmail = x.userEmail;
							$scope.preId = '';
							$scope.patientInfo = '';
						});
					};

					$scope.addNewPrescription = function(msg) {
						console.log("addnewPrescription called");
						$http(
								{
									method : 'POST',
									url : '/mednotification/createPrescription?userEmail='
											+ $scope.patientEmail
											+ '&prescription=' + msg
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
					$scope.deletePrescription = function(x) {
						$http({
							method : 'POST',
							url : '/mednotification/history'
						}).success(function(response) {
							$scope.patientInfo = response;
						})
					};
					$scope.updatePrescription = function(msg) {
						console.log(msg);
						$http(
								{
									method : 'POST',
									url : '/mednotification/updatePrescription?userEmail='
											+ $scope.patientEmail
											+ '&prescription=' + msg

								}).success(function(response) {
							$scope.patientInfo = response;
						})
					};

					$scope.clear = function() {
						$scope.showForm = '';
					};
					$scope.clearMed = function() {
						$scope.showMedicineForm = '';
					};

					$scope.showPatientMedicines = function(y) {
						console.log(y.prescriptioId);
						$http(
								{
									method : 'POST',
									url : '/mednotification/findMedicine?prescriptionId='
											+ y.prescriptioId
								}).success(function(response) {
							$scope.medicine = response.medicineList;
							$scope.presId = y.prescriptioId;
							$scope.preId = y.prescriptioId;

						})
					};

					$scope.showPrescription = function(id) {
						console.log("show prescription clicked");
						$http(
								{
									method : 'POST',
									url : '/mednotification/PrescriptionDetails?prescriptionId='
											+ id
								}).success(function(response) {
							$scope.prescriptionInfo = response;
							$scope.prescriptionId = id;
						})
					};

					$scope.showPrescriptionForm = function() {
						$scope.showForm = 'show';
					}
					$scope.showMedicineDiv = function() {
						$scope.showMedicineForm = 'show';
					}
					$scope.addMedicine = function(presId, medName, dayCounts,
							days, gap, startDate) {
						/*
						 * console.log(presId); console.log(medName);
						 * console.log(dayCounts); console.log(days);
						 * console.log(gap);
						 */
						console.log($scope.date.getHours());

						if (startDate == undefined)
							$scope.patientInfo = "Please Enter Date";
						else if (presId == undefined)
							$scope.patientInfo = "Please Select Prescription Id";
						else {
							$http(
									{
										method : 'POST',
										url : '/mednotification/addMedicine?prescriptionId='
												+ presId
												+ '&medName='
												+ medName
												+ '&dayCounts='
												+ dayCounts
												+ '&days='
												+ days
												+ '&gap='
												+ gap
												+ '&startDate='
												+ startDate
												+'&CheckUpTime='
												+$scope.date.getHours()
									}).success(function(response) {
								$scope.patientInfo = "Medicine Added";
								$scope.medicine.push({
									'medicineId' : '',
									'prescriptioId' : presId,
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

					$scope.removeRow = function(presId) {
						console.log(presId);
						/* $window.alert("are u sure u want to delete...??"); */
						$http(
								{
									method : 'POST',
									url : '/mednotification/delete?prescriptionId='
											+ presId
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

					$scope.removeMedicineRow = function(name, id) {
						console.log(name);

						$http(
								{
									method : 'POST',
									url : '/mednotification/deleteMed?medicineName='
											+ name + '&prescriptionId=' + id
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
					}

				});
