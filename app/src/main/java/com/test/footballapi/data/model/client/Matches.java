package com.test.footballapi.data.model.client;

import java.util.Objects;

public class Matches {
    private Score score;
    private TeamName homeTeam;
    private TeamName awayTeam;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public TeamName getWinnerHomeTeam() {
        return homeTeam;
    }

    public void setWinnerHomeTeam(TeamName teamName) {
        this.homeTeam = teamName;
    }

    public TeamName getWinnerAwayTeam() {
        return awayTeam;
    }

    public void setWinneAwayTeam(TeamName awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matches matches = (Matches) o;
        return Objects.equals(score, matches.score) &&
                Objects.equals(homeTeam, matches.homeTeam) &&
                Objects.equals(awayTeam, matches.awayTeam);
    }
}
