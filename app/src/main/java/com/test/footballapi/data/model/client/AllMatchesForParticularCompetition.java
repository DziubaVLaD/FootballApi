package com.test.footballapi.data.model.client;

import java.util.List;

public class AllMatchesForParticularCompetition {
    private Competition competition;
    private List<Matches> matches;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Matches> getMatches() {
        return matches;
    }

    public void setMatches(List<Matches> matches) {
        this.matches = matches;
    }
}
