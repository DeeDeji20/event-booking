package com.musala.services.notification;

import com.musala.dtos.request.EmailNotificationRequest;

public interface NotificationService {
    String sendHtmlEmail(EmailNotificationRequest emailNotificationRequest);
}
