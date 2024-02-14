package com.musala.services.notification;

import com.musala.dtos.request.EmailNotificationRequest;
import com.musala.models.Notification;

public interface NotificationService {
    String sendHtmlEmail(EmailNotificationRequest emailNotificationRequest);

    void createNotification(Notification notification);
}
