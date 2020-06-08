package com.sdd.cinemareservations;

import java.util.Map;

public class MovieScreening {
    private Map<String, Row> rows;
    private final int MAXIMUM_NUMBER_OF_ALLOWED_TICKETS = 8;

    public MovieScreening(Map<String, Row> rows) {
        this.rows = rows;
    }

    public SeatsAllocated allocateSeats(AllocateSeats allocateSeats) throws TooManyTicketsRequested, NoPossibleAllocationsFound {
        if (allocateSeats.getPartyRequested() > MAXIMUM_NUMBER_OF_ALLOWED_TICKETS) {
            throw new TooManyTicketsRequested(allocateSeats.getPartyRequested());
        }

        SeatAllocation allocation = new SeatAllocation(allocateSeats.getPartyRequested());

        for (Map.Entry<String, Row> entry: rows.entrySet()) {

            for (Seat seat :  entry.getValue().getSeats()) {
                if (seat.isAvailable())
                {
                    allocation.addSeat(seat);

                    if(allocation.isFulfilled()) {
                        return new SeatsAllocated(allocateSeats.getPartyRequested(), allocation.getAllocatedSeats());
                    }
                }
            }
        }
        throw new NoPossibleAllocationsFound(allocateSeats.getPartyRequested());
    }
}
