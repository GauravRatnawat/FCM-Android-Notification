//package net.mestwin.fcmpushnotifications;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.fail;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.firstgroup.FCM.firebase.FCMInitializer;
//import com.firstgroup.FCM.firebase.FCMService;
//import com.firstgroup.FCM.model.PushNotificationRequest;
//import com.google.firebase.FirebaseApp;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class FcmPushNotificationsApplicationTests {
//
//	@Mock
//	FCMInitializer fcmInitializer;
//
//	@Mock
//	FCMService fcmClient;
//
//	@Test
//	public void contextLoads() {
//	}
//
//
//	@Test
//	public void firebaseAppInitializationTest() {
//		fcmInitializer.initialize();
//		System.out.println(FirebaseApp.getApps());
//		assertFalse(FirebaseApp.getApps().isEmpty());
//	}
//
//	@Test
//	public void sendTestMessage() {
//		PushNotificationRequest request = new PushNotificationRequest("hello", "testMessage", "testTopic");
//		Map<String, String> pushData = new HashMap<>();
//		try {
//			fcmClient.sendMessage(pushData, request);
//		} catch(Exception e) {
//			fail();
//		}
//	}
//
//}
