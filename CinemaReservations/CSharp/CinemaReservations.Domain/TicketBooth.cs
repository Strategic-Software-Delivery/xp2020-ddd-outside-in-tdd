namespace CinemaReservations.Domain
{
    public class TicketBooth {
        private IMovieScreeningRepository _screeningRepository;

        public TicketBooth(IMovieScreeningRepository repo)
        {
            _screeningRepository = repo;
        }

        public SeatsAllocated AllocateSeats(AllocateSeats allocateSeats)
        {
            return null;
        }

    }
}
