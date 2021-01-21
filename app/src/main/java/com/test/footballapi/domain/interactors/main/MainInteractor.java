package com.test.footballapi.domain.interactors.main;

import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.NetworkEvent;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MainInteractor {
    Flowable<NetworkEvent> subscribeOnNetworkEvents();

    Single<AllMatchesForParticularCompetition> getBestTeamLast30Days();
}
