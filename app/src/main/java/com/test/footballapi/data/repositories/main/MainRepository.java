package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.Team;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainRepository {
    boolean isNetworkConnected();

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getBestTeam(String startDateCompetition, String endDateCompetition, int idCompetition);

    Single<Team> getInfoAboutBestTeam(List<Integer> idBestTeamsList);

    Single<CompetitionInfo> getInfoAboutCompetition();

    void registerNetworkCallback();

    void unregisterNetworkCallback();
}

