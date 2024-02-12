package com.musala.services.notification;

import com.musala.dtos.request.EmailNotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventNotificationService implements NotificationService{

    private final JavaMailSender javaMailSender;

    @Override
    public String sendHtmlEmail(EmailNotificationRequest emailNotificationRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try{
            mimeMessageHelper.setFrom("no-reply@email.com");
            mimeMessageHelper.setTo(emailNotificationRequest.getUserEmail());
            mimeMessageHelper.setText(emailNotificationRequest.getContent(), true);
            javaMailSender.send(mimeMessage);
            return String.format("email sent to %s successfully", emailNotificationRequest.getUserEmail());
        }catch (MessagingException exception){
            exception.printStackTrace();;
        }
        return String.format("email not sent to %s", emailNotificationRequest.getUserEmail());
    }
}
