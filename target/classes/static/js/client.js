
$('document').ready(function(){

    $('.table #editButton').on('click',function(event){
         event.preventDefault();
         var href = $(this).attr('href');
         $.get(href, function(client, status){
            $('#txtAddressEdit').val(client.address);
            $('#txtCityEdit').val(client.city);
            $('#ddlCountryEdit').val(client.countryid);
            $('#txtDetailsEdit').val(client.details);
            $('#txtEmailEdit').val(client.email);
            $('#txtIdEdit').val(client.id);
            $('#txtMobileEdit').val(client.mobile);
            $('#txtNameEdit').val(client.name);
            $('#txtPhoneEdit').val(client.phone);
            $('#ddlStateEdit').val(client.stateid);
            $('#txtWebsiteEdit').val(client.website);
         });
        $('#editModal').modal();
    });

$('.table #detailsButton').on('click',function(event){
         event.preventDefault();
         var href = $(this).attr('href');

         $.get(href, function(client, status){
            $('#txtAddressDetail').val(client.address);
                       $('#txtCityDetail').val(client.city);
                       $('#ddlCountryDetail').val(client.countryid);
                       $('#txtDetailsDetail').val(client.details);
                       $('#txtEmailDetail').val(client.email);
                       $('#txtIdDetail').val(client.id);
                       $('#txtMobileDetail').val(client.mobile);
                       $('#txtNameDetail').val(client.name);
                       $('#txtPhoneDetail').val(client.phone);
                       $('#ddlStateDetail').val(client.stateid);
                       $('#txtWebsiteDetail').val(client.website);
         });
        $('#detailModal').modal();
    });

    $('.table #deleteButton').on('click',function(event){
        event.preventDefault();
        var href= $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();
    });

});