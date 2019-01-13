package com.example.lucamele.journey.controller;

import android.arch.lifecycle.LiveData;

import com.example.lucamele.journey.dtos.ConnectionContainerDto;

public class ConnectionRepository {

    private ConnectionWebService webService = new ConnectionWebService();

    public LiveData<ConnectionContainerDto> searchConnection(String from, String to) {
        return webService.searchConnection(from, to);
    }
}
