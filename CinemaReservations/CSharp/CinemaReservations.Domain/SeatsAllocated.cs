using System.Collections.Generic;

namespace CinemaReservations.Domain {
    public class SeatsAllocated
    {
        public int PartyRequested { get; }
        public List<Seat> ReservedSeats { get; }
        public SeatsAllocated(int partyRequested, List<Seat> reservedSeats)
        {
            PartyRequested = partyRequested;
            ReservedSeats = reservedSeats;
        }
    }
}
