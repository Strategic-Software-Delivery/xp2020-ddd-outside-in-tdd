using System.Collections.Generic;

namespace CinemaReservations.Tests.StubMovieScreening
{
    public class MovieScreeningDto
    {
        public Dictionary<string, IReadOnlyList<SeatDto>> Rows { get; set; }
    }
}