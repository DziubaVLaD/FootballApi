package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.Team;
import com.test.footballapi.data.model.client.TeamList;

import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleSource;

public interface MainRepository {
    boolean isNetworkConnected();

    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getBestTeamLast30Days(String startDateCompetition, String endDateCompetition, int idCompetition);

    Single<TeamList> getInfoAboutBestTeam(List<Integer> idBestTeamsList);

    Single<CompetitionInfo> getInfoAboutCompetition();

    void registerNetworkCallback();

    void unregisterNetworkCallback();
}

