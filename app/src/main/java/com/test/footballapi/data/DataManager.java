package com.test.footballapi.data;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.network.RestService;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DataManager extends RestService {
    boolean isNetworkConnected();

    Single<AllMatchesForParticularCompetitionResponse> getAllMatchesForParticularCompetition(String token, int id, String dateFrom, String dateTo);

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    void registerNetworkCallback();

    void unregisterNetworkCallback();
}