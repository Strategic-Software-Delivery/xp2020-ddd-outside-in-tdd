using System.Collections.Generic;

namespace CinemaReservations.Domain {
    public class Row {
        public string Name {  get; set; }
        public List<Seat> Seats { get; set; } = new List<Seat>();
    }
}