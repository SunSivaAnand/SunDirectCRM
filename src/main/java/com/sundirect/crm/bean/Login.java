package com.sundirect.crm.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {
	
	private Integer id;
	@JsonProperty("name")
	private String username;
	@JsonProperty("key")
	private String password;
	@JsonProperty("tenant id")
	private String tenantId;
	@JsonProperty("tenant name")
	private String tenantName;
	@JsonProperty("role")
	private String role;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	

}
