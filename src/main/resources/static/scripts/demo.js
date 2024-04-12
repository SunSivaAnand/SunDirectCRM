$(document)
		.ready(
				function() {
					$('#_cFetchPlansBtn')
							.click(
									function(event) {
										event.preventDefault();
										if (!($('.subscribe_plans')
												.is(':empty'))) {
											$('.subscribe_plans').empty();
										}
										var _cSubscriptionId = '';
										var _cSubscriberCountry = '';
										var _cSubscriberPlatform = '';
										var _cSubscriberTags = '';
										try {
											_cSubscriptionId = $(
													'#_cSubscriptionId').val();
											_cSubscriberCountry = $(
													'#_cSubscriberCountry')
													.val();
											_cSubscriberPlatform = $(
													'#_cSubscriberPlatform')
													.val();
											_cSubscriberTags = $(
													'#_cSubscriberTags').val();
										} catch (e) {
											console.log("Exception occured"
													+ e.message);
										}
										if (_cSubscriptionId.length > 0
												&& _cSubscriberCountry.length > 0) {
											$
													.ajax({
														url : '/api/subscriber/v1/subscription/'
																+ _cSubscriptionId
																+ '/'
																+ _cSubscriberCountry,
														type : 'GET',
														dataType : 'json',
														async : true,
														beforeSend : function(
																xhr) {
															$('#pageloader')
																	.show();
														},
														success : function(
																result1) {
															if (result1.code == 200) {
																swal(
																		'Oops...',
																		result1.message
																				+ '!',
																		'error');
																$(
																		'#summary_area')
																		.show();
																return false;
															} else {
																$
																		.ajax({
																			url : '/api/plan/v1/plan',
																			type : 'GET',
																			dataType : 'json',
																			async : true,
																			data : {
																				"userId" : _cSubscriptionId,
																				"country" : _cSubscriberCountry,
																				"tag" : _cSubscriberTags,
																				"platform" : _cSubscriberPlatform
																			},
																			beforeSend : function(
																					xhr) {
																			},
																			success : function(
																					result) {
																				var contents = '';
																				var titleval = '<h2 class="title1 subscriberDetails">subscribe now</h2>';
																				contents = titleval;

																				if (result.code == 200) {
																					if (result.results != null) {
																						$
																								.each(
																										result.results,
																										function(
																												index,
																												item) {
																											var currencySymbol1 = '';
																											currencySymbol1 = item.restriction.country.symbol;
																											var pricing = '';
																											var sellingPrice = '';
																											var duration = '';
																											var discountPercentage = '';
																											var planId = '';
																											var description = '';
																											if (item.pricing != null) {
																												if (item.description.length > 20) {
																													description = item.description
																															.substring(
																																	0,
																																	15)
																															+ "...";
																												} else {
																													description = item.description;
																												}
																												$
																														.each(
																																item.pricing,
																																function(
																																		i,
																																		item1) {
																																	duration = item1.duration;
																																	if (item1.discount != null) {
																																		pricing = item1.price;
																																		$
																																				.each(
																																						item1.discount,
																																						function(
																																								index2,
																																								item2) {
																																							if (index2 == 0) {
																																								sellingPrice = currencySymbol1
																																										+ item2.sellingPrice;
																																								discountPercentage = 'discount:'
																																										+ item2.percentage
																																										+ '%';
																																							}
																																						});
																																	} else {
																																		sellingPrice = currencySymbol1
																																				+ item1.price;
																																	}
																																});
																											}
																											contents = contents
																													+ '<div class="col-sm-12 col-md-6 signup_inn_wrap">'
																													+ '<div class="signup_box"><div class="card_detail_wrap">'
																													+ '<h2 class="sub_title2">'
																													+ item.name
																													+ '</h2>'
																													+ '<ul class="plan_list"><li onclick="getPlanDetails('
																													+ item.planId
																													+ ','
																													+ _cSubscriptionId
																													+ ')"><div class="plan_wrap"><div class="plan_duration">'
																													+ '<p class="days"><span>'
																													+ duration
																													+ '</span>&nbsp;DAYS</p>'
																													+ '<p class="subs_plan_detail" title="'
																													+ item.description
																													+ '">'
																													+ description
																													+ '</p></div>'
																													+ '<div class="plan_rate discount-plan"><p class="rate">'
																													+ sellingPrice
																													+ '<span>'
																													+ pricing
																													+ '</span>'
																													+ '</p><p class="rate discount"><label>'
																													+ discountPercentage
																													+ '</label></p>'
																													+ '<p class="subscription">Subscribe Now</p></div>'
																													+ '</div></li></ul></div></div></div>';
																										});
																						$(
																								'.subscribe_plans')
																								.append(
																										contents);
																					}
																				} else if (result.code == 404) {
																					swal(
																							'Oops...',
																							result.message
																									+ '!',
																							'error')
																					return false;
																				}
																			},
																			complete : function() {
																				$(
																						'#pageloader')
																						.hide();
																				$(
																						'#summary_area')
																						.hide();
																				$(
																						'.subscribe_plans')
																						.show();
																			}

																		});
															}
														},
														complete : function() {
															$('#pageloader')
																	.hide();
														}
													});
										}
									});

				});
function make_base_auth() {
	var tok = "apiuser" + ':' + "apiuser";
	var hash = btoa(tok);
	return 'Basic ' + hash;
}
function getPlanDetails(planid, userid) {
	if (planid != null && userid != null) {
		$('#_cSubscriptionDiv').hide();
		$
				.ajax({
					url : '/api/plan/v1/' + planid + '/' + userid,
					type : 'GET',
					dataType : 'json',
					async : true,
					beforeSend : function(xhr) {
						$('#pageloader').show();
					},
					success : function(result1) {
						var duration = '';
						var planPrice = '';
						var planName = '';
						var currencySymbol = '';
						var country = '';
						var paymentMode = '';
						var discountId = null;
						var paymentModeTypes = '';
						if (result1.code == 200) {
							currencySymbol = result1.result.restriction.country.symbol;
							planName = result1.result.name;
							country = result1.result.restriction.country.code;
							$
									.each(
											result1.result.pricing,
											function(index, item) {
												var isPaymentActive = '';
												paymentMode = item.paymentMode;
												if (index == 0) {
													isPaymentActive = 'active';
												}
												if (index == 0) {
													duration = item.duration;
													if (item.discount != null) {
														$
																.each(
																		item.discount,
																		function(
																				index2,
																				item2) {
																			if (index2 == 0) {
																				discountId = item2.discountId;
																				planPrice = currencySymbol
																						+ item2.sellingPrice;
																				discountPercentage = 'discount-'
																						+ item2.percentage
																						+ '%';
																			}
																		});
													} else {
														planPrice = currencySymbol
																+ item.price;
													}
												}
												paymentModeTypes = paymentModeTypes
														+ '<label class="btn btn-sm btn-white '
														+ isPaymentActive
														+ '"><input type="radio" id="paymentTypes" name="filter" value="'
														+ paymentMode
														+ '">'
														+ paymentMode
														+ '</label>';
											});
							paymentModeTypes = paymentModeTypes
									+ '<a href="#" class="btn btn-pay" onclick="createPayUOrder('
									+ planid + ',' + userid + ',' + "'"
									+ country + "'" + ',' + discountId
									+ ')">Pay</a>';
							$('#filterButtons').append(paymentModeTypes);
							var content = '<div class="signup_box"><h2 class="title1">Payment details</h2>'
									+ '<div class="card_detail_wrap"><h2 class="sub_title2">Selected Plan</h2>'
									+ '<ul class="plan_list"><li><div class="plan_wrap"><div class="plan_duration">'
									+ '<p class="days"><span>'
									+ duration
									+ '</span>&nbsp;DAYS</p><p class="subs_plan_detail">'
									+ planName
									+ '</p></div><div class="plan_rate"><p class="rate" id="planrate">'
									+ planPrice
									+ '</p></div></div></li></ul></div></div><div class="card_tab_form"><section>'
									+ '<ul class="card_tab">'
									+ '</ul>'
									+ '</section><div class="update_pay_form"></div><div class="clearfix "></div></div>'
									+ '<div class="plans-coupon"><h3>Coupon Code<input class="" type="text" id= "couponCodeId" name="coupon code"'
									+ 'placeholder="Enter Coupon Code"> <a class="apply-btn" onclick="checkCoupon('
									+ userid
									+ ','
									+ "'"
									+ country
									+ "'"
									+ ','
									+ planid
									/*
									 * + ',' + "'" + paymentMode + "'"
									 */
									+ ','
									+ "'"
									+ currencySymbol
									+ "'"
									+ ","
									+ discountId + ')">Apply</a></h3></div>';
							$('#_cPlanDetails').append(content);
						}
					},
					complete : function() {
						$('#pageloader').hide();
						$('#_cPaymentDiv').show();
					}
				});
	}
}

function createPayUOrder(planIdval, userIdval, countryval, discountIdval) {
	var couponcode = $('#couponCodeId').val().trim();
	var paymentModeval = null;
	if ($('label.active #paymentTypes').val().length > 0) {
		paymentModeval = $('label.active #paymentTypes').val();
	}
	if (couponcode.length == 0) {
		couponcode = null;
	}
	var jsonString = {
		"actionPlanId" : null,
		"assetId" : null,
		"clientIPAddress" : "127.0.0.1",
		"country" : countryval,
		"couponCode" : couponcode,
		"discountId" : discountIdval,
		"orderType" : "Purchase",
		"paymentMode" : paymentModeval,
		"planId" : planIdval,
		"platform" : "Android",
		"userId" : userIdval
	}
	$.ajax({
		url : '/api/payment/v1/order',
		type : 'POST',
		contentType : 'application/json',
		dataType : 'json',
		async : true,
		data : JSON.stringify(jsonString),
		beforeSend : function(xhr) {
			/* xhr.setRequestHeader('Authorization', make_base_auth()); */
			$('#pageloader').show();
		},
		success : function(result1) {
			if (!(result1.code == 302)) {
				swal('Oops...', result1.message + '!', 'error')
				return false;
			} else {
				var redirectionUrl = '';
				redirectionUrl = result1.result.paymentGatewayUrl;
				window.location.href = redirectionUrl;
			}
		},
		complete : function() {
			$('#pageloader').hide();
		}
	});
}

function checkCoupon(useridval, countryval, planidval, currencySymbolval,
		discountIdval) {
	var couponcode = $('#couponCodeId').val();
	var paymentModeval = null;
	if ($('label.active #paymentTypes').val().length > 0) {
		paymentModeval = $('label.active #paymentTypes').val();
	}
	$.ajax({
		url : '/api/payment/v1/coupon/redeem',
		type : 'GET',
		dataType : 'json',
		async : true,
		data : {
			"userId" : useridval,
			"country" : countryval,
			"planId" : planidval,
			"couponCode" : couponcode,
			"paymentMode" : paymentModeval,
			"discountId" : discountIdval
		},
		beforeSend : function(xhr) {
			/* xhr.setRequestHeader('Authorization', make_base_auth()); */
			$('#pageloader').show();
		},
		success : function(result1) {
			if (result1.code == 200) {
				$('p#planrate').text(
						currencySymbolval + result1.result.sellingPrice);
				swal({
					title : result1.message + '!',
					text : '',
					type : 'success',
					timer : 1500,
					showConfirmButton : false
				});
			} else {
				swal('Oops...', result1.message + '!', 'error')
				return false;
			}
		},
		complete : function() {
			$('#pageloader').hide();
		}
	});
}