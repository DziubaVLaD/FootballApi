package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.client.Matches;
import com.test.footballapi.data.model.client.Season;

import java.util.ArrayList;
import java.util.List;

public class MatchesMapper {
    AwayTeamMapper awayTeamMapper = new AwayTeamMapper();
    HomeTeamMapper homeTeamMapper = new HomeTeamMapper();
    ScoreMapper scoreMapper = new ScoreMapper();
    SeasonMapper seasonMapper = new SeasonMapper();
    public List<Matches> transform(List<MatchesResponse> matchesResponseList) {
        List<Matches> matchesList = new ArrayList<>();
        for (MatchesResponse matchesResponse: matchesResponseList){
            matchesList.add(transform(matchesResponse));
        }
        return matchesList;
    }

    private Matches transform(MatchesResponse matchesResponse) {
        Matches matches = new Matches();
        matches.setAwayTeam(awayTeamMapper.transform(matchesResponse.getAwayTeam()));
        matches.setHomeTeam(homeTeamMapper.transform(matchesResponse.getHomeTeam()));
        matches.setScore(scoreMapper.transform(matchesResponse.getScore()));
        matches.setSeason(seasonMapper.transform(matchesResponse.getSeason()));
        matches.setUtcDate(matchesResponse.getUtcDate());
        return matches;
    }
}
