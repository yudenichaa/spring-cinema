package dev.nightzen.cinema.dto;

import dev.nightzen.cinema.Seat;
import com.fasterxml.jackson.annotation.JsonGetter;

public class TicketReturnDto {
    private Seat returnedTicket;

    @JsonGetter("returned_ticket")
    public Seat getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    public TicketReturnDto(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
}
