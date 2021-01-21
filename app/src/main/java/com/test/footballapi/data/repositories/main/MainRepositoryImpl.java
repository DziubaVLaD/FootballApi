package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.DataManager;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.Team;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.BestTeamMapper;
import com.test.footballapi.utils.AppConstants;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class MainRepositoryImpl implements MainRepository {
    private DataManager dataManager;
    private AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;
    private BestTeamMapper bestTeamMapper;


    public MainRepositoryImpl(DataManager dataManager) {
        this.dataManager = dataManager;
        allMatchesForParticularCompetitionMapper = new AllMatchesForParticularCompetitionMapper();

    }

    @Override
    public boolean isNetworkConnected() {
        return dataManager.isNetworkConnected();
    }

    @Override
    public Flowable<NetworkEvent> subscribeOnNetworkEvents() {
        return dataManager.subscribeOnNetworkEvents();
    }

    @Override
    public Single<AllMatchesForParticularCompetition> getBestTeamLast30Days() {
        return dataManager.getAllMatchesForParticularCompetition(AppConstants.X_AUTH_TOKEN, "2001", "2020-05-01", "2020-12-01")
                .map(allMatchesResponse -> allMatchesForParticularCompetitionMapper.transform(allMatchesResponse));
    }

    @Override
    public Single<Team> getInfoAboutBestTeam() {
        return dataManager.getInfoAboutBestTeam(AppConstants.X_AUTH_TOKEN, "18")
                .map(teamResponse -> bestTeamMapper.transform(teamResponse));
    }

    @Override
    public void registerNetworkCallback() {
        dataManager.registerNetworkCallback();
    }

    @Override
    public void unregisterNetworkCallback() {
        dataManager.unregisterNetworkCallback();
    }
}
