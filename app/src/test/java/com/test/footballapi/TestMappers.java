package com.test.footballapi;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.ScoreResponse;
import com.test.footballapi.data.model.WinnerTeamResponse;
import com.test.footballapi.data.model.mapper.MatchesMapper;
import com.test.footballapi.data.model.mapper.ScoreMapper;
import com.test.footballapi.data.model.mapper.WinnerTeamMapper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestMappers {
    WinnerTeamMapper winnerTeamMapper;
    ScoreMapper scoreMapper;
    MatchesMapper matchesMapper;
    List<MatchesResponse> matchesResponseList;
    MatchesResponse matchesResponse;
    WinnerTeamResponse winnerHomeTeamResponse;
    WinnerTeamResponse winnerAwayTeamResponse;
    ScoreResponse scoreResponse;
    int idHomeTeam = 1;
    int idAwayTeam = 2;
    String nameHomeTeam = "HOME_TEAM";
    String nameAwayTeam = "AWAY_TEAM";
    String teamWinner = "HOME_TEAM";

    @Test
    public void testMatchesMapper() {
        winnerTeamMapper = new WinnerTeamMapper();
        scoreMapper = new ScoreMapper();
        getWinnerHomeTeamResponse(idHomeTeam, nameHomeTeam);
        getWinnerAwayTeamResponse(idAwayTeam, nameAwayTeam);
        getScoreResponse(teamWinner);
        getMatchesResponse();
        getMatchesListResponse();
        matchesMapper = new MatchesMapper(winnerTeamMapper, scoreMapper);

        assertEquals(matchesMapper.transform(matchesResponseList).size(), 1);
    }

    private void getWinnerHomeTeamResponse(int homeTeamId, String nameHomeTeam) {
        winnerHomeTeamResponse = new WinnerTeamResponse();
        winnerHomeTeamResponse.setId(homeTeamId);
        winnerHomeTeamResponse.setName(nameHomeTeam);
    }

    private void getWinnerAwayTeamResponse(int awayTeamId, String nameAwayTeam) {
        winnerAwayTeamResponse = new WinnerTeamResponse();
        winnerAwayTeamResponse.setId(awayTeamId);
        winnerAwayTeamResponse.setName(nameAwayTeam);
    }

    private void getScoreResponse(String teamWinner) {
        scoreResponse = new ScoreResponse();
        scoreResponse.setWinner(teamWinner);
    }

    private void getMatchesResponse() {
        matchesResponse = new MatchesResponse();
        matchesResponse.setScore(scoreResponse);
        matchesResponse.setHomeTeam(winnerHomeTeamResponse);
        matchesResponse.setAwayTeam(winnerAwayTeamResponse);
    }

    private void getMatchesListResponse() {
        matchesResponseList = new ArrayList<>();
        matchesResponseList.add(matchesResponse);
    }


}
