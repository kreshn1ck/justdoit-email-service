package com.ubt.email.listener;

import com.ubt.email.service.EmailService;
import com.ubt.email.transport.UserTransport;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaConsumer {

    private final EmailService emailService;
    private static final Logger LOGGER = LogManager.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "USER_CREATED", containerFactory = "userKafkaListenerFactory")
    public void consumeUserJson(UserTransport user) throws Exception {
        System.out.println("Consumed JSON Message: " + user);
        LOGGER.info("Consuming new message for user creation");
        boolean emailSent;
        int retryNr = 1;
        do {
            emailSent = emailService.sendConfirmUserEmail(user.getEmail(), user.getConfirmToken(), user.getUsername());
        } while(!emailSent && retryNr++ <= 5);
        if (emailSent) {
            LOGGER.info("An email was sent");
        } else {
            LOGGER.info("Could not sent email, retrying.");
            System.out.println("Could not send email");
        }
    }
}
