<div class="container">
	<div class="row">
		<div class="col-sm-4">
			<div class="panel panel-default" data-ng-if="allPrescriptions">
				<div class="panel-heading">
					<h3 class="panel-title">My Prescription</h3>
				</div>
				<div class="panel-body autoscroll" >
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Prescription details</th>
								<th>Doctor Name</th>
							</tr>
						</thead>
						<tbody>
							<tr data-ng-repeat="pres in allPrescriptions" class="">
								<td data-ng-click="showPatientMedicines(pres)"><span style="cursor:pointer;">{{pres.prescriptionDetails}}</span></td>
								<td>{{pres.Doctor}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-sm-8">
			<div class="panel panel-default" data-ng-show="medicine">
				<div class="panel-heading">
					<h3 class="panel-title">Medicines</h3>
				</div>
				<div class="panel-body autoscroll" >
					<div class="row" >
						<div class="col-lg-6 box" data-ng-repeat="med in medicine">
							<!-- START widget-->
							<div class="panel panel-default widget">
								<div class="panel-body">
									<div class="row row-table">
										<div class="col-xs-4 text-center">
											<!-- <img src="app/img/user/09.jpg" alt="Image"
												class="img-circle thumb96"> -->
										</div>
										<div class="col-xs-8">
											<h3 class="mt0">{{med.medicineName}}</h3>
											<ul class="list-unstyled">
												<li class="mb-sm"><em class="fa fa-twitter fa-fw"></em>
													Start:-{{med.startDate}}</li>
												<li class="mb-sm"><em class="fa fa-envelope fa-fw"></em>
													End:-{{med.finaldate}}</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="panel-body bg-inverse bg-primary">
									<div class="row row-table text-center">
										<div class="col-xs-4">
											<p class="m0 h3">{{med.doseInADay}}</p>
											<p class="m0 text-default">Dose In A Day</p>
										</div>
										<div class="col-xs-4">
											<p class="m0 h3">{{med.doseDay}}</p>
											<p class="m0 text-default">Dose Day</p>
										</div>
										<div class="col-xs-4">
											<p class="m0 h3">{{med.gapInMedicine}}</p>
											<p class="m0 text-default">Gap In Medicine</p>
										</div>
									</div>
								</div>
							</div>
							<!-- END widget-->
							<!-- END widget-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- <button class="inputButton" type="button"
		data-ng-click="showAllPrescription()">My Prescription</button>
	<div style="width: 60%;" data-ng-show="allPrescriptions">
		<h4 style="color: purple;">

			</h3>
	</div>
	<div style="width: 60%;" data-ng-show="medicine">
		
		<h4 style="color: purple;">

			</h3>
	</div> -->

