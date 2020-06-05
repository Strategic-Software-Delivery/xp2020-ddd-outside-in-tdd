namespace CinemaReservations.Domain {

    public class Seat {
        public string RowName { get; }
        public uint Number { get; }
        private SeatAvailability SeatAvailability { get; set; }
        public Seat(string rowName, uint number, SeatAvailability seatAvailability)
        {
            RowName = rowName;
            Number = number;
            SeatAvailability = seatAvailability;
        }
        public override string ToString()
        {
            return $"{RowName}{Number}";
        }
    }
}

