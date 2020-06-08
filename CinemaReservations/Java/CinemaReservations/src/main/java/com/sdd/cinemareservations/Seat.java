package com.sdd.cinemareservations;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Seat {

    private String rowName;
    private int number;
    private SeatAvailability seatAvailability;

    public Seat(String rowName, int number, SeatAvailability seatAvailability) {
        this.rowName = rowName;
        this.number = number;
        this.seatAvailability = seatAvailability;
    }

    @Override
    public String toString() {
        return rowName + number;
    }
}
