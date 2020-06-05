namespace CinemaReservations.Domain
{
    public class AllocateSeats {
        public string ShowId { get; }
        public int PartyRequested { get; }

        public AllocateSeats(string showId, int partyRequested) {
            this.ShowId = showId;
            this.PartyRequested = partyRequested;
        }
    }
}
