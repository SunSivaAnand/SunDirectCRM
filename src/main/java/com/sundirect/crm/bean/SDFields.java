package com.sundirect.crm.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SDFields {

	
	@JsonProperty("sms_plan_id")
    private int smsPlanId;
    @JsonProperty("sd_plan_id")
    private int sdPlanId;
    private String plan_description;
    private String plan_duration;
    private String plan_name;
    private int status;
    private String created_on;
    private String modified_on;
    private String subscription_type;
	public int getSmsPlanId() {
		return smsPlanId;
	}
	public void setSmsPlanId(int smsPlanId) {
		this.smsPlanId = smsPlanId;
	}
	public int getSdPlanId() {
		return sdPlanId;
	}
	public void setSdPlanId(int sdPlanId) {
		this.sdPlanId = sdPlanId;
	}
	public String getPlan_description() {
		return plan_description;
	}
	public void setPlan_description(String plan_description) {
		this.plan_description = plan_description;
	}
	public String getPlan_duration() {
		return plan_duration;
	}
	public void setPlan_duration(String plan_duration) {
		this.plan_duration = plan_duration;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}
	public String getSubscription_type() {
		return subscription_type;
	}
	public void setSubscription_type(String subscription_type) {
		this.subscription_type = subscription_type;
	}
    
    
    
	

}
