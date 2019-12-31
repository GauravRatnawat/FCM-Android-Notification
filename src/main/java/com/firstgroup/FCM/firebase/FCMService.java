package com.firstgroup.FCM.firebase;

import com.firstgroup.FCM.model.PushNotificationRequest;
import com.google.firebase.messaging.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(data, request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response);
    }

    public void sendMessageTokens(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException, FirebaseMessagingException {
        MulticastMessage message = getPreconfiguredMessageWithDataTokens(data, request);
        List<SendResponse> response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response.get(0).getException());
    }
    public void sendMessageWithoutData(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithoutData(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
    }

    public void sendMessageToToken(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }
    private List<SendResponse> sendAndGetResponse(MulticastMessage message) throws InterruptedException, ExecutionException, FirebaseMessagingException {
        return FirebaseMessaging.getInstance().sendMulticast(message).getResponses();
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
              .build();
//        .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
//                .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build())
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }

    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
                .build();
    }

    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic())
                .build();
    }
    private MulticastMessage getPreconfiguredMessageWithDataTokens(Map<String, String> data, PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder1(request).putAllData(data).addAllTokens(request.getTokenIds())
                .build();
    }

    private MulticastMessage.Builder getPreconfiguredMessageBuilder1(PushNotificationRequest request)
    {
    	 AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
         ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
         return MulticastMessage.builder()
                 .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig);
//         setNotification(
//                 new Notification(request.getTitle(), request.getMessage()))
    }
    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }


}
