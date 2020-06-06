package com.sdd.cinemareservations;

import lombok.Value;

import java.util.List;

@Value
public class SeatsAllocated {
    int partyRequested;
    List<Seat> reservedSeats;
}
