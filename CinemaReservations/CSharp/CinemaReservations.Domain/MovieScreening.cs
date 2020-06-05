using System;
using System.Collections.Generic;

namespace CinemaReservations.Domain
{
    public class MovieScreening  {
        public readonly Dictionary<string, Row> Rows;
        private int MAXIMUM_NUMBER_OF_ALLOWED_TICKETS = 8;

        public MovieScreening(Dictionary<String, Row> rows) {
            Rows = rows;
        }
        public SeatsAllocated allocateSeats(AllocateSeats allocateSeats)
        {
            if (allocateSeats.PartyRequested > MAXIMUM_NUMBER_OF_ALLOWED_TICKETS) {
                return new TooManyTicketsRequested(allocateSeats.PartyRequested);
            }

            var allocation = new SeatAllocation(allocateSeats.PartyRequested);

            foreach (var row in Rows)
            {
                foreach (Seat seat in row.Value.Seats)
                {
                    if (seat.IsAvailable)
                    {
                        allocation.AddSeat(seat);

                        if(allocation.IsFulfilled) {
                            return new SeatsAllocated(allocateSeats.PartyRequested, allocation.AllocatedSeats);
                        }
                    }
                }
            }
            return new NoPossibleAllocationsFound(allocateSeats.PartyRequested);;
        }
    }
}
