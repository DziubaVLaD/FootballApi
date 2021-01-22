package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AllMatchesForParticularCompetitionResponse {
    @JsonProperty("matches")
    private List<MatchesResponse> matches;

    public List<MatchesResponse> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchesResponse> matches) {
        this.matches = matches;
    }
}
