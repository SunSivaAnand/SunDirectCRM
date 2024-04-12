package com.sundirect.crm.bean;

import java.util.Date;
import java.util.List;

public class Restriction {
    private String subscriptionType;
    private String consumptionType;
    private Country country;
    private List<String> platform;
    private List<String> accessLabel;
    private String devicesAllowed;
    private boolean unSubscription;
    private boolean ads;
    private String notifiyType;
    private String tag;
    private boolean chromeCast;
    private boolean subtitle;
    private Integer concurrentStream;
    private Integer noOfViews;
    private Integer noOfMinutes;
    private boolean seekEnabled;
    private Integer updateStatusIntervalInSec;
    private Date startDate;
    private Date subsStartDate;
    private String postBookingMsg;
    private String preBookingMsg;
    
    public static class Country {
        private String code;
        private String name;
        private String currency;
        private String symbol;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
        
        
    }
    
   



	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<String> getPlatform() {
		return platform;
	}

	public void setPlatform(List<String> platform) {
		this.platform = platform;
	}

	public List<String> getAccessLabel() {
		return accessLabel;
	}

	public void setAccessLabel(List<String> accessLabel) {
		this.accessLabel = accessLabel;
	}

	public String getDevicesAllowed() {
		return devicesAllowed;
	}

	public void setDevicesAllowed(String devicesAllowed) {
		this.devicesAllowed = devicesAllowed;
	}

	public boolean isUnSubscription() {
		return unSubscription;
	}

	public void setUnSubscription(boolean unSubscription) {
		this.unSubscription = unSubscription;
	}

	public boolean isAds() {
		return ads;
	}

	public void setAds(boolean ads) {
		this.ads = ads;
	}

	public String getNotifiyType() {
		return notifiyType;
	}

	public void setNotifiyType(String notifiyType) {
		this.notifiyType = notifiyType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isChromeCast() {
		return chromeCast;
	}

	public void setChromeCast(boolean chromeCast) {
		this.chromeCast = chromeCast;
	}

	public boolean isSubtitle() {
		return subtitle;
	}

	public void setSubtitle(boolean subtitle) {
		this.subtitle = subtitle;
	}

	public Integer getConcurrentStream() {
		return concurrentStream;
	}

	public void setConcurrentStream(Integer concurrentStream) {
		this.concurrentStream = concurrentStream;
	}

	public Integer getNoOfViews() {
		return noOfViews;
	}

	public void setNoOfViews(Integer noOfViews) {
		this.noOfViews = noOfViews;
	}

	public Integer getNoOfMinutes() {
		return noOfMinutes;
	}

	public void setNoOfMinutes(Integer noOfMinutes) {
		this.noOfMinutes = noOfMinutes;
	}

	public boolean isSeekEnabled() {
		return seekEnabled;
	}

	public void setSeekEnabled(boolean seekEnabled) {
		this.seekEnabled = seekEnabled;
	}

	public Integer getUpdateStatusIntervalInSec() {
		return updateStatusIntervalInSec;
	}

	public void setUpdateStatusIntervalInSec(Integer updateStatusIntervalInSec) {
		this.updateStatusIntervalInSec = updateStatusIntervalInSec;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getSubsStartDate() {
		return subsStartDate;
	}

	public void setSubsStartDate(Date subsStartDate) {
		this.subsStartDate = subsStartDate;
	}

	public String getPostBookingMsg() {
		return postBookingMsg;
	}

	public void setPostBookingMsg(String postBookingMsg) {
		this.postBookingMsg = postBookingMsg;
	}

	public String getPreBookingMsg() {
		return preBookingMsg;
	}

	public void setPreBookingMsg(String preBookingMsg) {
		this.preBookingMsg = preBookingMsg;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(String consumptionType) {
		this.consumptionType = consumptionType;
	}

	
    
}
