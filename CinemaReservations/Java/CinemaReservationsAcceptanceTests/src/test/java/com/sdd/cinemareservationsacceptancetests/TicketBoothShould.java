package com.sdd.cinemareservationsacceptancetests;

import com.sdd.cinemareservations.*;
import com.sdd.cinemareservationsacceptancetests.StubMovieScreening.StubMovieScreeningRepository;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

public class TicketBoothShould {


    @Test
    public void reserve_one_seat_when_available() throws IOException, NoMovieScreeningFound, TooManyTicketsRequested, NoPossibleAllocationsFound {
        String showId = "1";
        int partyRequested = 1;

        MovieScreeningRepository repository =  new StubMovieScreeningRepository();
        TicketBooth ticketBooth = new TicketBooth(repository);

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(showId, partyRequested));

        assertThat(seatsAllocated.getReservedSeats()).hasSize(1);
        assertThat(seatsAllocated.getReservedSeats().get(0).toString()).isEqualTo("A3");
    }

    @Test(expected = NoPossibleAllocationsFound.class)
    public void return_SeatsNotAvailable_when_all_seats_are_unavailable() throws IOException, NoMovieScreeningFound, TooManyTicketsRequested, NoPossibleAllocationsFound {
        String showId = "5";
        int partyRequested = 1;

        MovieScreeningRepository repository =  new StubMovieScreeningRepository();
        TicketBooth ticketBooth = new TicketBooth(repository);

        ticketBooth.allocateSeats(new AllocateSeats(showId, partyRequested));
    }

    @Test
    public void reserve_first_possibility_for_multiple_seats_when_available() throws IOException, NoMovieScreeningFound, TooManyTicketsRequested, NoPossibleAllocationsFound {
        String showId = "3";
        int partyRequested = 2;

        MovieScreeningRepository repository =  new StubMovieScreeningRepository();
        TicketBooth ticketBooth = new TicketBooth(repository);

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(showId, partyRequested));

        assertThat(seatsAllocated.getReservedSeats()).hasSize(2);
        assertThat(seatsAllocated.getReservedSeats().get(0).toString()).isEqualTo("A6");
        assertThat(seatsAllocated.getReservedSeats().get(1).toString()).isEqualTo("A7");
    }

    @Test(expected = TooManyTicketsRequested.class)
    public void return_TooManyTicketsRequested_when_9_tickets_are_requested() throws IOException, NoMovieScreeningFound, TooManyTicketsRequested, NoPossibleAllocationsFound {
        String showId = "5";
        int partyRequested = 9;

        MovieScreeningRepository repository =  new StubMovieScreeningRepository();
        TicketBooth ticketBooth = new TicketBooth(repository);
        ticketBooth.allocateSeats(new AllocateSeats(showId, partyRequested));
    }

}
