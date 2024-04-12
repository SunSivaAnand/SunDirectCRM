/**
 * 
 */
$(document).ready(function() {
	$("#summary_table tbody tr").click(function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		var value = $(this).find('td:first , th:first').attr('id');
		$("#payment_scroll").load("/subscriber/subscription/"+value+"/order");
		$("#payment_scroll").show();
		$('#summary_area').hide();
	});
});