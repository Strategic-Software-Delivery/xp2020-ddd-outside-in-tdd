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
            try
            {
                MovieScreening movieScreening = _screeningRepository.FindMovieScreeningById(allocateSeats.ShowId);
                return movieScreening.allocateSeats(allocateSeats);
            }
            catch
            {
                return new NoPossibleAllocationsFound(allocateSeats.PartyRequested);
            }
        }

    }
}
