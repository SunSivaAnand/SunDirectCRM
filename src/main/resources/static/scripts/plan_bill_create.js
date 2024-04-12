jQuery.validator.addMethod("noSpace", function(value, element) {
	return $.trim(value) != "";
}, "Space are not allowed");

$(document).ready(function() {

	
	$('#plan_bill_form').validate({
		rules : {
			paymentMode : {
				required : true
			},
			price : {
				required : true,
				number : true
			},
			duration : {
				required : true,
				number : true
			},
			billCycles : {
				required : function(element) {
					return $('input[name=isBilling]:checked').val() != 'Fixed';
				},
			},
			trialDuration : {
				required : function(element) {
					return $('input[name=isTrialType]:checked').val() === 'true';
				},
			},
			unitPrice : {
				required : function(element) {
					return $('input[name=isRefund]:checked').val() === 'true';
				},
			},
			unitDays : {
				required : function(element) {
					return $('input[name=isRefund]:checked').val() === 'true';
				},
			},
			refundValidTill : {
				required : function(element) {
					return $('input[name=isRefund]:checked').val() === 'true';
				},
			}
		},
		messages : {},
		submitHandler : function(form) {
			$('#planBillButton').prop('disabled', 'disabled');
			$('#planBillButtonLoader').show();
			form.submit();
		}
	});

	$('[data-toggle="tooltip"]').tooltip();

	
	$("input[name='autoRenew']").change(function() {
		var value = $(this).val();
		if (value == "false") {
			$('#billingCyclesDiv').hide();
			$('#intervalCycles').hide();
		} else {
			$('#billingCyclesDiv').show();
			$('#intervalCycles').show();
		}
	});
	
	$("input[name='isBilling']").change(function() {
		var value = $(this).val();
		if (value == "all") {
			$(".number_Of_billing_cycles").prop('required', false);
			$('#billing_hide').hide();
			
		} else {
			$(".number_Of_billing_cycles").prop('required', true);
			$('#billing_hide').show();
			
			
		}
	});
	
	$("input[name='isTrialType']").change(function() {
		var value = $(this).val();
		if (value == "false") {
			$(".number_of_trial_period").prop('required', false);
			$('#billing_hide_trial').hide();
		} else {
			$(".number_of_trial_period").prop('required', true);
			$('#billing_hide_trial').show();
		}
	});
	
	$("input[name='isRefund']").change(function() {
		var value = $(this).val();
		if (value == "false") {
			$(".refundRequired").prop('required', false);
			$('#refundDiv').hide();
		} else {
			$(".refundRequired").prop('required', true);
			$('#refundDiv').show();
		}
	});


});
