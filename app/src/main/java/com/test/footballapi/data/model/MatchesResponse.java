package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchesResponse {
    @JsonProperty("utcDate")
    private String utcDate;
    @JsonProperty("score")
    private ScoreResponse score;
    @JsonProperty("homeTeam")
    private HomeTeamResponse homeTeam;
    @JsonProperty("awayTeam")
    private AwayTeamResponse awayTeam;

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
