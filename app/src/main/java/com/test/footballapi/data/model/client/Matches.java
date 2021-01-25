package com.test.footballapi.data.model.client;

public class Matches {
    private Score score;
    private WinnerTeam homeTeam;
    private WinnerTeam awayTeam;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public WinnerTeam getWinnerHomeTeam() {
        return homeTeam;
    }

    public void setWinnerHomeTeam(WinnerTeam winnerTeam) {
        this.homeTeam = winnerTeam;
    }

    public WinnerTeam getWinnerAwayTeam() {
        return awayTeam;
    }

    public void setWinneAwayTeam(WinnerTeam awayTeam) {
        this.awayTeam = awayTeam;
    }
}
