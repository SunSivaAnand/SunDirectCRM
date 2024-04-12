$(document).ready(function() {
	
	
	$("input[name='filter']").change(function(){
		//alert($(this).val());
		var value2 = $('#tenantId').val();
		window.location.href = "/discounts/"+value2+"?status=" + $(this).val();	    	
	});
	
	$('#searchForm').submit(function(event){
		event.preventDefault();
		window.location = "/discounts?status="+$('label.active input').val()+"&query=" +$('#query').val();
	});
});