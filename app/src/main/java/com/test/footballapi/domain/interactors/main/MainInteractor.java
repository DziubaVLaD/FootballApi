package com.test.footballapi.domain.interactors.main;

import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.Team;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainInteractor {
    boolean isNetworkConnected();

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getBestTeamLast30Days();

    Single<Team> getInfoAboutBestTeam();

    void registerNetworkCallback();

    void unregisterNetworkCallback();
}
