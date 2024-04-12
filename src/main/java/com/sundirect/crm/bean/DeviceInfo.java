package com.sundirect.crm.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceInfo {
	private String model;
    @JsonProperty("pk")
    private int id;
    private Fields fields;
    public static class Fields {
    	private String os;
    	private String os_version;
    	private String make;
    	private String model;
    	private String resolution;
    	private String serial_number;
    	private String device_id;
    	private Date created_on;
    	private Date modified_on;
    	private String profile;
    	@JsonProperty("user")
    	private Integer userId;
    	private String service_id;
    	private String mso_subscriber_id;
    	private String status;
    	private String friendly_name;
    	@JsonProperty("tenant")
    	private String tenant_id;
    	@JsonProperty("client_secret")
    	private Long client_secret_id;
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public String getOs_version() {
			return os_version;
		}
		public void setOs_version(String os_version) {
			this.os_version = os_version;
		}
		public String getMake() {
			return make;
		}
		public void setMake(String make) {
			this.make = make;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getResolution() {
			return resolution;
		}
		public void setResolution(String resolution) {
			this.resolution = resolution;
		}
		public String getSerial_number() {
			return serial_number;
		}
		public void setSerial_number(String serial_number) {
			this.serial_number = serial_number;
		}
		public String getDevice_id() {
			return device_id;
		}
		public void setDevice_id(String device_id) {
			this.device_id = device_id;
		}
		public Date getCreated_on() {
			return created_on;
		}
		public void setCreated_on(Date created_on) {
			this.created_on = created_on;
		}
		public Date getModified_on() {
			return modified_on;
		}
		public void setModified_on(Date modified_on) {
			this.modified_on = modified_on;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getService_id() {
			return service_id;
		}
		public void setService_id(String service_id) {
			this.service_id = service_id;
		}
		public String getMso_subscriber_id() {
			return mso_subscriber_id;
		}
		public void setMso_subscriber_id(String mso_subscriber_id) {
			this.mso_subscriber_id = mso_subscriber_id;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getFriendly_name() {
			return friendly_name;
		}
		public void setFriendly_name(String friendly_name) {
			this.friendly_name = friendly_name;
		}
		public String getTenant_id() {
			return tenant_id;
		}
		public void setTenant_id(String tenant_id) {
			this.tenant_id = tenant_id;
		}
		public Long getClient_secret_id() {
			return client_secret_id;
		}
		public void setClient_secret_id(Long client_secret_id) {
			this.client_secret_id = client_secret_id;
		}
    	
    	
    }
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Fields getFields() {
		return fields;
	}
	public void setFields(Fields fields) {
		this.fields = fields;
	}
    
    

}
