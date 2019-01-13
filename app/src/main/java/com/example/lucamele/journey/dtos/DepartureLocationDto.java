package com.example.lucamele.journey.dtos;


import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DepartureLocationDto extends LocationDto {

    public Date departure;


    @NonNull
    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("EEEE dd.MM.yyyy HH:mm", Locale.GERMAN);
        return f.format(departure);
    }
}
