package com.example.lucamele.journey.controller;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.lucamele.journey.dtos.ConnectionContainerDto;
import com.example.lucamele.journey.model.ConnectionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionWebService {

    private ConnectionService service;

    public ConnectionWebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://transport.opendata.ch/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(ConnectionService.class);
    }

    public LiveData<ConnectionContainerDto> searchConnection(String from, String to) {
        final MutableLiveData<ConnectionContainerDto> data = new MutableLiveData<>();
        Call<ConnectionContainerDto> call = this.service.searchConnections(from, to);
        call.enqueue(new Callback<ConnectionContainerDto>() {
            @Override
            public void onResponse(Call<ConnectionContainerDto> call, Response<ConnectionContainerDto> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ConnectionContainerDto> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
