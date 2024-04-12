$(document).ready(function() {
	
	
	$("input[name='filter']").change(function(){
		var value = $(this).val();		
		var value2 = $('#tenantId').val();
		window.location.href = "/coupons/"+value2+"?status=" + value;	    	
	});
	
	/*$('#searchForm').submit(function(event){
		event.preventDefault();
		window.location = "/coupon?status="+$('label.active input').val()+"&query=" +$('#query').val();
	});*/
	
});