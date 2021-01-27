package com.test.footballapi.data;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.CompetitionInfoResponse;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.TeamInfoResponse;
import com.test.footballapi.data.network.NetworkManager;
import com.test.footballapi.data.network.RestService;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class DataDelegate implements DataManager {

    private final RestService restService;
    private final NetworkManager networkManager;

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
    public Single<CompetitionInfoResponse> getCompetitionInfo(String token, int id) {
        return restService.getCompetitionInfo(token, id);
    }

    @Override
    public Single<AllMatchesForParticularCompetitionResponse> getAllMatchesForParticularCompetition(String token, int id, String dateFrom, String dateTo) {
        return restService.getAllMatchesForParticularCompetition(token, id, dateFrom, dateTo);
    }

    @Override
    public Single<TeamInfoResponse> getBestTeamInfo(String token, int id) {
        return restService.getBestTeamInfo(token, id);
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