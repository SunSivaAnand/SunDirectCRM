/**
 * 
 */

/** ***********login page modal rules**************** */
jQuery.validator.addMethod("_checkLoginUserName", function(user_name, element) {
	user_name = $('#user_name').val().trim();
	if (user_name.length == 0) {
		return this.optional(element) || false;
	}else {
		return this.optional(element) || true;
	}
}, function(error, element) {
	user_name1 = $('#user_name').val().trim();
	if (user_name1.length == 0) {
		return 'Empty spaces not allowed';
	}
});

jQuery.validator.addMethod("_checkLoginPassword", function(password, element) {
	password = $('#password').val().trim();
	if (password.length == 0) {
		return this.optional(element) || false;
	} else {
		return this.optional(element) || true;
	}
}, function(error, element) {
	var password1 = $('#password').val().trim();
	if (password1.length == 0) {
		return 'password cannot be empty'
	}
});

jQuery.validator.addMethod("_checkLoginTenantName", function(tenantname, element) {
	tenantname = $('#tenantname').val().trim();
	if (tenantname.length == 0) {
		return this.optional(element) || false;
	} else {
		return this.optional(element) || true;
	}
}, function(error, element) {
	var tenantname1 = $('#tenantname').val().trim();
	if (tenantname1.length == 0) {
		return 'tenantname cannot be empty'
	}
});


$(document).ready(function() {
	/** ***************login form validation************** */
	$('#login_form').validate({
		rules : {
			username : {
				required : true,
				_checkLoginUserName : true
			},
			password : {
				required : true,
				_checkLoginPassword : true
			},
          			
			tenantname : {
				required : true,
				_checkLoginTenantName : true
			},
		},
		messages : {},
		submitHandler : function(form) {
			$('.loginButton').prop('disabled', 'disabled');
			$('#_loginButtonLoader').show();
			form.submit();
		}
	});
});