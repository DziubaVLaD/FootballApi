package com.test.footballapi.data.model.mapper;

import android.util.Log;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.client.Matches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchesMapper {
    AwayTeamMapper awayTeamMapper = new AwayTeamMapper();
    HomeTeamMapper homeTeamMapper = new HomeTeamMapper();
    ScoreMapper scoreMapper = new ScoreMapper();

    public List<Matches> transform(List<MatchesResponse> matchesResponseList) {
        List<Matches> matchesList = new ArrayList<>();
        for (MatchesResponse matchesResponse : matchesResponseList) {
            matchesList.add(transform(matchesResponse));
        }
        return matchesList;
    }

    private Matches transform(MatchesResponse matchesResponse) {
        Matches matches = new Matches();
        matches.setAwayTeam(awayTeamMapper.transform(matchesResponse.getAwayTeam()));
        matches.setHomeTeam(homeTeamMapper.transform(matchesResponse.getHomeTeam()));
        matches.setScore(scoreMapper.transform(matchesResponse.getScore()));
        matches.setUtcDate(matchesResponse.getUtcDate());
        calculateBestTeam(matches);
        return matches;
    }

    private Matches calculateBestTeam(Matches matches) {
        Map<Integer, Integer> teamsHashMap = new HashMap<>();
        int winForAwayTeam = 0;
        int winForHomeTeam = 0;
        if (matches.getScore().getWinner().equals("HOME_TEAM")) {
            winForAwayTeam = 1;
        } else if (matches.getScore().getWinner().equals("AWAY_TEAM")) {
            winForHomeTeam = 1;
        }
        int valueAwayTeam = teamsHashMap.get(matches.getAwayTeam());
        int valueHomeTeam = teamsHashMap.get(matches.getHomeTeam());

        teamsHashMap.put(matches.getAwayTeam().getId(), valueAwayTeam + winForAwayTeam);
        teamsHashMap.put(matches.getHomeTeam().getId(), valueHomeTeam + winForHomeTeam);
        Log.i("HASHMAP " , "HASHMAP " + teamsHashMap);
        return matches;
    }
}
