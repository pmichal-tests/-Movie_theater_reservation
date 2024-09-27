package com.example.cinema.controller;

import com.example.cinema.model.Reservation;
import com.example.cinema.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{line}/{seat}")
    public ResponseEntity<Reservation> getReservation(@PathVariable String line, @PathVariable String seat) {
        Optional<Reservation> reservation = reservationService.getReservation(line, seat); // Zmieniono "row" na "line"
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserveSeat(@RequestParam String line, @RequestParam String seat) {
        Optional<Reservation> reservation = reservationService.reserveSeat(line, seat); // Zmieniono "row" na "line"
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}