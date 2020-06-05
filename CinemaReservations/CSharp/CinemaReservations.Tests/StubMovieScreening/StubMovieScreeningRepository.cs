using System.Collections.Generic;
using System.IO;
using System.Reflection;
using System.Runtime.InteropServices;
using CinemaReservations.Domain;

namespace CinemaReservations.Tests.StubMovieScreening
{
    public class StubMovieScreeningRepository : IMovieScreeningRepository
    {
        private readonly Dictionary<string, MovieScreeningDto> _reservedSeatsRepository = new Dictionary<string, MovieScreeningDto>();
        public StubMovieScreeningRepository()
        {
            var directoryName = $"{GetExecutingAssemblyDirectoryFullPath()}\\MovieScreenings\\";

            if (RuntimeInformation.IsOSPlatform(OSPlatform.Linux) || RuntimeInformation.IsOSPlatform(OSPlatform.OSX))
            {
                directoryName = $"{GetExecutingAssemblyDirectoryFullPath()}/MovieScreenings/";
            }

            foreach (var fileFullName in Directory.EnumerateFiles($"{directoryName}"))
            {
                var fileName = Path.GetFileName(fileFullName);
                var eventId = Path.GetFileName(fileName.Split("-")[0]);

                _reservedSeatsRepository[eventId] = JsonFile.ReadFromJsonFile<MovieScreeningDto>(fileFullName);
            }
        }

        public MovieScreening FindMovieScreeningById(string screeningId)
        {
            MovieScreeningDto movieScreeningDto = _reservedSeatsRepository[screeningId];

            var rows = new Dictionary<string, Row>();
            foreach (var rowDto in movieScreeningDto.Rows)
            {
                foreach (var seatDto in rowDto.Value)
                {
                    var rowName = ExtractRowName(seatDto.Name);
                    var number = ExtractNumber(seatDto.Name);

                    var seatAvailability = ExtractAvailability(seatDto.SeatAvailability);

                    if (!rows.ContainsKey(rowName))
                    {
                        rows[rowName] = new Row();
                    }

                    rows[rowName].Seats.Add(new Seat(rowName, number, seatAvailability));
                }
            }

            return new MovieScreening(rows);
        }

        private static string GetExecutingAssemblyDirectoryFullPath()
        {
            var directoryName = Path.GetDirectoryName(Assembly.GetExecutingAssembly().CodeBase);

            if (directoryName.StartsWith(@"file:\"))
            {
                directoryName = directoryName.Substring(6);
            }

            if (directoryName.StartsWith(@"file:/"))
            {
                directoryName = directoryName.Substring(5);
            }

            return directoryName;
        }

        private static string ExtractRowName(string name)
        {
            return name[0].ToString();
        }
        
        private static uint ExtractNumber(string name)
        {
            return uint.Parse(name.Substring(1));
        }

        private static SeatAvailability ExtractAvailability(string availability)
        {
            if (availability.Equals("Available"))
            {
                return SeatAvailability.Available;
            }

            if (availability.Equals("Reserved"))
            {
                return SeatAvailability.Reserved;
            }
            return SeatAvailability.Confirmed;
            
        }
    }
}
