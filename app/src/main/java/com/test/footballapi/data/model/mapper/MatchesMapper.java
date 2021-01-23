package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.client.Matches;

import java.util.ArrayList;
import java.util.List;

public class MatchesMapper {
    WinnerTeamMapper winnerTeamMapper = new WinnerTeamMapper();
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
        matches.setWinneAwayTeam(winnerTeamMapper.transform(matchesResponse.getAwayTeam()));
        matches.setWinnerHomeTeam(winnerTeamMapper.transform(matchesResponse.getHomeTeam()));
        matches.setScore(scoreMapper.transform(matchesResponse.getScore()));
        matches.setUtcDate(matchesResponse.getUtcDate());
        return matches;
    }
}
