package com.sundirect.crm.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
    private String model;
    @JsonProperty("pk")
    private int id;
    private Fields fields;
    public static class Fields {
        private String first;
        private String last;
        @JsonProperty("password_hash")
        private String passwordHash;
        @JsonProperty("mobile_no")
        private String mobileNo;
        private String smc;
        private int status;
        @JsonProperty("mobile_no_verified")
        private boolean mobileNoVerified;
        @JsonProperty("email_id")
        private String emailId;
        private String dob;
        private String gender;
        @JsonProperty("created_on")
        private String createdOn;
        @JsonProperty("modified_on")
        private String modifiedOn;
        private int tenant;
        @JsonProperty("guest_device")
        private String guestDevice;
        @JsonProperty("service_id")
        private String serviceId;
        @JsonProperty("customer_account_number")
        private String customerAccountNumber;
        private String age;
		public String getFirst() {
			return first;
		}
		public void setFirst(String first) {
			this.first = first;
		}
		public String getLast() {
			return last;
		}
		public void setLast(String last) {
			this.last = last;
		}
		public String getPasswordHash() {
			return passwordHash;
		}
		public void setPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
		}
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
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public boolean isMobileNoVerified() {
			return mobileNoVerified;
		}
		public void setMobileNoVerified(boolean mobileNoVerified) {
			this.mobileNoVerified = mobileNoVerified;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(String createdOn) {
			this.createdOn = createdOn;
		}
		public String getModifiedOn() {
			return modifiedOn;
		}
		public void setModifiedOn(String modifiedOn) {
			this.modifiedOn = modifiedOn;
		}
		public int getTenant() {
			return tenant;
		}
		public void setTenant(int tenant) {
			this.tenant = tenant;
		}
		public String getGuestDevice() {
			return guestDevice;
		}
		public void setGuestDevice(String guestDevice) {
			this.guestDevice = guestDevice;
		}
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		public String getCustomerAccountNumber() {
			return customerAccountNumber;
		}
		public void setCustomerAccountNumber(String customerAccountNumber) {
			this.customerAccountNumber = customerAccountNumber;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}

       
    }
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public Fields getFields() {
		return fields;
	}
	public void setFields(Fields fields) {
		this.fields = fields;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
    
}
