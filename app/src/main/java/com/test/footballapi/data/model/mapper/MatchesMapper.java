package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.client.Matches;

import java.util.ArrayList;
import java.util.List;

public class MatchesMapper {
    private WinnerTeamMapper winnerTeamMapper;
    private ScoreMapper scoreMapper;

    public MatchesMapper(WinnerTeamMapper winnerTeamMapper, ScoreMapper scoreMapper) {
        this.winnerTeamMapper = winnerTeamMapper;
        this.scoreMapper = scoreMapper;
    }

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
        return matches;
    }
}
