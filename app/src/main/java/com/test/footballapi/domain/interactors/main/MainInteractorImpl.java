package com.test.footballapi.domain.interactors.main;

import android.util.Log;

import com.test.footballapi.data.model.NetworkEvent;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.data.model.client.Matches;
import com.test.footballapi.data.model.client.Team;
import com.test.footballapi.data.model.client.TeamList;
import com.test.footballapi.data.repositories.main.MainRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainInteractorImpl implements MainInteractor {
    private MainRepository mainRepository;
    private String startDateCompetition;
    private String endDateCompetition;
    private int idCompetition;
    Map<Integer, Integer> teamsHashMap = new HashMap<>();
    private static final String AWAY_TEAM = "AWAY_TEAM";
    private static final String HOME_TEAM = "HOME_TEAM";

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
    public Single<CompetitionInfo> getInfoAboutCompetition() {
        return mainRepository.getInfoAboutCompetition()
                .map(competitionInfo -> {
                    //TODO Refactor calculate dates
                    idCompetition = competitionInfo.getId();
                    endDateCompetition = calculateLastDay(competitionInfo.getSeasons().get(0).getEndDate());
                    startDateCompetition = calculateFirstDay(competitionInfo.getSeasons().get(0).getStartDate()
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
                        checkWinnerTeam(matches);
                    }
                    return allMatchesForParticularCompetition;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<TeamList> getInfoAboutBestTeam() {
        return mainRepository.getInfoAboutBestTeam(bestTeamList())
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

    private void checkWinnerTeam(Matches matches) {
        if (matches.getScore().getWinner() != null) {
            if (matches.getScore().getWinner().equals(HOME_TEAM)) {
                addWinnerTeam(matches.getWinnerHomeTeam().getId());
            } else if (matches.getScore().getWinner().equals(AWAY_TEAM)) {
                addWinnerTeam(matches.getWinnerAwayTeam().getId());
            }
        }
    }

    private void addWinnerTeam(int idTeam) {
        if (teamsHashMap.containsKey(idTeam)) {
            teamsHashMap.put(idTeam, teamsHashMap.get(idTeam) + 1);
        } else {
            teamsHashMap.put(idTeam, 1);
        }
        Log.i("HASHMAP", "HASHMAP " + teamsHashMap);
    }

    private List<Integer> bestTeamList() {
        int bestCountWinner = 0;
        ArrayList idBestTeamsList = new ArrayList();
        for (int key : teamsHashMap.keySet()) {
            int value = teamsHashMap.get(key);
            if (value == bestCountWinner) {
                idBestTeamsList.add(key);

            } else if (value > bestCountWinner) {
                bestCountWinner = value;
                idBestTeamsList.clear();
                idBestTeamsList.add(key);
            }
        }
        Log.i("List", "idBestTeamsList " + idBestTeamsList);
        return idBestTeamsList;
    }

    private String calculateLastDay(String endDateCompetition) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDateCompetitionDate = sdf.parse(endDateCompetition);
        Date currentDate = new Date();
        if (endDateCompetitionDate.compareTo(currentDate) >= 0) {
            return convertDateToString(currentDate);
        } else {
            return convertDateToString(endDateCompetitionDate);
        }
    }

    private String calculateFirstDay(String startDateCompetitionString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startCompetitionDate = sdf.parse(startDateCompetitionString);
        Date currentDate = new Date();
        if (startCompetitionDate.compareTo(currentDate) < 0) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -30);
            return sdf.format(new Date(cal.getTimeInMillis()));
        } else {
            return convertDateToString(startCompetitionDate);
        }
    }

    private String convertDateToString(Date date) {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String todayAsString = df.format(date);
        return todayAsString;
    }
}
