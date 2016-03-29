<div class="container" data-ng-init="init()">
	<h2 style="color:purple; padding-left: 40%;">Update profile</h2>
	<div style="padding-left: 30%;padding-right:30%;">
		<span style="color: red;" data-ng-bind="updateMsg"></span><br><h3 style="color: purple;">Name:-</h3> <input
			type="text" class="input pass" placeholder="Enter Name"
			data-ng-model="userName" data-ng-bing="userName" autofocus required><h3 style="color: purple;">Phone No:-</h3><input
			type="text" class="input pass" placeholder="Enter Phone No"
			data-ng-model="userPhNo" data-ng-bind="userPhNo" required> <input type="button"
			class="inputButton" value="Update Profile"
			data-ng-click="updateProfile()">
	</div>
</div>