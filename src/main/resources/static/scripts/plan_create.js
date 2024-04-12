jQuery.validator.addMethod("noSpace", function(value, element) {
	return $.trim(value) != "";
}, "Space are not allowed");

$(document).ready(function() {

	$.validator.setDefaults({
		ignore : ":hidden:not(select)"
	});
	if ($("select.plan_bundle_select").length > 0) {
		$("select.plan_bundle_select").each(function() {
			if ($(this).attr('required') !== undefined) {
				$(this).on("change", function() {
					$(this).valid();
				});
			}
		});
	}

	if ($('input[name=isPlatformAll]:checked').val() === 'Fixed') {
		$.validator.setDefaults({
			ignore : ":hidden:not(select)"
		});
		if ($("select.plan_platform").length > 0) {
			$("select.plan_platform").each(function() {
				if ($(this).attr('required') !== undefined) {
					$(this).on("change", function() {
						$(this).valid();
					});
				}
			});
		}
	}

	$('#plan_form').validate({
		rules : {
			name : {
				required : true,
				minlength : 3,
				maxlength : 150,
				noSpace : true
			},
			description : {
				required : true,
				minlength : 3,
				maxlength : 250,
				noSpace : true
			},
			devicesAllowed : {
				required : false,
				minlength : 1,
				number : true
			},
			upgradeActionPolicy : {
				required : function(element) {
					return $('#upgrade_action_planId').val().length > 0;
				},
			},
			upgradeActionPlanId : {
				required : function(element) {
					return $('#upgrade_action_policy').val().length > 0;
				},
			},
			downgradeActionPolicy : {
				required : function(element) {
					return $('#downgrade_action_planId').val().length > 0;
				},
			},
			downgradeActionPlanId : {
				required : function(element) {
					return $('#downgrade_action_policy').val().length > 0;
				},
			}
		},
		messages : {},
		submitHandler : function(form) {
			$('#planButton').prop('disabled', 'disabled');
			$('#planButtonLoader').show();
			form.submit();
		}
	});

	$('[data-toggle="tooltip"]').tooltip();

	$("input[name='isPlatformAll']").change(function() {
		var value = $(this).val();
		if (value == "all") {
			$(".plan_platform").prop('required', false);
			$('#fixedPlatformDiv').hide();
		} else {
			$(".plan_platform").prop('required', true);
			$('#fixedPlatformDiv').show();
		}
	});

	$('.dual_select').bootstrapDualListbox({
		selectorMinimalHeight : 160
	});

	$('#wrapper-field').multifield();
	$('#wrapper-field-downgrade').multifield();
	$('#wrapper-field-upgrade').multifield();
	

	
	$('#subsStartDate').datetimepicker();
    $('#planStartDate').datetimepicker();
 });
