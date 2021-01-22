package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.Team;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainRepository  {
    boolean isNetworkConnected();

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getBestTeamLast30Days();

    Single<Team> getInfoAboutBestTeam();

    Single<CompetitionInfo> getInfoAboutCompetition();

    void registerNetworkCallback();

    void unregisterNetworkCallback();
}

