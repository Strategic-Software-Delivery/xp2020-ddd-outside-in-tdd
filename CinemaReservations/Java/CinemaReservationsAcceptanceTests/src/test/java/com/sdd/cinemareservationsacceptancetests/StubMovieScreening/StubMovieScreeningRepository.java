package com.sdd.cinemareservationsacceptancetests.StubMovieScreening;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;
import com.sdd.cinemareservations.*;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubMovieScreeningRepository implements MovieScreeningRepository {

    private Map<String, MovieScreeningDto> repository = new HashMap<>();

    public StubMovieScreeningRepository() throws IOException {
        String jsonDirectory = Paths.get(System.getProperty("user.dir")).getParent().getParent().getParent().toString() + "/Stubs/MovieScreenings";

        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(jsonDirectory));

        for (Path path : directoryStream) {
            String fileName = path.getFileName().toString();
            ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());
            repository.put(fileName.split("-")[0], mapper.readValue(path.toFile(), MovieScreeningDto.class));
        }
    }

    @Override
    public MovieScreening findMovieScreeningById(final String showId) throws NoMovieScreeningFound {
        MovieScreeningDto movieScreeningDto;

        if (repository.containsKey(showId)) {
            movieScreeningDto = repository.get(showId);
        } else {
            throw new NoMovieScreeningFound("No MovieScreening found with id: "+showId);
        }

        Map<String, Row> rows = new HashMap<>();

        for (Map.Entry<String, ImmutableList<SeatDto>> rowDto : movieScreeningDto.rows().entrySet()) {
            List<Seat> seats = new ArrayList<>();

            rowDto.getValue().forEach(seatDto -> {
                String rowName = rowDto.getKey();
                int number = extractNumber(seatDto.name());

                SeatAvailability seatAvailability = extractAvailability(seatDto.seatAvailability());


                seats.add(new Seat(rowName, number, seatAvailability));
            });

            rows.put(rowDto.getKey(), new Row(rowDto.getKey(), seats));
        }

        return new MovieScreening(rows);
    }

    private SeatAvailability extractAvailability(final String seatAvailability) {

        switch(seatAvailability) {
            case "Available":
                return SeatAvailability.Available;
            case "Reserved":
                return SeatAvailability.Reserved;
            case "Confirmed":
                return SeatAvailability.Confirmed;
            default:
                throw new IllegalStateException("Unexpected value: " + seatAvailability);
        }
    }

    private static int extractNumber(final String name) {
        return Integer.parseUnsignedInt(name.substring(1));
    }
}
