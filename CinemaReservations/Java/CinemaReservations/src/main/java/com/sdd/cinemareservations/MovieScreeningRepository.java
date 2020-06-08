package com.sdd.cinemareservations;

public interface MovieScreeningRepository {
    MovieScreening findMovieScreeningById(String screeningId) throws NoMovieScreeningFound;
}
