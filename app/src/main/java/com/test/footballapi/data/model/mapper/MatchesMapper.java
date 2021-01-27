package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.client.Matches;

import java.util.ArrayList;
import java.util.List;

public class MatchesMapper {
    private final TeamNameMapper teamNameMapper;
    private final ScoreMapper scoreMapper;

    public MatchesMapper(TeamNameMapper teamNameMapper, ScoreMapper scoreMapper) {
        this.teamNameMapper = teamNameMapper;
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
        matches.setWinneAwayTeam(teamNameMapper.transform(matchesResponse.getAwayTeam()));
        matches.setWinnerHomeTeam(teamNameMapper.transform(matchesResponse.getHomeTeam()));
        matches.setScore(scoreMapper.transform(matchesResponse.getScore()));
        return matches;
    }
}
