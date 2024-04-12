package com.sundirect.crm.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerEvent {

	private String model;
	private int pk;
	private Fields fields;
	private List<DeviceInfo> device;
	
	public static class Fields {
        @JsonProperty("user_id")
        private String userId;
        @JsonProperty("content_id")
        private String contentId;
        @JsonProperty("device_id")
        private String deviceId;
        private String action;
        @JsonProperty("elapsed_time")
        private int elapsedTime;
        @JsonProperty("total_duration")
        private int totalDuration;
        @JsonProperty("stream_name")
        private String streamName;
        @JsonProperty("created_at")
        private Date createdAt;
        @JsonProperty("modified_at")
        private Date modifiedAt;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getContentId() {
			return contentId;
		}
		public void setContentId(String contentId) {
			this.contentId = contentId;
		}
		public String getDeviceId() {
			return deviceId;
		}
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public int getElapsedTime() {
			return elapsedTime;
		}
		public void setElapsedTime(int elapsedTime) {
			this.elapsedTime = elapsedTime;
		}
		public int getTotalDuration() {
			return totalDuration;
		}
		public void setTotalDuration(int totalDuration) {
			this.totalDuration = totalDuration;
		}
		public String getStreamName() {
			return streamName;
		}
		public void setStreamName(String streamName) {
			this.streamName = streamName;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getModifiedAt() {
			return modifiedAt;
		}
		public void setModifiedAt(Date modifiedAt) {
			this.modifiedAt = modifiedAt;
		}
		       
    }

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public List<DeviceInfo> getDevice() {
		return device;
	}

	public void setDevice(List<DeviceInfo> device) {
		this.device = device;
	}	
	
}
