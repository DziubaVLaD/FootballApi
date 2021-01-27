package com.test.footballapi;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.ScoreResponse;
import com.test.footballapi.data.model.TeamNameResponse;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.Matches;
import com.test.footballapi.data.model.client.Score;
import com.test.footballapi.data.model.client.TeamName;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.MatchesMapper;
import com.test.footballapi.data.model.mapper.ScoreMapper;
import com.test.footballapi.data.model.mapper.TeamNameMapper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestMappers {
    TeamNameMapper teamNameMapper;
    AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;
    ScoreMapper scoreMapper;
    MatchesMapper matchesMapper;
    List<MatchesResponse> matchesResponseList;
    List<Matches> matchesList;
    MatchesResponse matchesResponse;
    Matches matches;
    TeamNameResponse homeTeamNameResponse;
    TeamName homeTeamName;
    TeamNameResponse awayTeamNameResponse;
    TeamName awayTeamName;
    AllMatchesForParticularCompetitionResponse allMatchesForParticularCompetitionResponse;
    AllMatchesForParticularCompetition allMatchesForParticularCompetition;
    ScoreResponse scoreResponse;
    Score score;
    int idHomeTeam = 1;
    int idAwayTeam = 2;
    String nameHomeTeam = "HOME_TEAM";
    String nameAwayTeam = "AWAY_TEAM";
    String teamWinner = "HOME_TEAM";

    @Test
    public void allMatchesForParticularCompetitionMapper_isCorrect() {
        teamNameMapper = new TeamNameMapper();
        scoreMapper = new ScoreMapper();
        getHomeTeamNameResponse(idHomeTeam, nameHomeTeam);
        getHomeTeamName(idHomeTeam, nameHomeTeam);
        getAwayTeamNameResponse(idAwayTeam, nameAwayTeam);
        getAwayTeamName(idAwayTeam, nameAwayTeam);
        getScoreResponse(teamWinner);
        getScore(teamWinner);
        getMatchesResponse();
        getMatches();
        getMatchesListResponse();
        getMatchesList();
        getAllMatchesForParticularCompetitionResponse();
        getAllMatchesForParticularCompetition();
        matchesMapper = new MatchesMapper(teamNameMapper, scoreMapper);
        allMatchesForParticularCompetitionMapper = new AllMatchesForParticularCompetitionMapper(matchesMapper);

        assertEquals(allMatchesForParticularCompetitionMapper.transform(allMatchesForParticularCompetitionResponse), allMatchesForParticularCompetition);
    }


    private void getHomeTeamNameResponse(int homeTeamId, String nameHomeTeam) {
        homeTeamNameResponse = new TeamNameResponse();
        homeTeamNameResponse.setId(homeTeamId);
        homeTeamNameResponse.setName(nameHomeTeam);
    }

    private void getHomeTeamName(int homeTeamId, String nameHomeTeam) {
        homeTeamName = new TeamName();
        homeTeamName.setId(homeTeamId);
        homeTeamName.setName(nameHomeTeam);
    }

    private void getAwayTeamNameResponse(int awayTeamId, String nameAwayTeam) {
        awayTeamNameResponse = new TeamNameResponse();
        awayTeamNameResponse.setId(awayTeamId);
        awayTeamNameResponse.setName(nameAwayTeam);
    }

    private void getAwayTeamName(int awayTeamId, String nameAwayTeam) {
        awayTeamName = new TeamName();
        awayTeamName.setId(awayTeamId);
        awayTeamName.setName(nameAwayTeam);
    }

    private void getScoreResponse(String teamWinner) {
        scoreResponse = new ScoreResponse();
        scoreResponse.setWinner(teamWinner);
    }

    private void getScore(String teamWinner) {
        score = new Score();
        score.setWinner(teamWinner);
    }

    private void getMatchesResponse() {
        matchesResponse = new MatchesResponse();
        matchesResponse.setScore(scoreResponse);
        matchesResponse.setHomeTeam(homeTeamNameResponse);
        matchesResponse.setAwayTeam(awayTeamNameResponse);
    }

    private void getMatches() {
        matches = new Matches();
        matches.setScore(score);
        matches.setHomeTeamName(homeTeamName);
        matches.setAwayTeamName(awayTeamName);
    }

    private void getMatchesListResponse() {
        matchesResponseList = new ArrayList<>();
        matchesResponseList.add(matchesResponse);
    }

    private void getMatchesList() {
        matchesList = new ArrayList<>();
        matchesList.add(matches);
    }

    private void getAllMatchesForParticularCompetitionResponse() {
        allMatchesForParticularCompetitionResponse = new AllMatchesForParticularCompetitionResponse();
        allMatchesForParticularCompetitionResponse.setMatches(matchesResponseList);
    }

    private void getAllMatchesForParticularCompetition() {
        allMatchesForParticularCompetition = new AllMatchesForParticularCompetition();
        allMatchesForParticularCompetition.setMatches(matchesList);
    }


}
