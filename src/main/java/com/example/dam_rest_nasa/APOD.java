package com.example.dam_rest_nasa;

import java.time.LocalDate;

public class APOD {
    private final String url;
    private final LocalDate date;

    public APOD(String url, LocalDate date) {
        this.url = url;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getDate() {
        return date;
    }
}
