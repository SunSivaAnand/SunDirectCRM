/**
 * 
 */
$(document).ready(function() {
	$("#summary_table tbody tr").click(function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		var value = $(this).find('td:first , th:first').html();
		$("#payment_scroll").load("/bundle/"+value+"/plan");
		$("#payment_scroll").show();
		$('#summary_area').hide();
	});
});