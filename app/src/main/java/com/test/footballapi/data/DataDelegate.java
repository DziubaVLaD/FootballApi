package com.test.footballapi.data;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.CompetitionInfoResponse;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.TeamResponse;
import com.test.footballapi.data.network.NetworkManager;
import com.test.footballapi.data.network.RestService;

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
    public boolean isNetworkConnected() {
        return networkManager.isConnected();
    }

    @Override
    public Single<AllMatchesForParticularCompetitionResponse> getAllMatchesForParticularCompetition(String token, String id, String dateFrom, String dateTo) {
        return restService.getAllMatchesForParticularCompetition(token, id, dateFrom, dateTo);
    }

    @Override
    public Single<TeamResponse> getInfoAboutBestTeam(String token, String id) {
        return restService.getInfoAboutBestTeam(token, id);
    }

    @Override
    public Single<CompetitionInfoResponse> getInfoAboutCompetition(String token, String id) {
        return restService.getInfoAboutCompetition(token, id);
    }

    @Override
    public Flowable<NetworkEvent> subscribeOnNetworkEvents() {
        return networkManager.subscribeOnNetworkEvents();
    }

    @Override
    public void registerNetworkCallback() {
        networkManager.registerNetworkCallback();
    }

    @Override
    public void unregisterNetworkCallback() {
        networkManager.unregisterNetworkCallback();
    }
}