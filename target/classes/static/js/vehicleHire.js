

$('document').ready(function() {	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicleHire, status){
			$('#idEdit').val(vehicleHire.id);
			$('#ddlClientEdit').val(vehicleHire.clientid);			
			
			var dateIn = vehicleHire.dateIn.substr(0,10);
			var dateOut = vehicleHire.dateOut.substr(0,10);
			$('#dateInEdit').val(dateIn);
			$('#dateOutEdit').val(dateOut);
			
			$('#timeInEdit').val(vehicleHire.timeIn);
			$('#timeOutEdit').val(vehicleHire.timeOut);
			
			$('#ddlLocationEdit').val(vehicleHire.locationid);
			$('#priceEdit').val(vehicleHire.price);
			$('#remarksEdit').val(vehicleHire.remarks);
			$('#ddlVehicleEdit').val(vehicleHire.vehicleid);			
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicleHire, status){
			$('#idDetail').val(vehicleHire.id);
			$('#ddlClientDetail').val(vehicleHire.clientid);
			
			var dateIn = vehicleHire.dateIn.substr(0,10);
			var dateOut = vehicleHire.dateOut.substr(0,10);
			$('#dateInDetail').val(vehicleHire.dateIn);
			$('#dateOutDetail').val(vehicleHire.dateOut);
			$('#ddlLocationDetail').val(vehicleHire.locationid);
			$('#priceDetail').val(vehicleHire.price);
			$('#remarksDetail').val(vehicleHire.remarks);
			$('#ddlVehicleDetail').val(vehicleHire.vehicleid);
			$('#lastModifiedByDetail').val(vehicleHire.lastModifiedBy);
			$('#lastModifiedDateDetail').val(vehicleHire.lastModifiedDate.substr(0,19).replace("T", " "));
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