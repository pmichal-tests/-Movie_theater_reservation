package com.example.cinema.repository;

import com.example.cinema.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByLineAndSeat(String line, String seat); // Zmieniono "row" na "line"
}