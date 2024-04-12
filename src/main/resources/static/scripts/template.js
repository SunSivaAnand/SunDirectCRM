$(document).ready(function() {

	$("input[name='filter']").change(function() {
		var value = $(this).val();
		//alert(value);
		var value2 = $('#tenantId').val();
		var planId= $('#planId').val();
		//alert(planId);
		if (planId === '') {
			document.getElementById("vmsg").innerHTML = "Please fill planId field";
			return false;
			
		}
		//window.location.href = "/ck/" + value2 + "?status=" + value;
		$.ajax({
			url : "/api/ck/data",
			data : {
				status : value,
				planId : planId
			},
			async : false,
			success : function(result) {
				$('#input1').val(value);
				/*if(result===""){
					
				}*/
				CKEDITOR.instances["TextArea1"].setData(result);
				$('#getData').attr("disabled", false);
			}
		});
		/*alert(window.location.href);*/
	});

	/*$( "#submit_button" ).click(function() {
		 // alert( "Handler for .click() called." );
		});*/

	$("input[name='smsfilter']").change(function() {
		var value = $(this).val();
		//alert("sms filter"+value);
		var value2 = $('#tenantId').val();
		// window.location.href = "/ck/" + value2 + "?status=" + value;
		$.ajax({
			url : "/api/ck/smsdata?status=" + value,
			async : false,
			success : function(result) {
				//alert("sms function");
				$('#input1').val(value);
				$('#TextArea1').val(result);
				/*
				 * if(result===""){ }
				 */
				//CKEDITOR.instances["TextArea1"].setData(result);
				$('#getData').attr("disabled", false);
			}
		});
		/* alert(window.location.href); */
	});

});