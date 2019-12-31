package com.firstgroup.FCM.model;

import java.util.List;
import java.util.Map;

public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;
    private List<String> tokenIds;
    private Map<String, String> pushData;

    public PushNotificationRequest() {
    }

    public PushNotificationRequest(String title, String messageBody, String topicName) {
        this.title = title;
        this.message = messageBody;
        this.topic = topicName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public List<String> getTokenIds() {
		return tokenIds;
	}

	public void setTokenIds(List<String> tokenIds) {
		this.tokenIds = tokenIds;
	}

	public Map<String, String> getPushData() {
		return pushData;
	}

	public void setPushData(Map<String, String> pushData) {
		this.pushData = pushData;
	}
}
