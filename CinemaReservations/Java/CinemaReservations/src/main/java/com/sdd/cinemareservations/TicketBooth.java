package com.sdd.cinemareservations;

public class TicketBooth {
    private MovieScreeningRepository movieScreeningRepository;

    public TicketBooth(MovieScreeningRepository repo)
    {
        this.movieScreeningRepository = repo;
    }

    public SeatsAllocated allocateSeats(AllocateSeats allocateSeats) throws TooManyTicketsRequested, NoPossibleAllocationsFound, NoMovieScreeningFound {
        MovieScreening movieScreening = movieScreeningRepository.findMovieScreeningById(allocateSeats.showId());
        return movieScreening.allocateSeats(allocateSeats);
    }
}
