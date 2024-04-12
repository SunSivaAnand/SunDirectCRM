package com.sundirect.crm.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plan {

	    @JsonProperty("planId")
	    private int planId;
	    private String name;
	    private String category;
	    private String description;
	    private Integer precedence;
	    private String status;
	    private boolean hasEula;
	    private String buttonText;
	    private Restriction restriction;
	    private List<Action> action;
	    private List<CustomField> customField;
	    @JsonProperty("planBundleMapping")
	    private List<Bundle> planBundleMapping;
	    private List<Pricing> pricing;
	    private String accessLabel;
	    
	    
		public List<Bundle> getPlanBundleMapping() {
			return planBundleMapping;
		}
		public void setPlanBundleMapping(List<Bundle> planBundleMapping) {
			this.planBundleMapping = planBundleMapping;
		}
		public int getPlanId() {
			return planId;
		}
		public void setPlanId(int planId) {
			this.planId = planId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getPrecedence() {
			return precedence;
		}
		public void setPrecedence(Integer precedence) {
			this.precedence = precedence;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public boolean isHasEula() {
			return hasEula;
		}
		public void setHasEula(boolean hasEula) {
			this.hasEula = hasEula;
		}
		public String getButtonText() {
			return buttonText;
		}
		public void setButtonText(String buttonText) {
			this.buttonText = buttonText;
		}
		public Restriction getRestriction() {
			return restriction;
		}
		public void setRestriction(Restriction restriction) {
			this.restriction = restriction;
		}
		

		public String getAccessLabel() {
			return accessLabel;
		}
		public void setAccessLabel(String accessLabel) {
			this.accessLabel = accessLabel;
		}
		public List<Pricing> getPricing() {
			return pricing;
		}
		public void setPricing(List<Pricing> pricing) {
			this.pricing = pricing;
		}
		public List<CustomField> getCustomField() {
			return customField;
		}
		public void setCustomField(List<CustomField> customField) {
			this.customField = customField;
		}
		public List<Action> getAction() {
			return action;
		}
		public void setAction(List<Action> action) {
			this.action = action;
		}
	    
		
		
	    
	    
	    
}
