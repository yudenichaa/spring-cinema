package dev.nightzen.cinema;

import com.fasterxml.jackson.annotation.JsonGetter;

public class CinemaStats {
    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public CinemaStats(int currentIncome, int numberOfAvailableSeats, int numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public void purchaseTicket(Seat seat) {
        ++numberOfPurchasedTickets;
        --numberOfAvailableSeats;
        currentIncome += seat.getPrice();
    }

    public void returnTicket(Seat seat) {
        --numberOfPurchasedTickets;
        ++numberOfAvailableSeats;
        currentIncome -= seat.getPrice();
    }

    @JsonGetter("current_income")
    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    @JsonGetter("number_of_available_seats")
    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    @JsonGetter("number_of_purchased_tickets")
    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets(int numberOfPurchasedTickets) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }
}
