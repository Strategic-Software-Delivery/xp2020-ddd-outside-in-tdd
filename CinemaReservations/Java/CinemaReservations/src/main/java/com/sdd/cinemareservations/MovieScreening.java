package com.sdd.cinemareservations;

import java.util.Map;
public class MovieScreening {
    private Map<String, Row> rows;
    private final int MAXIMUM_NUMBER_OF_ALLOWED_TICKETS = 8;

    public MovieScreening(Map<String, Row> rows) {
        this.rows = rows;
    }
}
