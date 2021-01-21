package com.test.footballapi.data.repositories.main;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainRepository  {
    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getBestTeamLast30Days();
}

