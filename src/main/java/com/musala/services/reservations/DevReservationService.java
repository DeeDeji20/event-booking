package com.musala.services.reservations;

import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.ReservationResponse;
import com.musala.exception.NotFoundException;
import com.musala.models.Event;
import com.musala.models.Reservation;
import com.musala.models.User;
import com.musala.repositories.ReservationRepository;
import com.musala.services.users.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musala.models.enums.ReservationStatus.CANCELED;
import static com.musala.util.AppUtil.createPageRequestWith;

@Service
@AllArgsConstructor
public class DevReservationService implements ReservationService {
    
    private final ReservationRepository reservationRepository;

    private final UserService userService;

    private final ModelMapper mapper;


    @Override
    public void createReservationFor(Event event, int ticketCount) {
        Reservation reservation = new Reservation();
        reservation.setEvent(event);
        reservation.setCreatedAt(event.getCreatedAt());
        reservation.setUser(event.getCreatedBy());
        reservation.setUpdatedAt(event.getCreatedAt());
        reservation.setTicketCount(ticketCount);
        reservationRepository.save(reservation);
    }

    @Cacheable(cacheNames = "cache1", key = "'#key'")
    @Override
    public List<ReservationResponse> listReservations(Integer page, Integer size) {
        PageRequest pageRequest = createPageRequestWith(page, size);
        Page<Reservation> reservationPage = reservationRepository.findAll(pageRequest);
        List<Reservation> reservations = reservationPage.getContent();
        return reservations.stream().map(reservation -> mapper.map(reservation, ReservationResponse.class)).toList();
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
        return mapper.map(findBy(id), ReservationResponse.class);
    }

    private Reservation findBy(Long id){
        return reservationRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format("Reservation with id %d not found", id)));
    }

    @Override
    public ApiResponse<ReservationResponse> cancelReservation(Long id) {
        Reservation foundReservation = reservationRepository.findById(id).orElseThrow(()->new NotFoundException(
                String.format("Reservation with id %d not found", id)));

        Event event = foundReservation.getEvent();
        event.setAvailableAttendeesCount(event.getAvailableAttendeesCount() - foundReservation.getTicketCount());
        foundReservation.setReservationStatus(CANCELED);
        Reservation savedReservation = reservationRepository.save(foundReservation);
        return new ApiResponse<>(mapper.map(savedReservation, ReservationResponse.class));
    }



    private List<ReservationResponse> buildReservationResponseFrom(Page<Reservation> reservationPage) {
        return reservationPage.getContent()
                .stream()
                .map(reservation -> mapper.map(reservation, ReservationResponse.class))
                .toList();
    }

}
