package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchesResponse {
    @JsonProperty("score")
    private ScoreResponse score;
    @JsonProperty("homeTeam")
    private TeamNameResponse homeTeam;
    @JsonProperty("awayTeam")
    private TeamNameResponse awayTeam;

    public ScoreResponse getScore() {
        return score;
    }

    public void setScore(ScoreResponse score) {
        this.score = score;
    }

    public TeamNameResponse getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamNameResponse homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamNameResponse getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamNameResponse awayTeam) {
        this.awayTeam = awayTeam;
    }
}
