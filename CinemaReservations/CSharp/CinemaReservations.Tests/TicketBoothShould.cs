using CinemaReservations.Tests.StubMovieScreening;
using CinemaReservations.Domain;
using NFluent;
using NUnit.Framework;

namespace CinemaReservations.Tests
{
    [TestFixture]
    public class TicketBoothShould
    {
        [Test]
        public void Reserve_one_seat_when_available()
        {
            const string showId = "1";
            const int partyRequested = 1;

            IMovieScreeningRepository repository =  new StubMovieScreeningRepository();
            TicketBooth ticketBooth = new TicketBooth(repository);

            var seatsAllocated = ticketBooth.AllocateSeats(new AllocateSeats(showId, partyRequested));

            Check.That(seatsAllocated.ReservedSeats).HasSize(1);
            Check.That(seatsAllocated.ReservedSeats[0].ToString()).IsEqualTo("A3");
        }

        [Test]
        public void Return_SeatsNotAvailable_when_all_seats_are_unavailable()
        {
            Check.That(true).IsFalse();
        }

        [Test]
        public void Reserve_first_possibility_for_multiple_seats_when_available()
        {
            Check.That(true).IsFalse();
        }

        [Test]
        public void Return_TooManyTicketsRequested_when_9_tickets_are_requested()
        {
            Check.That(true).IsFalse();
        }

    }
}
