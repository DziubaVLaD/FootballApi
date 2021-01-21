package com.test.footballapi.data.model.client;

import com.test.footballapi.data.model.CompetitionResponse;
import com.test.footballapi.data.model.MatchesResponse;

import java.util.List;

public class AllMatchesForParticularCompetition {
    private CompetitionResponse competition;
    private List<MatchesResponse> matches;

    public CompetitionResponse getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionResponse competition) {
        this.competition = competition;
    }

    public List<MatchesResponse> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchesResponse> matches) {
        this.matches = matches;
    }
}
