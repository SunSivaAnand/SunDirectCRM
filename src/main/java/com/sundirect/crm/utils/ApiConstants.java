
package com.sundirect.crm.utils;

public class ApiConstants {

	public enum Status {
		SUCCESS,
		FAILED,
		FAILURE,
		REDIRECT,
		PENDING
	}

	public enum Messages {

		ASSET_CREATED("Asset create/update success"),
		ASSET_NOT_CREATED("Asset already exist"),
		ASSET_NOT_FOUND("Asset not found"),
		AUTH_NOT_FOUND("Auth Token not found"),
		SUBSCRIBER_CREATED("Subscriber created success"),
		SUBSCRIBER_NOT_CREATED("Subscriber creation failed"),
		SUBSCRIBER_NOT_FOUND("Subscriber not found"),
		SUBSCRIBER_FOUND("Subscriber fetch success"),
		FETCH_ALLPLANS_NOT_FOUND("No plans found"),
		FETCH_ALLPLANS_FOUND("Fetch all plans success"),
		FETCH_PLAN("Fetch plan success"),
		ADD_SUBSCRIPTION("Subscription added success"),
		MYPLANS_NOT_FOUND("Subscriptions Not Found"),
		MYPLANS_FOUND("Subscriptions found"),
		TRANSACTIONS_FOUND("Transactions Found"),
		TRANSACTIONS_NOT_FOUND("Transactions Not Found"),
		BALANCE_FOUND("Customer Balance"),
		ASSET_SUBSCRIPTIONS_NOT_FOUND("Subscriptions Not Found"),
		ASSET_SUBSCRIPTIONS_FOUND("Subscriptions found"),
		COUPON_NOT_FOUND("Coupon not found"),
		TENENAT_NOT_FOUND("Tenant not found"),
		CARD_NOT_FOUND("Card or Pin not found"),
		COUNTRY_NOT_FOUND("Country not found"),
		PAYMENT_MODE_NOT_FOUND("Payment mode not found"),
		PLAN_NOT_FOUND("Plan not found"),
		COUPON_REDEEM("Coupon redeem success"),
		GIFT_CARD_REDEEM("Gift Card redeem success"),
		CREATE_ORDER("Order created success"),
		ORDER_FAILED("Failed to create Order"),
		ORDER_FAILED_NULL("order creation failed due to null"),
		ORDER_FAILED_LOW_BALANCE("order creation failed due to low balance"),
		ORDER_FAILED_NO_PACKAGE_ID("order creation failed due to no package id"),
		ORDER_FAILED_STATUS("order creation failed at ezybill"),
		PAYPAL_FAILED_STATUS("order creation failed at paypal"),
		ORDER_FAILED_PORTED("failed due to ported user"),
		ORDER_PAYU("Order created for payU"),
		CANCEL_REFUND_SUBSCRIPTION("Cancel Refund subscription success"),
		UPDATESUBSCRIPTION("Update Subscription success"),
		STATUS_NOT_FOUND("Status not found"),
		STATUS_FAILED("status is 0 at ezyBill"),
		UNSUBSCRIPTION("Unsubscription success"),
		SUBSCRIPTION("subscription updated successfully"),
		MSG_SUBSCRIPTION("msg subscription updated successfully"),
		MSG_SUBSCRIPTION_NOT_UPDATED("subscription not updated successfully"),
		SUBSCRIPTION_NOT_UPDATED("subscription not updated successfully"),
		CANCEL_SUBSCRIPTION("Cancel subscription success"),
		CANCEL_SUBSCRIPTION_FAILED("Cancel subscription failed"),
		ORDER_STATUS("Subscription created successfully"),
		DISCOUNT_NOT_FOUND("Discount not found"),
		ACTION_PLAN_TYPE_NOT_MATCHED("Order Type is not matching with actionPlanId"),
		ACTION_PLAN_NOT_FOUND("Action Plan Id not found"),
		SUBSCRIBER_PLAN_SUBSCRIPTION_NOT_FOUND("Subscriberd subscription not found"),
		TRIAL_TYPE_NOT_FOUND("Trial type not found"),
		TRIAL_TYPE_ALREADY_SUBSCRIBED("Trial type for this plan already subscribed"),
		TAG_NOT_FOUND("Tag not found"),
		TENANT_ID_NOT_FOUND("TenantId can't be empty"),
		TENANT_ID_NOT_FOUND_IN_HEADER("tenantId not found in header"),
		TENANT_ID_NOT_FOUND_IN_DB("tenantId not exists in database"),
		ADD_TENANT("Tenant created success"),
		TENANT_ID_FOUND("Tenant id found"),
		TENANTID_SAME("Tenant id same in DB"),		
		TENANT_NOT_UPDATE("Failed to update"),
		TENANT_NOT_DELETED("Failed to Delete"),
		TENANT_LIST_FOUND("LIST FOUND"),
		TENANT_DELETED("Delete success"),
		TENANT_UPDATED("TenantId Updated successfully"),
		PLATFORM_NOT_FOUND("Platform is empty"),
		PLATFORM_NOT_MAPPED("Platform not mapped to plan"),
		MOBILENO_LENGTH_ERROR("mobile number length is long"),
		MOBILENO_NOT_FOUND("mobile number not found in DB"),
		STANDARD_PACK_ERROR("Plan has two active subscriptions"),
		PLAN_ACTIVATED_MORETHAN_ONCE("plan activated more than once"),
		PAYMENT_SUCCESS("payment success"),
		PAYMENT_SUCCESS_WEBHOOK("payment success using webhook"),
		PAYMENT_FAILED("payment_failed"),
		PAYMENT_PENDING("payment pending at billdesk"),
		BILLDESK_CHECKSUM_INVALID("checksum invalid"),
		BILLDESK_MSG_LENTH_INVALID("msg length not matched"),
		BILLDESK_TECHNICAL_ERROR("Technical error at billdesk"),
		RAZORPAY_TRANSACTION_DETAILS_INSERTION_FAILED("failed to insert orderid,paymentid,signature in DB"),
		RAZORPAY_ORDERID_INVALID("razorpay orderid not present in order or subscription table"),
		SUCCESS("success"),
		FAILED("DB insertion failed."),
		MALFORMEDFAILED("The data in the receipt-data property was malformed or the service experienced a temporary issue. Try again."),
		HTTPPOST("The request to the App Store was not made using the HTTP POST request method."),
		NOLONGER("This status code is no longer sent by the App Store."),
		AUTHENTIICATED("The receipt could not be authenticated"),
		NOTMATCH("The shared secret you provided does not match the shared secret on file for your account."),
		TEMPUNAVAILABLE("The receipt server was temporarily unable to provide the receipt. Try again."),
		EXPIRED("This receipt is valid but the subscription has expired. When this status code is returned to your server, the receipt data is also decoded and returned as part of the response. Only returned for iOS 6-style transaction receipts for auto-renewable subscriptions."),
		TESTENV("This receipt is from the test environment, but it was sent to the production environment for verification."),
		PRODENV("This receipt is from the production environment, but it was sent to the test environment for verification"),
		INTERNAL("Internal data access error. Try again later."),
		MISMATCH("ProductId mismatch.."),
		IFAILED("iTunes Transaction table insertion failed"),
		TFAILED("Table Insertion Failed"),
		ACCNOTFOUND("The user account cannot be found or has been deleted."),
		ORDER_FAILED_CONTEXT_NOT_AVAILABLE("context is mendatory"),
		PAYU_ORDERID_INVALID("orderid not exists in order or subscription table"),
        TOKENID_NULL("tokenid not present for paymentId"), 
        PRODUCT_ID_NOT_FOUND("product is mandatory in custom fields"), 
        WALLET_NOT_CREATED("Subscriber created but failed in wallet creation"), 
        MANDATORY_FIELDS("address,pincode,state,city,email id,userid are mandatory"),
        EMAIL_MOBILE_NOT_FOUND("Email or mobile not found."),
        SUBSCRIBER_UPDATED("subscriber updated successfully"),
        SUBSCRIBER_NOT_UPDATED("updation failed"),
        SUBSCRIBER_DETAILS_EXISTED("Subscriber profile details already existed"),
        STATUS_UPDATED_ALREADY("Status already updated"),
        PRODUCT_CREATE("Product Created Successfully"), 
        ACCESS_NOT_FOUND("Access label not found"), 
        BUNDLE_CREATED("Bundle Created"),
        BUNDLE_UPDATED("Bundle Updated"),
        BUNDLE_NOT_FOUND("Bundle not found"), 
        DELTE_ASSET("Asset Deleted"), 
        BUNDLE_FAILED("Bundle creation failed"), 
        PLAN_CREATE("Plan Created"), 
        PLAN_UPDATE("Update plan"), 
        FAILED_INAPP("Receipt Verification Failed"),
        ALREADY_AVAILED("Free subscription already availed"), 
        FETCH_ASSET("Fetching Asset Detail"),
        MANDATORY_USER_ID("userid is mandatory"),
        GPLAY_CANCEL_FAILED_MESSAGE("GPLAY Cancellation Failed"),
        GPLAY_CANCEL_FAILED("Google play subscription is not cancelled"),
        DELETE_SUBSCRIBER_UPDATED("subscriber deleted successfully"),
        DELETED_SUBSCRIBER_ALREADY("subscriber account already deleted"),
        DELETE_SUBSCRIBER_FAILED("Subscriber details not deleted"),
        SUBSCRIBER_ACCOUNT_DELETED("Deleted User"),
        MANDATORY_COUNTRY("Country is mandatory");		
		

		
		

		private final String message;

		private Messages(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

	}

	public enum CustomCodes {

		COUPON_EXPIRED(601, "Coupon code expired"),
		INACTIVE_COUPON(602,"Coupon not active"),
		LESS_BALANCE(601,"Card deactivated. Zero balance."),
		RETRY_MSG(601,"Could not reach server,Please re-try after some time."),
		REDEEM_FAIL(602,"Redeem Failed"),
		REV_SUCCESS_MSG(601,"Qwikcilver transaction timed out, please retry"),
		REV_FAILED_MSG(601,"Reversal Failed"),
		AUTH_FAIL(603,"Auth Token Failed"),
		INVALID_COUPON(602,"Coupon not eligible"),
		INACTIVE_COUPON_PAMENT(603, "Plan does not have this payment mode"),
		SUBSCRIBER_ALREADY_EXISTS(202, "Subscriber already exists"),
		MOBILE_SUBSCRIBER_ALREADY_EXISTS(202, "Mobile number already exist with this country"),
		EMAIL_SUBSCRIBER_ALREADY_EXISTS(202, "Email already exists"),
		ASSET_ALREADY_EXISTS(202,"Asset already exists"),
		SUBSCRIPTION_NOT_FOUND(604,"Subscription not found"),
		SUBSCRIPTION_STATE_NOT_FOUND(605,"Subscription state not found"),
		PAYMENT_FAILED(605,"Payment failed"),
		ORDER_NOT_FOUND(604,"Order not found"),
		SUBSCRIPTION_ADDED_FOR_DEMO_USER(201,"Subscription created for demo|zero price|trial type user"),
		SUBSCRIPTION_ALREADY_EXISTS(202, "Subscription already exists for this plan and subscriber"),
		PLATFORM_NOT_MAPPED(602,"Coupon not eligible"),
		AUTH_NOT_FOUND(600,"Auth Token Not Matched"),
		SUB_NOT_FOUND(600,"Subscription Not Found"),
		SUB_ACT_FOUND(600,"Order is not in created state"),
		SUBSCRIBER_NOT_FOUND(600,"Subscriber not found"),
		ORDER_EXISTED(600,"Duplicate Fetch Request"),
		PAY_MODE_NOT_MATCHED(600,"Payment mode not matched"),
		PLAN_NOT_FOUND(600,"Plan not found"),
		EMAIL_REF_ID_NOT_MATCHED(600,"Email And Ref ID Not Matched"),
		TRAN_ORDER_NOT_MATCHED(600,"Transaction Id and Order Record Not Matched"),
		SUBSCRIBER_NOT_EXISTS(202,"Subscriber not existed for updation"),
		COUPON_SUB(602,"Sorry, you are not eligible for this offer");
		
		

		private final Integer code;
		private final String value;

		private CustomCodes(Integer code, String value) {
			this.code = code;
			this.value = value;

		}

		public Integer getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}

	}

}
