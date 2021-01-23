package com.test.footballapi.data.model.mapper;

import android.util.Log;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.client.Matches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchesMapper {
    WinnerTeamMapper winnerTeamMapper = new WinnerTeamMapper();
    ScoreMapper scoreMapper = new ScoreMapper();
    Map<Integer, Integer> teamsHashMap = new HashMap<>();
    private static final String AWAY_TEAM = "AWAY_TEAM";
    private static final String HOME_TEAM = "HOME_TEAM";

    public List<Matches> transform(List<MatchesResponse> matchesResponseList) {
        List<Matches> matchesList = new ArrayList<>();
        for (MatchesResponse matchesResponse : matchesResponseList) {
            matchesList.add(transform(matchesResponse));
        }
        bestTeamList();
        return matchesList;
    }
    private Matches transform(MatchesResponse matchesResponse) {
        Matches matches = new Matches();
        matches.setWinneAwayTeam(winnerTeamMapper.transform(matchesResponse.getAwayTeam()));
        matches.setWinnerHomeTeam(winnerTeamMapper.transform(matchesResponse.getHomeTeam()));
        matches.setScore(scoreMapper.transform(matchesResponse.getScore()));
        matches.setUtcDate(matchesResponse.getUtcDate());
        checkWinnerTeam(matches);
        return matches;
    }

    private Matches checkWinnerTeam(Matches matches) {
        if (matches.getScore().getWinner() != null) {
            if (matches.getScore().getWinner().equals(HOME_TEAM)) {
                addWinnerTeam(matches.getWinnerHomeTeam().getId());
            } else if (matches.getScore().getWinner().equals(AWAY_TEAM)) {
                addWinnerTeam(matches.getWinnerAwayTeam().getId());
            }
        }
        return matches;
    }

    private void addWinnerTeam(int idTeam){
        if (teamsHashMap.containsKey(idTeam)) {
            teamsHashMap.put(idTeam, teamsHashMap.get(idTeam) + 1);
        } else {
            teamsHashMap.put(idTeam, 1);
        }
        Log.i("HASHMAP", "HASHMAP " + teamsHashMap);
    }

    private List<Integer> bestTeamList(){
        int bestCountWinner = 0;
        ArrayList idBestTeamsList = new ArrayList();
        for (int key : teamsHashMap.keySet()) {
            int value = teamsHashMap.get(key);
            if (value == bestCountWinner){
                idBestTeamsList.add(key);

            } else if (value > bestCountWinner){
                bestCountWinner = value;
                idBestTeamsList.clear();
                idBestTeamsList.add(key);
            }
        }
        Log.i("List", "idBestTeamsList " + idBestTeamsList);
        return idBestTeamsList;
    }
}
