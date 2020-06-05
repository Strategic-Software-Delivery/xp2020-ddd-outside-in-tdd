using System.Collections.Generic;

namespace CinemaReservations.Domain {

    public class TooManyTicketsRequested : SeatsAllocated
    {
        public TooManyTicketsRequested(int partyRequested) : base(partyRequested, new List<Seat>())
        {
        }
    }
}