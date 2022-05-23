package dev.nightzen.cinema.dto;

import dev.nightzen.cinema.Seat;
import com.fasterxml.jackson.annotation.JsonGetter;

public class TicketPurchaseDto {
    private String token;
    private Seat ticket;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonGetter("ticket")
    public Seat getSeat() {
        return ticket;
    }

    public void setSeat(Seat seat) {
        this.ticket = seat;
    }

    public TicketPurchaseDto(String token, Seat seat) {
        this.token = token;
        this.ticket = seat;
    }
}
