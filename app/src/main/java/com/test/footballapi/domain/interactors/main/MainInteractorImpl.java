package com.test.footballapi.domain.interactors.main;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.Matches;
import com.test.footballapi.data.model.client.Team;
import com.test.footballapi.data.repositories.main.MainRepository;
import com.test.footballapi.utils.CalculateDateInterval;
import com.test.footballapi.utils.CalculateWinnerTeam;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainInteractorImpl implements MainInteractor {
    private MainRepository mainRepository;
    private String startDateCompetition;
    private String endDateCompetition;
    private int idCompetition;

    public MainInteractorImpl(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public boolean isNetworkConnected() {
        return mainRepository.isNetworkConnected();
    }

    @Override
    public Flowable<NetworkEvent> subscribeOnNetworkEvents() {
        return mainRepository.subscribeOnNetworkEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void registerNetworkCallback() {
        mainRepository.registerNetworkCallback();
    }

    @Override
    public void unregisterNetworkCallback() {
        mainRepository.unregisterNetworkCallback();
    }

    public String getStartDate() {
        return startDateCompetition;
    }

    public String getEndDate() {
        return endDateCompetition;
    }

    @Override
    public Single<CompetitionInfo> getInfoAboutCompetition() {
        return mainRepository.getInfoAboutCompetition()
                .map(competitionInfo -> {
                    idCompetition = competitionInfo.getId();
                    endDateCompetition = CalculateDateInterval.calculateLastDay(competitionInfo.getSeasons().get(0).getEndDate());
                    startDateCompetition = CalculateDateInterval.calculateFirstDay(competitionInfo.getSeasons().get(0).getStartDate()
                    );
                    return competitionInfo;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<AllMatchesForParticularCompetition> getBestTeam() {
        return mainRepository.getBestTeamLast30Days(startDateCompetition, endDateCompetition, idCompetition)
                .map(allMatchesForParticularCompetition -> {
                    for (Matches matches : allMatchesForParticularCompetition.getMatches()) {
                        CalculateWinnerTeam.checkWinnerTeam(matches);
                    }
                    return allMatchesForParticularCompetition;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Team> getInfoAboutBestTeam() {
        return mainRepository.getInfoAboutBestTeam(CalculateWinnerTeam.bestTeamList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
