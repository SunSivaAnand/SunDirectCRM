$(document).ready(function() {
	
	$('#editEndDate').click(function(){
		var datetime = $('#endDateText').text().split(" ");
		var time = datetime[1];
		var date = datetime[0].split("/");
		$('#endDateInput').val(date[2] + "-" + date[0] + "-" + date[1] + "T" + time);
		$('#endDateText').css("visibility", "hidden");
		$('#endDateText').css("display", "none");
		$('#editEndDate').css("visibility", "hidden");
		$('#editEndDate').css("display", "none");
		$('#endDateInput').css("visibility", "visible");
		$('#endDateInput').css("display", "inline");
		$('#updateEndDate').css("visibility", "visible");
		$('#updateEndDate').css("display", "inline");
		$('#endDateInput').focus();
	});
	
	$('#updateEndDate').click(function() {
		sbrsubscriberId = $('#subscriberId').val();
		subscriptionId = $('#subscriptionId').val();
		validTill = $('#endDateInput').val();
		document.location.href = "/subscriber/" + sbrsubscriberId + "/subscription/" + subscriptionId +"/changeValidity/" + validTill;
	  });
});