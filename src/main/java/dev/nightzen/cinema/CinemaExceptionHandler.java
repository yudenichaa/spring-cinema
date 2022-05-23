package dev.nightzen.cinema;

import dev.nightzen.cinema.exceptions.SeatOutOfBoundsException;
import dev.nightzen.cinema.exceptions.TicketAlreadyPurchasedException;
import dev.nightzen.cinema.exceptions.WrongPasswordException;
import dev.nightzen.cinema.exceptions.WrongTicketTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CinemaExceptionHandler {
    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<Map<String, String>> ticketAlreadyPurchasedExceptionHandler() {
        return new ResponseEntity<>(
                Map.of("error", "The ticket has been already purchased!"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatOutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> seatOutOfBoundsExceptionHandler() {
        return new ResponseEntity<>(
                Map.of("error", "The number of a row or a column is out of bounds!"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTicketTokenException.class)
    public ResponseEntity<Map<String, String>> wrongTicketTokenExceptionHandler() {
        return new ResponseEntity<>(
                Map.of("error", "Wrong token!"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String, String>> wrongPasswordExceptionHandler() {
        return new ResponseEntity<>(
                Map.of("error", "The password is wrong!"),
                HttpStatus.UNAUTHORIZED);
    }

}
