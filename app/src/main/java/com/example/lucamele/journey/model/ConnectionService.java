package com.example.lucamele.journey.model;

import com.example.lucamele.journey.dtos.ConnectionContainerDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConnectionService {

    @GET("connections")
    Call<ConnectionContainerDto> searchConnections(@Query("from") String origin, @Query("to") String destination);
}
