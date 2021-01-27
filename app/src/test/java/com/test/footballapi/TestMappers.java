package com.test.footballapi;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.ScoreResponse;
import com.test.footballapi.data.model.WinnerTeamResponse;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.data.model.client.Matches;
import com.test.footballapi.data.model.client.Score;
import com.test.footballapi.data.model.client.WinnerTeam;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.MatchesMapper;
import com.test.footballapi.data.model.mapper.ScoreMapper;
import com.test.footballapi.data.model.mapper.WinnerTeamMapper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestMappers {
    WinnerTeamMapper winnerTeamMapper;
    AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;
    ScoreMapper scoreMapper;
    MatchesMapper matchesMapper;
    List<MatchesResponse> matchesResponseList;
    List<Matches> matchesList;
    MatchesResponse matchesResponse;
    Matches matches;
    WinnerTeamResponse winnerHomeTeamResponse;
    WinnerTeam winnerHomeTeam;
    WinnerTeamResponse winnerAwayTeamResponse;
    WinnerTeam winnerAwayTeam;
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
    public void testAllMatchesForParticularCompetitionMapper() {
        winnerTeamMapper = new WinnerTeamMapper();
        scoreMapper = new ScoreMapper();
        getWinnerHomeTeamResponse(idHomeTeam, nameHomeTeam);
        getWinnerHomeTeam(idHomeTeam, nameHomeTeam);
        getWinnerAwayTeamResponse(idAwayTeam, nameAwayTeam);
        getWinnerAwayTeam(idAwayTeam, nameAwayTeam);
        getScoreResponse(teamWinner);
        getScore(teamWinner);
        getMatchesResponse();
        getMatches();
        getMatchesListResponse();
        getMatchesList();
        getAllMatchesForParticularCompetitionResponse();
        getAllMatchesForParticularCompetition();
        matchesMapper = new MatchesMapper(winnerTeamMapper, scoreMapper);
        allMatchesForParticularCompetitionMapper = new AllMatchesForParticularCompetitionMapper(matchesMapper);

        assertEquals(allMatchesForParticularCompetitionMapper.transform(allMatchesForParticularCompetitionResponse), allMatchesForParticularCompetition);
    }


    private void getWinnerHomeTeamResponse(int homeTeamId, String nameHomeTeam) {
        winnerHomeTeamResponse = new WinnerTeamResponse();
        winnerHomeTeamResponse.setId(homeTeamId);
        winnerHomeTeamResponse.setName(nameHomeTeam);
    }

    private void getWinnerHomeTeam(int homeTeamId, String nameHomeTeam) {
        winnerHomeTeam = new WinnerTeam();
        winnerHomeTeam.setId(homeTeamId);
        winnerHomeTeam.setName(nameHomeTeam);
    }

    private void getWinnerAwayTeamResponse(int awayTeamId, String nameAwayTeam) {
        winnerAwayTeamResponse = new WinnerTeamResponse();
        winnerAwayTeamResponse.setId(awayTeamId);
        winnerAwayTeamResponse.setName(nameAwayTeam);
    }

    private void getWinnerAwayTeam(int awayTeamId, String nameAwayTeam) {
        winnerAwayTeam = new WinnerTeam();
        winnerAwayTeam.setId(awayTeamId);
        winnerAwayTeam.setName(nameAwayTeam);
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
        matchesResponse.setHomeTeam(winnerHomeTeamResponse);
        matchesResponse.setAwayTeam(winnerAwayTeamResponse);
    }

    private void getMatches() {
        matches = new Matches();
        matches.setScore(score);
        matches.setWinnerHomeTeam(winnerHomeTeam);
        matches.setWinneAwayTeam(winnerAwayTeam);
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
