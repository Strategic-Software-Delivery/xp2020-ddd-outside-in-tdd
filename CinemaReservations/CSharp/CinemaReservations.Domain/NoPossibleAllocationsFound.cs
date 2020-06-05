using System.Collections.Generic;

namespace CinemaReservations.Domain {

    public class NoPossibleAllocationsFound : SeatsAllocated
    {
        public NoPossibleAllocationsFound(int partyRequested) : base(partyRequested, new List<Seat>())
        {
        }
    }
}