<div class="container">
	<h2 style="color: purple; padding-left: 40%;">Change Password</h2>
	<div style="padding-left: 30%; padding-right: 30%;">
		<form action="#" name="myForm">
			<span data-ng-bind="changePaswd" style="color: red;"></span> <br>
			<input type="password" class="input pass" data-ng-model="oldPass"
				name="oldPass" data-ng-bind="oldPass" placeholder="Old Password"
				autofocus="autofocus" required="required"> <input
				type="password" class="input pass" data-ng-model="newPass"
				name="newPass" data-ng-bind="newPass" placeholder="New Password"
				autofocus="autofocus" required="required"> <input
				type="password" class="input pass" data-ng-model="cnfPass"
				name="cnfPass" data-ng-bind="cnfPass" placeholder="New Password"
				autofocus="autofocus" required="required"> <input
				type="button" class="inputButton" value="Change Password"
				data-ng-click="changePass()">
		</form>
	</div>

</div>