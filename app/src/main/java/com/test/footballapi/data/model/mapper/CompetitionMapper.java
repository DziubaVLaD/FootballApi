package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.CompetitionResponse;
import com.test.footballapi.data.model.client.Competition;

public class CompetitionMapper {
    public Competition transform(CompetitionResponse competitionResponse) {
        Competition competition = new Competition();
        competition.setId(competitionResponse.getId());
        competition.setName(competitionResponse.getName());
        return competition;
    }
}
