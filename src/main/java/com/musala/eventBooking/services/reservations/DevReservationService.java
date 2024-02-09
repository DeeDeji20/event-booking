package com.musala.eventBooking.services.reservations;

import com.musala.eventBooking.dtos.response.EventReservationResponse;
import com.musala.eventBooking.dtos.response.ReservationList;
import com.musala.eventBooking.dtos.response.ReservationResponse;
import com.musala.eventBooking.exception.ConflictException;
import com.musala.eventBooking.exception.NotFoundException;
import com.musala.eventBooking.models.Event;
import com.musala.eventBooking.models.Reservation;
import com.musala.eventBooking.models.enums.ReservationStatus;
import com.musala.eventBooking.repositories.EventRepository;
import com.musala.eventBooking.repositories.ReservationRepository;
import com.musala.eventBooking.services.reservations.ReservationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.musala.eventBooking.models.enums.EventStatus.ENDED;
import static com.musala.eventBooking.util.AppUtil.NOT_FOUND;
import static java.math.BigInteger.ONE;

@Service
@AllArgsConstructor
public class DevReservationService implements ReservationService {

    private final EventRepository eventRepository;
    
    private final ReservationRepository reservationRepository;

    private final ModelMapper mapper;

    @Override
    public EventReservationResponse bookReservation(Long eventId) {
        //find the event
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new NotFoundException(NOT_FOUND));
        //check if the event has status of not ended
        boolean isEnded = event.getEventStatus().name().equals(ENDED.name());
        if (isEnded)  throw new ConflictException("Event has ended");
        boolean isAvailableAttendeesCount = event.getAvailableAttendeesCount() < event.getMaxAttendeesCount();
        if(isAvailableAttendeesCount) {
            Reservation reservation = buildReservation(event);
            event.setAvailableAttendeesCount(event.getAvailableAttendeesCount()+ONE.intValue());
            eventRepository.save(event);
            return EventReservationResponse.builder()
                    .reservationId(reservation.getId())
                    .name(event.getName())
                    .description(event.getDescription())
                    .category(event.getCategory())
                    .eventStatus(event.getEventStatus())
                    .eventDate(event.getEventDate())
                    .reservationStatus(ReservationStatus.BOOKED)
                    .build();
        }
       throw new ConflictException("No available bookings for event");
    }

//    @Cacheable(cacheNames = "cache1", key = "'#key'")
    @Override
    public ReservationList listReservations(PageRequest pageable) {
        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        List<Reservation> x = reservationPage.getContent();
        System.out.println(x);
        for (Reservation y:x){
            ReservationResponse response = mapper.map(y, ReservationResponse.class);
            reservationResponses.add(response);
        }
        return new ReservationList(reservationResponses, PageRequest.of(reservationPage.getPageable().getPageNumber(),
                reservationPage.getPageable().getPageSize(), Sort.Direction.DESC, "id"), reservationPage.getTotalPages()
                );
    }

    private Reservation buildReservation(Event event) {
        Reservation reservation = Reservation.builder()
//                .user(new User())
                .reservationStatus(ReservationStatus.BOOKED)
                .build();
        return reservationRepository.save(reservation);
    }
}
