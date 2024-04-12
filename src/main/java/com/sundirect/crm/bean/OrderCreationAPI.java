package com.sundirect.crm.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderCreationAPI {
	public String action;
	public String mobileNo;
	public String packageIds;
	public String smc;
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getSmc() {
		return smc;
	}
	public void setSmc(String smc) {
		this.smc = smc;
	}
	public String getPackageIds() {
		return packageIds;
	}
	public void setPackageIds(String packageIds) {
		this.packageIds = packageIds;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
			
}
