package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchesResponse {
    @JsonProperty("utcDate")
    private String utcDate;
    @JsonProperty("score")
    private ScoreResponse score;
    @JsonProperty("homeTeam")
    private WinnerTeamResponse homeTeam;
    @JsonProperty("awayTeam")
    private WinnerTeamResponse awayTeam;

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

    public WinnerTeamResponse getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(WinnerTeamResponse homeTeam) {
        this.homeTeam = homeTeam;
    }

    public WinnerTeamResponse getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(WinnerTeamResponse awayTeam) {
        this.awayTeam = awayTeam;
    }
}
