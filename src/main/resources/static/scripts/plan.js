$(document).ready(function() {
	
	$("input[name='filter']").change(function(){	
		var value = $(this).val();		
		var value2 = $('#tenantId').val();
		window.location.href = "/plans/"+value2+"?status=" +value;
		/*alert(window.location.href);*/
	});
	
/*	$('#searchForm').submit(function(event){
		event.preventDefault();
		window.location = "/plan?status="+$('label.active input').val()+"&query=" +$('#query').val();
	});*/
});