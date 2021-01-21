package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreResponse {
    @JsonProperty("winner")
    private String winner;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
