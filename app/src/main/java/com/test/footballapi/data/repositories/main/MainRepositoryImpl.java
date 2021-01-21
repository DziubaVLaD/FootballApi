package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.DataManager;
import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.utils.AppConstants;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class MainRepositoryImpl implements MainRepository {
    private DataManager dataManager;
    private AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;


    public MainRepositoryImpl(DataManager dataManager) {
        this.dataManager = dataManager;
        allMatchesForParticularCompetitionMapper = new AllMatchesForParticularCompetitionMapper();

    }

    @Override
    public Flowable<NetworkEvent> subscribeOnNetworkEvents() {
        return dataManager.subscribeOnNetworkEvents();
    }

    @Override
    public Single<AllMatchesForParticularCompetition> getBestTeamLast30Days() {
        return dataManager.getAllMatchesForParticularCompetition(AppConstants.X_AUTH_TOKEN,"2020.01.01","2020.01.20")
                .map(allMatchesResponse ->allMatchesForParticularCompetitionMapper.transform(allMatchesResponse));
    }
}
