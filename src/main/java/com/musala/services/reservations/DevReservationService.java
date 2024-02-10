package com.musala.services.reservations;

import com.musala.dtos.response.*;
import com.musala.exception.ConflictException;
import com.musala.exception.NotFoundException;
import com.musala.models.Event;
import com.musala.models.Reservation;
import com.musala.models.User;
import com.musala.models.enums.ReservationStatus;
import com.musala.repositories.EventRepository;
import com.musala.repositories.ReservationRepository;
import com.musala.services.users.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.musala.models.enums.EventStatus.ENDED;
import static com.musala.util.AppUtil.NOT_FOUND;
import static com.musala.util.AppUtil.createPageRequestWith;
import static java.math.BigInteger.ONE;

@Service
@AllArgsConstructor
public class DevReservationService implements ReservationService {

    private final EventRepository eventRepository;
    
    private final ReservationRepository reservationRepository;

    private final UserService userService;

    private final ModelMapper mapper;

    @Override
    public EventReservationResponse bookReservation(Long eventId, long user) {
        //find the event
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new NotFoundException(NOT_FOUND));
        //check if the event has status of not ended
        boolean isEnded = event.getEventStatus().name().equals(ENDED.name());
        if (isEnded)  throw new ConflictException("Event has ended");
        boolean isAvailableAttendeesCount = event.getAvailableAttendeesCount() < event.getMaxAttendeesCount();
        if(isAvailableAttendeesCount) {
            Reservation reservation = buildReservation(event, user);
            event.setAvailableAttendeesCount(event.getAvailableAttendeesCount()+ONE.intValue());
            eventRepository.save(event);
            UserResponse mappedUser = mapper.map(user, UserResponse.class);
            return EventReservationResponse.builder()
                    .reservationId(reservation.getId())
                    .name(event.getName())
                    .description(event.getDescription())
                    .category(event.getCategory())
                    .eventStatus(event.getEventStatus())
                    .eventDate(event.getEventDate())
                    .reservationStatus(ReservationStatus.BOOKED)
                    .user(mappedUser)
                    .build();
        }
       throw new ConflictException("No available bookings for event");
    }

    @Cacheable(cacheNames = "cache1", key = "'#key'")
    @Override
    public ReservationList listReservations(Integer page, Integer size) {
        System.out.println("HERE");
        PageRequest pageRequest = createPageRequestWith(page, size);
        Page<Reservation> reservationPage = reservationRepository.findAll(pageRequest);
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

    @Override
    public List<ReservationResponse> viewBookedEvent(String email, Integer page, Integer size) {
        User user = userService.getUserByEmail(email);
        Pageable pageable = createPageRequestWith(page, size);
        Page<Reservation> reservationPage = reservationRepository.findReservationByUser(user, pageable);
        List<ReservationResponse> reservations =  buildReservationResponseFrom(reservationPage);
        System.out.println(reservations);
        return reservations;
    }

    @Override
    public ReservationResponse getReservationBy(Long id) {
        return mapper.map(reservationRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format("Reservation with id %d not found", id))), ReservationResponse.class
        );
    }

    @Override
    public ApiResponse<ReservationResponse> cancelReservation(Long id) {
        Reservation foundReservation = reservationRepository.findById(id).orElseThrow(()->new NotFoundException(
                String.format("Reservation with id %d not found", id)));

        foundReservation.setReservationStatus(ReservationStatus.CANCELED);
        Reservation savedReservation = reservationRepository.save(foundReservation);
        return new ApiResponse<>(mapper.map(savedReservation, ReservationResponse.class));
    }



    private List<ReservationResponse> buildReservationResponseFrom(Page<Reservation> reservationPage) {
        return reservationPage.getContent()
                .stream()
                .map(reservation -> mapper.map(reservation, ReservationResponse.class))
                .toList();
    }

    private Reservation buildReservation(Event event, long user) {
        Reservation reservation = Reservation.builder()
                .user(null)
                .event(event)
                .reservationStatus(ReservationStatus.BOOKED)
                .build();
        return reservationRepository.save(reservation);
    }
}
