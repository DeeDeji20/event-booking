package com.musala.services;

import com.musala.dtos.request.EmailNotificationRequest;
import com.musala.services.notification.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void testEmailNotification(){
        EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest("deolaoladeji@gmail.com", "<p>Hi dee</p>");
        String response =notificationService.sendHtmlEmail(emailNotificationRequest);
        assertThat(response.contains("successfully")).isTrue();
    }

}