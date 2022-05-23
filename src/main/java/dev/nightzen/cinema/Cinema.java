package dev.nightzen.cinema;

import dev.nightzen.cinema.dto.TicketPurchaseDto;
import dev.nightzen.cinema.dto.TicketReturnDto;
import dev.nightzen.cinema.exceptions.SeatOutOfBoundsException;
import dev.nightzen.cinema.exceptions.TicketAlreadyPurchasedException;
import dev.nightzen.cinema.exceptions.WrongTicketTokenException;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Cinema {
    private final int totalRows;
    private final int totalColumns;
    private final Seat[][] seats;
    private final Map<String, Seat> takenSeats = new HashMap<>();
    private final CinemaStats stats;

    @JsonIgnore
    public CinemaStats getStats() {
        return stats;
    }

    @JsonGetter("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    @JsonGetter("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonGetter("available_seats")
    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();

        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (!seat.isPurchased()) {
                    availableSeats.add(seat);
                }
            }
        }

        return availableSeats;
    }

    public synchronized TicketPurchaseDto purchaseTicket(int row, int column) {
        if (row <= 0 || row > totalRows || column <= 0 || column > totalColumns) {
            throw new SeatOutOfBoundsException();
        }

        Seat seat = seats[row - 1][column - 1];

        if (seat.isPurchased()) {
            throw new TicketAlreadyPurchasedException();
        }

        seat.setPurchased(true);
        String token = UUID.randomUUID().toString();
        takenSeats.put(token, seat);
        stats.purchaseTicket(seat);
        return new TicketPurchaseDto(token, seat);
    }

    public synchronized TicketReturnDto returnTicket(String token) {
        Seat seat = takenSeats.get(token);

        if (seat == null) {
            throw new WrongTicketTokenException();
        }

        seat.setPurchased(false);
        takenSeats.remove(token);
        stats.returnTicket(seat);
        return new TicketReturnDto(seat);
    }

    private int getSeatPrice(int seatRow) {
        return seatRow < 4 ? 10 : 8;
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.seats = new Seat[totalRows][totalColumns];
        this.stats = new CinemaStats(0, totalRows * totalColumns, 0);

        for (int row = 0; row < totalRows; ++row) {
            int price = getSeatPrice(row);
            for (int column = 0; column < totalColumns; ++column) {
                seats[row][column] = new Seat(row + 1, column + 1, price, false);
            }
        }
    }
}
