
$('document').ready(function() {	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicle, status){
			var acDate = vehicle.acquisitionDate.substr(0,10);
			var regDate = vehicle.registrationDate.substr(0,10);
			$('#txtIdEdit').val(vehicle.id);
			$('#ddlVehicleTypeEdit').val(vehicle.vehicletypeid);
			$('#txtNameEdit').val(vehicle.name);
			$('#txtVehicleNumberEdit').val(vehicle.vehicleNumber);
			$('#txtRegistrationDateEdit').val(regDate);
			$('#txtDescriptionEdit').val(vehicle.description);
			$('#txtAcquisitionDateEdit').val(acDate);
			$('#ddlVehicleMakeEdit').val(vehicle.vehiclemakeid);
			$('#ddlVehicleModelEdit').val(vehicle.vehiclemodelid);
			$('#ddlLocationEdit').val(vehicle.locationid);
			$('#txtPowerEdit').val(vehicle.power);
			$('#txtFuelCapacityEdit').val(vehicle.fuelCapacity);
			$('#txtNetWeightEdit').val(vehicle.netWeight);
			$('#txtRemarksEdit').val(vehicle.remarks);
			$('#ddlEmployeeEdit').val(vehicle.employeeid);
			$('#ddlVehicleStatusEdit').val(vehicle.vehiclestatusid);
		});
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicleType, status){
			$('#idDetails').val(vehicleType.id);
			$('#descriptionDetails').val(vehicleType.description);
			$('#detailsDetails').val(vehicleType.details);
			$('#lastModifiedByDetails').val(vehicleType.lastModifiedBy);
			$('#lastModifiedDateDetails').val(vehicleType.lastModifiedDate.substr(0,19).replace("T", " "));
		});			
		$('#detailsModal').modal();		
	});	
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});	

});