namespace CinemaReservations.Domain
{
    public interface IMovieScreeningRepository {
        MovieScreening FindMovieScreeningById(string screeningId);
    }
}
