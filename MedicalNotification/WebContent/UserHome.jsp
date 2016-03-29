<center>
<button class="inputButton" type="button"
			data-ng-click="showAllPrescription()">My Prescription</button>
	<div style="width: 60%;" data-ng-show="allPrescriptions">

		
		<br> <br>
		<h4 style="color: purple;">
			<table class="table table-striped table-bordered table-condensed ">
				<thead>
					<tr>
						<th>Prescription Id</th>
						<th>Prescription details</th>
						<th>Doctor Name</th>
					</tr>
				</thead>
				<tfoot>
					<tr data-ng-repeat="pres in allPrescriptions" class="success">
						<th data-ng-click="showPatientMedicines(pres)"><a
							data-ng-href="#/{{y.prescriptioId}}">{{pres.prescriptioId}}</a></th>
						<th>{{pres.prescriptionDetails}}</th>
						<th>{{pres.Doctor}}</th>
					</tr>
				</tfoot>
			</table>
			</h3>
	</div>
	<div style="width: 60%;" data-ng-show="medicine">
		<h2 style="color: purple;">Medicines</h2>
		<h4 style="color: purple;">
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
					<tr data-ng-repeat="med in medicine" class="success">
						<th>{{med.medicineName}}</th>
						<th>{{med.doseInADay}}</th>
						<th>{{med.doseDay}}</th>
						<th>{{med.gapInMedicine}}</th>
						<th>{{med.startDate}}</th>
						<th>{{med.finaldate}}</th>
					</tr>
				</tfoot>
			</table>
			</h3>
	</div>
</center>