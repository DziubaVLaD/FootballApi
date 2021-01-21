package com.test.footballapi.data.model.client;

import com.test.footballapi.data.model.AwayTeamResponse;
import com.test.footballapi.data.model.HomeTeamResponse;
import com.test.footballapi.data.model.ScoreResponse;
import com.test.footballapi.data.model.SeasonResponse;

public class Matches {
    private SeasonResponse season;
    private String utcDate;
    private ScoreResponse score;
    private HomeTeamResponse homeTeam;
    private AwayTeamResponse awayTeam;

    public SeasonResponse getSeason() {
        return season;
    }

    public void setSeason(SeasonResponse season) {
        this.season = season;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public ScoreResponse getScore() {
        return score;
    }

    public void setScore(ScoreResponse score) {
        this.score = score;
    }

    public HomeTeamResponse getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeamResponse homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeamResponse getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeamResponse awayTeam) {
        this.awayTeam = awayTeam;
    }
}
