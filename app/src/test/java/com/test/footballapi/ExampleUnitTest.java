package com.test.footballapi;

import com.test.footballapi.data.model.MatchesResponse;
import com.test.footballapi.data.model.WinnerTeamResponse;
import com.test.footballapi.data.model.client.Matches;
import com.test.footballapi.data.model.client.WinnerTeam;
import com.test.footballapi.domain.interactors.main.MainInteractorImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMatchesMapper() {
        List<Matches> outputData = new ArrayList<>();
        List<MatchesResponse> inputData = new ArrayList<>();
        Matches matches = new Matches();
        MatchesResponse matchesResponse = new MatchesResponse();
        WinnerTeam winnerTeam = new WinnerTeam();
        WinnerTeamResponse winnerTeamResponse = new WinnerTeamResponse();
        winnerTeam.setId(15);
        winnerTeam.setName("1234");
        winnerTeamResponse.setId(55);
        winnerTeamResponse.setName("Hello world");
        matches.setWinnerHomeTeam(winnerTeam);
        matches.setWinneAwayTeam(winnerTeam);
        matchesResponse.setAwayTeam(winnerTeamResponse);
        matchesResponse.setHomeTeam(winnerTeamResponse);
        inputData.add(matchesResponse);
        outputData.add(matches);
        Assert.assertEquals(outputData.size(), inputData.size());
    }

    @Test
    public void testCountBestTeam() {

    }
}