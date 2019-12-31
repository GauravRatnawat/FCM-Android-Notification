//package com.firstgroup.FCM.firebase;
//
//import javax.annotation.PostConstruct;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FCMInitializer {
//
//    @Value("${app.firebase-configuration-file}")
//    private String firebaseConfigPath;
//
//    Logger logger = LoggerFactory.getLogger(FCMInitializer.class);
//
//    @PostConstruct
//    public void initialize() {
//    	
//    	
////        try {
////            FirebaseOptions options = new FirebaseOptions.Builder()
////                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
////            if (FirebaseApp.getApps().isEmpty()) {
////                FirebaseApp.initializeApp(options);
////                logger.info("Firebase application has been initialized");
////            }
////        } catch (IOException e) {
////            logger.error(e.getMessage());
////        }
//    }
//   
//}
