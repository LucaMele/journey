package com.example.lucamele.journey.dtos;


import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ArrivalLocationDto extends LocationDto  {

    public Date arrival;
    public ArrivalLocationStationDto station;


    @NonNull
    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);
        return f.format(arrival);
    }

}
