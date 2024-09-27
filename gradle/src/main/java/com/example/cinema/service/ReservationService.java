package com.example.cinema.service;

import com.example.cinema.model.Reservation;
import com.example.cinema.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(String line, String seat) {
        return reservationRepository.findByLineAndSeat(line, seat); // Zmieniono "row" na "line"
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> reserveSeat(String line, String seat) {
        Optional<Reservation> reservation = reservationRepository.findByLineAndSeat(line, seat); // Zmieniono "row" na "line"
        reservation.ifPresent(r -> {
            r.setReserved(true);
            reservationRepository.save(r);
        });
        return reservation;
    }
}