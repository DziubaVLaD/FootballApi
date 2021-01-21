package com.test.footballapi.data;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.network.NetworkManager;
import com.test.footballapi.data.network.RestService;
import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class DataDelegate implements DataManager {

    private RestService restService;

    private NetworkManager networkManager;


    public DataDelegate(RestService restService,
                        NetworkManager networkManager) {
        this.restService = restService;

        this.networkManager = networkManager;

    }

    @Override
    public Single<AllMatchesForParticularCompetitionResponse> getAllMatchesForParticularCompetition(String token, String dateFrom, String dateTo) {
        return restService.getAllMatchesForParticularCompetition(token,dateFrom,dateTo);
    }

    @Override
    public Flowable<NetworkEvent> subscribeOnNetworkEvents() {
        return networkManager.subscribeOnNetworkEvents();
    }
}