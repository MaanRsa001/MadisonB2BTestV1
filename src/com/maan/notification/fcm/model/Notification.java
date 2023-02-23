package com.maan.notification.fcm.model;

import com.google.gson.annotations.SerializedName;

public class Notification {
	
	@SerializedName("sound")
	private String sound;
	@SerializedName("icon")
	private String icon;
	@SerializedName("title")
	private String title;
	@SerializedName("body")
	private String body;
	@SerializedName("content_available")
	private boolean content_available;
	@SerializedName("priority")
	private String priority;
	
	
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public boolean isContent_available() {
		return content_available;
	}
	public void setContent_available(boolean content_available) {
		this.content_available = content_available;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
