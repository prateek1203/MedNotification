<div class="container">
	<h2 style="color:purple; padding-left: 40%;">Time Table</h2>
	<div style="padding-left: 30%; padding-right: 30%;">

		<span data-ng-bind="reply" style="color: red;"></span>
		<h3 style="color: purple;">
			<table class="table">

				<tr class="success">
					<th>BreakFast</th>
					<th><select data-ng-model="breakFastHr">
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
					</select></th>
					<th>:</th>
					<th><select data-ng-model="breakFastMin">
							<option value="00">00</option>
							<option value="15">15</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
					</select></th>
					<th>AM</th>
				</tr>
				<tr class="success">
					<th>Lunch</th>
					<th><select data-ng-model="lunchHr">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select></th>
					<th>:</th>
					<th><select data-ng-model="lunchMin">
							<option value="00">00</option>
							<option value="15">15</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
					</select></th>
					<th>PM</th>
				</tr>
				<tr class="success">
					<th>Dinner</th>
					<th><select data-ng-model="dinnerHr">
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
					</select></th>
					<th>:</th>
					<th><select data-ng-model="dinnerMin">
							<option value="00">00</option>
							<option value="15">15</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
					</select></th>
					<th>PM</th>
				</tr>

			</table>
		</h3>
		<div style="padding-right: 10%;">
			<input type="button" class="inputButton" value="Save"
				data-ng-click="updateTiming()">
		</div>

	</div>
</div>