package com.test.footballapi.domain.interactors.main;

import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.Team;
import com.test.footballapi.data.model.client.TeamList;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainInteractor {
    boolean isNetworkConnected();

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getBestTeam();

    Single<TeamList> getInfoAboutBestTeam();

    Single<CompetitionInfo> getInfoAboutCompetition();

    void registerNetworkCallback();

    void unregisterNetworkCallback();
}
