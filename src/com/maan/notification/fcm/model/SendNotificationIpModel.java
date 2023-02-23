package com.maan.notification.fcm.model;

import com.google.gson.annotations.SerializedName;

public class SendNotificationIpModel {
	
	@SerializedName("to")
	private String to;
	@SerializedName("notification")
	private Notification notification;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}
