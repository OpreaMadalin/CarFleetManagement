
$('document').ready(function() {
	
	$('.table #editButton').on('click',function(event){
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(contact, status){
			$('#txtEmailEdit').val(contact.email);
			$('#txtFirstnameEdit').val(contact.firstname);
			$('#txtIdEdit').val(contact.id);
			$('#txtLastnameEdit').val(contact.lastname);	
			$('#txtMobileEdit').val(contact.mobile);
			$('#txtPhoneEdit').val(contact.phone);			
			$('#txtRemarksEdit').val(contact.remarks);
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(contact, status){
			$('#txtEmailDetail').val(contact.email);
        	$('#txtFirstnameDetail').val(contact.firstname);
        	$('#txtIdDetail').val(contact.id);
        	$('#txtLastnameDetail').val(contact.lastname);
        	$('#txtMobileDetail').val(contact.mobile);
        	$('#txtPhoneDetail').val(contact.phone);
        	$('#txtRemarksDetail').val(contact.remarks);
			$('#lastModifiedByDetails').val(country.lastModifiedBy);
			$('#lastModifiedDateDetails').val(country.lastModifiedDate.substr(0,19).replace("T", " "));
		});			
		$('#detailModal').modal();
	});	
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});	
});