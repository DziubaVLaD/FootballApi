package com.test.footballapi.domain.interactors.main;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.TeamInfo;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainInteractor {
    boolean isNetworkConnected();

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getAllMatchesForParticularCompetition();

    Single<TeamInfo> getBestTeamInfo();

    Single<CompetitionInfo> getCompetitionInfo();

    void registerNetworkCallback();

    void unregisterNetworkCallback();

    String getStartDate();

    String getEndDate();
}
