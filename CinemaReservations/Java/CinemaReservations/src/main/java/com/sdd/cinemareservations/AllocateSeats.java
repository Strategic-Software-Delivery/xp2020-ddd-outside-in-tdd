package com.sdd.cinemareservations;

import lombok.Value;

@Value
public class AllocateSeats {

    String showId;
    int partyRequested;
}
