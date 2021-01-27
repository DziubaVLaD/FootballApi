package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.TeamInfo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainRepository {
    boolean isNetworkConnected();

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getAllMatchesForParticularCompetition(String startDateCompetition, String endDateCompetition, int idCompetition);

    Single<TeamInfo> getBestTeamInfo(List<Integer> idBestTeamsList);

    Single<CompetitionInfo> getCompetitionInfo();

    void registerNetworkCallback();

    void unregisterNetworkCallback();
}

