package com.example.dam_rest_nasa;

import java.time.LocalDate;

public class APOD {
    private final String url;
    private final LocalDate date;
    private final String title;

    public APOD(String url, LocalDate date, String title) {
        this.url = url;
        this.date = date;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }
}
