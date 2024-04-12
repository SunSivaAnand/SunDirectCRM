jQuery.validator.addMethod("noSpace", function(value, element) {
	return $.trim(value) != "";
}, "Space are not allowed");

$(document).ready(function() {
	$('.dual_select').bootstrapDualListbox({
		selectorMinimalHeight : 160
	});
	
	$('[data-toggle="tooltip"]').tooltip(); 
	
	$('input[name="daterange"]').daterangepicker();

    //$('#reportrange span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
    //$('#reportrange #date').val(moment().format('MM/DD/YYYY') + ',' + moment().format('MM/DD/YYYY'));

    $('#reportrange').daterangepicker({
        format: 'MM/DD/YYYY',
        startDate: moment($('#dateFrom').val(), 'MM/DD/YYYY'),
        endDate: moment($('#dateTill').val(), 'MM/DD/YYYY'),
        maxDate: moment().add(20,'years'),
        showDropdowns: true,
        showWeekNumbers: true,
        timePicker: false,
        timePickerIncrement: 1,
        timePicker12Hour: true,
        opens: 'right',
        drops: 'down',
        buttonClasses: ['btn', 'btn-sm'],
        applyClass: 'btn-primary',
        cancelClass: 'btn-default',
        separator: ' to ',
        locale: {
            applyLabel: 'Submit',
            cancelLabel: 'Cancel',
            fromLabel: 'From',
            toLabel: 'To',
            customRangeLabel: 'Custom',
            daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
            monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
            firstDay: 1
        }
    }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
        $('#reportrange span').html(start.format('MM/DD/YYYY') + ' - ' + end.format('MM/DD/YYYY'));
        $('#reportrange #date').val(start.format('MM/DD/YYYY') + ',' + end.format('MM/DD/YYYY'));
    });
    
    
    $("input[name='isMaxRedemptions']").change(function() {
		var value = $(this).val();
		if (value == "all") {
			$(".max_redemptions").prop('required', false);
			$('#billing_hide_maxredemption').hide();
		} else {
			$(".max_redemptions").prop('required', true);
			$('#billing_hide_maxredemption').show();
		}
	});
	
	$("input[name='isMaxRedemptionsForSubscriber']").change(function() {
		var value = $(this).val();
		if (value == "all") {
			$(".maxRedemptions_for_subscriber").prop('required', false);
			$('#billing_hide_maxredemptionforsub').hide();
		} else {
			$(".maxRedemptions_for_subscriber").prop('required', true);
			$('#billing_hide_maxredemptionforsub').show();
		}
	});
	
	$("input[name='isPlansAll']").change(function() {
		var value = $(this).val();
		if (value == "all") {
			$(".specific_plans").prop('required', false);
			$('#fixedPlansDiv').hide();
		} else {
			$(".specific_plans").prop('required', true);
			$('#fixedPlansDiv').show();
		}
	});
	
	
	
		$.validator.setDefaults({
			ignore : ":hidden:not(select)"
		});
		if ($("select.specific_plans").length > 0) {
			$("select.specific_plans").each(function() {
				if ($(this).attr('required') !== undefined) {
					$(this).on("change", function() {
						$(this).valid();
					});
				}
			});
		}
	
	
	$('#coupon_form').validate({
		rules : {
			name : {
				required : true,
				minlength : 3,
				maxlength : 50,
				noSpace : true
			},
			type : {
				required : true,
				noSpace : true
			},
			value : {
				required : true,
				number : true
			},
			maxRedemptions : {
				required : function(element) {
					return $('input[name=isMaxRedemptions]:checked').val() != 'Fixed';
				},
			},
			maxRedemptionsForSubscriber : {
				required : function(element) {
					return $('input[name=isMaxRedemptionsForSubscriber]:checked').val() != 'Fixed';
				},
			},
			paymentDesc : {
				required : true,
				minlength : 3,
				maxlength : 250,
				noSpace : true
			},
			invoiceDesc : {
				required : true,
				minlength : 3,
				maxlength : 250,
				noSpace : true
			}
		},
		messages : {},
		submitHandler : function(form) {
			$('#couponButton').prop('disabled', 'disabled');
			$('#couponButtonLoader').show();
			form.submit();
		}
	});

	
});