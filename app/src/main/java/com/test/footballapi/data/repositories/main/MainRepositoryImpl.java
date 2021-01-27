package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.DataManager;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.Team;
import com.test.footballapi.data.model.mapper.AboutCompetitionMapper;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.BestTeamMapper;
import com.test.footballapi.utils.AppConstants;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class MainRepositoryImpl implements MainRepository {
    private final DataManager dataManager;
    private final AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;
    private final BestTeamMapper bestTeamMapper;
    private final AboutCompetitionMapper aboutCompetitionMapper;


    public MainRepositoryImpl(DataManager dataManager, AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper, BestTeamMapper bestTeamMapper, AboutCompetitionMapper aboutCompetitionMapper) {
        this.dataManager = dataManager;
        this.allMatchesForParticularCompetitionMapper = allMatchesForParticularCompetitionMapper;
        this.bestTeamMapper = bestTeamMapper;
        this.aboutCompetitionMapper = aboutCompetitionMapper;
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
    public Single<CompetitionInfo> getInfoAboutCompetition() {
        return dataManager.getInfoAboutCompetition(AppConstants.X_AUTH_TOKEN, AppConstants.ID_BUNDESLIGA)
                .map(aboutCompetitionMapper::transform);
    }

    @Override
    public Single<AllMatchesForParticularCompetition> getBestTeam(String startDateCompetition, String endDateCompetition, int idCompetition) {
        return dataManager.getAllMatchesForParticularCompetition(AppConstants.X_AUTH_TOKEN, idCompetition, startDateCompetition, endDateCompetition)
                .map(allMatchesForParticularCompetitionMapper::transform);
    }

    @Override
    public Single<Team> getInfoAboutBestTeam(List<Integer> idBestTeamsList) {
        return dataManager.getInfoAboutBestTeam(AppConstants.X_AUTH_TOKEN, idBestTeamsList.get(0))
                .map(bestTeamMapper::transform);
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
