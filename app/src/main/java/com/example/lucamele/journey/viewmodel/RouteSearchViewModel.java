package com.example.lucamele.journey.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lucamele.journey.dtos.ConnectionContainerDto;
import com.example.lucamele.journey.controller.ConnectionRepository;

public class RouteSearchViewModel extends ViewModel {

    private ConnectionRepository connectionRepository = new ConnectionRepository();

    public LiveData<ConnectionContainerDto> searchConnections(String from, String to) {
        return this.connectionRepository.searchConnection(from, to);
    }
}
