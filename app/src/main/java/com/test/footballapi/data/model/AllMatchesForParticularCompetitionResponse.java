package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AllMatchesForParticularCompetitionResponse {
    @JsonProperty("competition")
    private CompetitionResponse competition;
    @JsonProperty("matches")
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
