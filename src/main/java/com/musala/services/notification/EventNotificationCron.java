package com.musala.services.notification;

import com.musala.dtos.request.EmailNotificationRequest;
import com.musala.dtos.response.EventResponse;
import com.musala.models.Event;
import com.musala.models.Reservation;
import com.musala.models.User;
import com.musala.services.events.EventService;
import com.musala.services.reservations.ReservationService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.musala.util.AppUtil.*;

@Slf4j
@Component
@AllArgsConstructor
public class EventNotificationCron {
    private final EventService eventService;
    private final ReservationService reservationService;
    private ModelMapper modelMapper;
    private final NotificationService notificationService;
    private final Configuration freemarkerConfig;
    @Scheduled(cron = "0 0 0 * * *") // Runs every mid-night
    public void sendEventNotifications() {
        log.info("::::::::SCHEDULER STARTED::::::::");
        List<EventResponse> upcomingEvents = eventService.getAllEventsFor(LocalDate.now());
        System.out.println(upcomingEvents);
        upcomingEvents.forEach(
                eventResponse -> {
                    List<Reservation> reservations = reservationService.getReservationsFor(modelMapper.map(eventResponse, Event.class));
                    reservations
                        .forEach(reservation -> {
                            User user = reservation.getUser();
                            try {
                                Template template = freemarkerConfig.getTemplate(NOTIFICATION_TEMPLATE);
                                String processedHtml =
                                                    FreeMarkerTemplateUtils
                                                        .processTemplateIntoString(template,
                                                            prepareModel(user.getName(), reservation.getEvent().getEventDate()));
                                EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest(user.getEmail(), processedHtml);
                                String message = notificationService.sendHtmlEmail(emailNotificationRequest);
                                log.info("::::::::DONE:::::::: {}", message);
                            } catch (IOException | TemplateException e) {
                                throw new RuntimeException(e);
                            }

                        });
                }
        );

    }

    private Map<String, Object> prepareModel(String name, LocalDateTime date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        String formattedTime = date.format(timeFormatter);
        Map<String, Object> model = new HashMap<>();
        model.put(NAME, name);
        model.put(DATE, date.toLocalDate());
        model.put(TIME, formattedTime);
        return model;
    }
}
