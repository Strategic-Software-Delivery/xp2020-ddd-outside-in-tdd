package com.sdd.cinemareservations;

public class TicketBooth {
    private MovieScreeningRepository movieScreeningRepository;

    public TicketBooth(MovieScreeningRepository repo)
    {
        this.movieScreeningRepository = repo;
    }

    public SeatsAllocated allocateSeats(AllocateSeats allocateSeats)
    {
        return null;
    }
}
