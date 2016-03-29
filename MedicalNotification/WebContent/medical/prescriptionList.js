var patient = angular.module("patientApp", [ 'ngRoute' ]);

patient.config(function($routeProvider) {
	console.log("config initilized...");
	$routeProvider.when('/', {
		templateUrl : 'UserHome.jsp',
		controller : 'patientCntrl'
	}).when('/upateProfile', {
		templateUrl : 'UpdateProfile.jsp',
		controller : 'updateCtrl'
	}).when('/setting', {
		templateUrl : 'setting.jsp',
		controller : 'changePassCtrl'
	}).when('/Timing', {
		templateUrl : 'doesTiming.jsp',
		controller : 'doseTimingCtrl'

	}).otherwise({
		redirectTo : '/'
	})
});

patient.controller("patientCntrl", function($scope, $http, $location) {
	$scope.allPrescriptions = '';
	$scope.showAllPrescription = function() {

		$http({
			method : 'POST',
			url : '/mednotification/patientPrescription'
		}).success(function(response) {
			console.log(response);
			$scope.allPrescriptions = response.prescriptionList;
		})
	};

	$scope.showPatientMedicines = function(y) {
		console.log(y.prescriptioId);
		$http(
				{
					method : 'POST',
					url : '/mednotification/findMedicine?prescriptionId='
							+ y.prescriptioId
				}).success(function(response) {
			console.log(response);
			$scope.medicine = response.medicineList;

		})
	};
});

patient.controller('updateCtrl', function($scope, $http) {
	$scope.init = function() {
		$http({
			method : 'POST',
			url : '/mednotification/print'

		}).success(function(response) {
			$scope.userName = response.patientInfo.name;
			$scope.userPhNo = response.patientInfo.PhoneNo;
		})
	};

	$scope.updateProfile = function() {
		console.log("updateCtrl initilized..");
		$http(
				{
					method : 'POST',
					url : '/mednotification/update?userName=' + $scope.userName
							+ '&userPhNo=' + $scope.userPhNo

				}).success(function(response) {
			$scope.updateMsg = response;
			$scope.userName = '';
			$scope.userPhNo = '';
		})
	};
});

patient.controller('changePassCtrl', function($scope, $http) {
	$scope.changePass = function() {
		console.log("Change Password Ctrl initilized..");
		if($scope.oldPass==undefined || $scope.newPass==undefined || $scope.cnfPass==undefined)
			{
			$scope.changePaswd="All fields are Mandetory";
			}
		else if ($scope.newPass == $scope.cnfPass) {
			$http(
					{
						method : 'POST',
						url : '/mednotification/updatePass?newPass='
								+ $scope.newPass + '&oldPass=' + $scope.oldPass
					}).success(function(response) {
				$scope.changePaswd = response;
				/*$scope.newPass = '';
				$scope.oldPass = '';
				$scope.cnfPass = '';*/
			})
		} else {
			$scope.changePaswd = "Password & Confirm Password Mismatched.";
		}
	};
});

/*
 * patient.controller('sentMailCtrl', function($scope, $http) { $scope.usertype =
 * 'Doctor'; $scope.sendMail = function() { console.log("sentMailCtrl
 * initilized..."); $http( { method : 'POST', url :
 * '/mednotification/sendMail?email=' + $scope.email + '&usertype=' +
 * $scope.usertype }).success(function(response) { $scope.responseMsg =
 * response; }) } });
 */

patient.controller('doseTimingCtrl', function($scope, $http) {
	$scope.breakFastHr = '7';
	$scope.breakFastMin = '00';
	$scope.lunchHr = '1';
	$scope.lunchMin = '00';
	$scope.dinnerHr = '7';
	$scope.dinnerMin = '00';
	$scope.updateTiming = function() {
		console.log("doseTimingCtrl initilized...");
		var breakFast = $scope.breakFastHr + ":" + $scope.breakFastMin + "AM";
		var lunch = $scope.lunchHr + ":" + $scope.lunchMin + "PM";
		var dinner = $scope.dinnerHr + ":" + $scope.dinnerMin + "PM";
		console.log(dinner);
		$http(
				{
					method : 'POST',
					url : '/mednotification/updateDose?breakFast=' + breakFast
							+ '&lunch=' + lunch + '&dinner=' + dinner
				}).success(function(response) {
			$scope.reply = response;
		})
	};
});
