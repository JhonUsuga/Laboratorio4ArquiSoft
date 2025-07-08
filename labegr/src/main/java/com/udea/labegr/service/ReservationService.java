package com.udea.labegr.service;

import com.udea.labegr.entity.Reservation;
import com.udea.labegr.repository.FlightRepository;
import com.udea.labegr.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    public ReservationService(ReservationRepository reservationRepository, FlightRepository flightRepository){
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }
    public Reservation reserveFlight(Long flightId, String passengerName, String seatNumber){
        return flightRepository.findById(flightId)
                .map(flight -> {
                    if(flight.getSeatsAvailable()<=0){
                        throw new RuntimeException("No seats available");
                    }
                    flight.setSeatsAvailable(flight.getSeatsAvailable()-1);

                    Reservation reservation = new Reservation();
                    reservation.setPassengerName(passengerName);
                    reservation.setSeatNumber(seatNumber);
                    reservation.setFlight(flight);
                    reservation.setReservationCode(generateReservationCode(flight.getFlightNumber()));
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(()-> new RuntimeException("flight not found"));
    }
    private String generateReservationCode(String flightNumber){
        return Optional.ofNullable(flightNumber)
                .map(number-> number + "-" + UUID.randomUUID().toString().substring(0,8).toUpperCase())
                .orElseThrow(()-> new IllegalArgumentException("flight number cannot be null"));
    }
}
