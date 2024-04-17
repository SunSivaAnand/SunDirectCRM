package com.sundirect.crm.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSignUp {
	
	private String id;
	private String name;
	private String key;
	private String role;
	@JsonProperty("tenant name")
	private String tenantName;
	@JsonProperty("tenant id")
	private String tenantId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}	
}
