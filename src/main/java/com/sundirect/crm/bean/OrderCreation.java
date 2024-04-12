package com.sundirect.crm.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderCreation {

	@NotNull(message = "userId can't be null")
	@Min(value = 1)
	private Integer userId;

	@NotEmpty(message = "country can't be empty")
	private String country;

	@NotNull(message = "planId can't be null")
	@Min(value = 1)
	private Integer planId;

	@NotEmpty(message = "platform can't be empty")
	private String platform;

	@NotEmpty(message = "client IP address can't be empty")
	private String clientIPAddress;

	@NotEmpty(message = "order type can't be empty")
	private String orderType;

	@Min(value = 1)
	private Integer actionPlanId;

	private String couponCode;

	@Min(value = 1)
	private Integer assetId;

	@NotEmpty(message = "payment mode can't be empty")
	private String paymentMode;

	private Integer discountId;
	
	private String pmtChannel;
	
	private String retControlUrl;
	
	private boolean renewable;
	
	private String startDate;
	
	private String tenantId;
	
	
	private String context;
	private String acquistionSource;
	private String acquisitionMedium;
	private String campaignName;
	private String transactionExternalId;
	private String redirectUrl;
	private String transactionCharge;
	private String email;
	private String mobile_no;
	private String failureRetControlUrl;
	//private Boolean lifeTimePack;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getClientIPAddress() {
		return clientIPAddress;
	}
	public void setClientIPAddress(String clientIPAddress) {
		this.clientIPAddress = clientIPAddress;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Integer getActionPlanId() {
		return actionPlanId;
	}
	public void setActionPlanId(Integer actionPlanId) {
		this.actionPlanId = actionPlanId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Integer getAssetId() {
		return assetId;
	}
	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Integer getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}
	public String getPmtChannel() {
		return pmtChannel;
	}
	public void setPmtChannel(String pmtChannel) {
		this.pmtChannel = pmtChannel;
	}
	public String getRetControlUrl() {
		return retControlUrl;
	}
	public void setRetControlUrl(String retControlUrl) {
		this.retControlUrl = retControlUrl;
	}
	public boolean isRenewable() {
		return renewable;
	}
	public void setRenewable(boolean renewable) {
		this.renewable = renewable;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getAcquistionSource() {
		return acquistionSource;
	}
	public void setAcquistionSource(String acquistionSource) {
		this.acquistionSource = acquistionSource;
	}
	public String getAcquisitionMedium() {
		return acquisitionMedium;
	}
	public void setAcquisitionMedium(String acquisitionMedium) {
		this.acquisitionMedium = acquisitionMedium;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getTransactionExternalId() {
		return transactionExternalId;
	}
	public void setTransactionExternalId(String transactionExternalId) {
		this.transactionExternalId = transactionExternalId;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getTransactionCharge() {
		return transactionCharge;
	}
	public void setTransactionCharge(String transactionCharge) {
		this.transactionCharge = transactionCharge;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getFailureRetControlUrl() {
		return failureRetControlUrl;
	}
	public void setFailureRetControlUrl(String failureRetControlUrl) {
		this.failureRetControlUrl = failureRetControlUrl;
	}
	
	
}
