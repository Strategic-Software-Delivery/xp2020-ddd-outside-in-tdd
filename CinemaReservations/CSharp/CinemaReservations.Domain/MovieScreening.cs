using System;
using System.Collections.Generic;

namespace CinemaReservations.Domain
{
    public class MovieScreening  {
        public readonly Dictionary<string, Row> Rows;
        private int MAXIMUM_NUMBER_OF_ALLOWED_TICKETS = 8;

        public MovieScreening(Dictionary<String, Row> rows) {
            Rows = rows;
        }
        
    }
}
