package com.sdd.cinemareservations;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class Row {
    private String name;
    private List<Seat> seats;

    public Row(String name, List<Seat> seats) {
        this.name = name;
        this.seats = seats;
    }
}
