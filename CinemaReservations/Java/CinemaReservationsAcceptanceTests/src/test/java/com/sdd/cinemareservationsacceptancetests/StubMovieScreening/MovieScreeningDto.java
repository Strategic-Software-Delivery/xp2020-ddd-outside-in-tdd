package com.sdd.cinemareservationsacceptancetests.StubMovieScreening;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class MovieScreeningDto {

    @JsonProperty("Rows")
    private ImmutableMap<String, ImmutableList<SeatDto>> rows;
}
