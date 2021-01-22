package com.test.footballapi.data.model.client;

import java.util.List;

public class AllMatchesForParticularCompetition {
    private List<Matches> matches;

    public List<Matches> getMatches() {
        return matches;
    }

    public void setMatches(List<Matches> matches) {
        this.matches = matches;
    }
}
