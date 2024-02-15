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
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musala.exception.ExceptionMessages.RESERVATION_NOT_FOUND;
import static com.musala.models.enums.ReservationStatus.BOOKED;
import static com.musala.models.enums.ReservationStatus.CANCELED;
import static com.musala.util.AppUtil.paginateDataWith;

@EnableCaching
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
        reservation.setReservationStatus(BOOKED);
        reservationRepository.save(reservation);
    }

    @Cacheable(cacheNames = "cache1", key = "'#key'")
    @Override
    public ApiResponse<List<ReservationResponse>> listReservations(Integer page, Integer size) {
        Pageable pageRequest = paginateDataWith(page, size);
        Page<Reservation> reservationPage = reservationRepository.findAll(pageRequest);
        List<ReservationResponse> reservationResponses = buildReservationResponseFrom(reservationPage);
        return new ApiResponse<>(reservationResponses);
    }

    @Override
    public ApiResponse<List<ReservationResponse>> viewBookedEvent(String email, Integer page, Integer size) {
        User user = userService.getUserByEmail(email);
        Pageable pageable = paginateDataWith(page, size);
        Page<Reservation> reservationPage = reservationRepository.findReservationByUserV2(user,BOOKED, pageable);
        List<ReservationResponse > reservationList= buildReservationResponseFrom(reservationPage);
        return new ApiResponse<>(reservationList);
    }

    @Override
    public ReservationResponse getReservationBy(Long id) {
        return mapper.map(findBy(id), ReservationResponse.class);
    }

    @Override
    public List<Reservation> getReservationsFor(Event event) {
        return reservationRepository.findReservationByEventAndReservationStatus(event, BOOKED);
    }

    private Reservation findBy(Long id){
        return reservationRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format(RESERVATION_NOT_FOUND.getMessage(), id)));
    }

    @Override
    public ApiResponse<ReservationResponse> cancelReservation(Long id) {
        Reservation foundReservation = reservationRepository.findById(id).orElseThrow(()->new NotFoundException(
                String.format(RESERVATION_NOT_FOUND.getMessage(), id)));

        Event event = foundReservation.getEvent();
        event.setCurrentNumberOfAttendees(event.getCurrentNumberOfAttendees() - foundReservation.getTicketCount());
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
