package dev.nightzen.cinema;

import dev.nightzen.cinema.dto.TicketPurchaseDto;
import dev.nightzen.cinema.dto.TicketReturnDto;
import dev.nightzen.cinema.exceptions.WrongPasswordException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    private final Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public TicketPurchaseDto purchaseTicket(@RequestBody Map<String, Integer> request) {
        int row = request.get("row");
        int column = request.get("column");
        return cinema.purchaseTicket(row, column);
    }

    @PostMapping("/return")
    public TicketReturnDto returnTicket(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        return cinema.returnTicket(token);
    }

    @PostMapping("/stats")
    public CinemaStats getStats(@RequestParam(required = false) String password) {
        if (!"super_secret".equals(password)) {
            throw new WrongPasswordException();
        }

        return cinema.getStats();
    }
}