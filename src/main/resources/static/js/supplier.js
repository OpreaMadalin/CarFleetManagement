
$('document').ready(function() {
	
	$('.table #editButton').on('click',function(event){
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(supplier, status){
			$('#txtAddressEdit').val(supplier.address);
			$('#txtCityEdit').val(supplier.city);
			$('#ddlCountryEdit').val(supplier.countryid);
			$('#txtDetailsEdit').val(supplier.details);
			$('#txtEmailEdit').val(supplier.email);
			$('#txtIdEdit').val(supplier.id);
			$('#txtMobileEdit').val(supplier.mobile);
			$('#txtNameEdit').val(supplier.name);	
			$('#txtPhoneEdit').val(supplier.phone);			
			$('#ddlStateEdit').val(supplier.stateid);	
			$('#txtWebsiteEdit').val(supplier.website);
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(supplier, status){
    			$('#txtIdDetail').val(supplier.id);
    			$('#txtNameDetail').val(supplier.name);
    			$('#txtDetailsDetail').val(supplier.details);
    			$('#txtWebsiteDetail').val(supplier.website);
				$('#txtAddressDetail').val(supplier.address);
    			$('#ddlStateDetail').val(supplier.stateid);
    			$('#ddlCountryDetail').val(supplier.countryid);
    			$('#txtCityDetail').val(supplier.city);
    			$('#txtPhoneDetail').val(supplier.phone);
    			$('#txtMobileDetail').val(supplier.mobile);
    			$('#txtEmailDetail').val(supplier.email);
//			$('#lastModifiedByDetails').val(country.lastModifiedBy);
//			$('#lastModifiedDateDetails').val(country.lastModifiedDate.substr(0,19).replace("T", " "));
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