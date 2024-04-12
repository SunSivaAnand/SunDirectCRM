$(document).ready(function() {

	$("input[name='filter']").change(function() {
		var value = $(this).val();
		var value2 = $('#tenantId').val();
		window.location.href = "/subscriber/" + value2 + "?searchType=" + value;
		/*alert(window.location.href);*/
	});

});