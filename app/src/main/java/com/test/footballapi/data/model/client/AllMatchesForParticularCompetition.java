package com.test.footballapi.data.model.client;

import java.util.List;
import java.util.Objects;

public class AllMatchesForParticularCompetition {
    private List<Matches> matches;

    public List<Matches> getMatches() {
        return matches;
    }

    public void setMatches(List<Matches> matches) {
        this.matches = matches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllMatchesForParticularCompetition that = (AllMatchesForParticularCompetition) o;
        return Objects.equals(matches, that.matches);
    }
}
