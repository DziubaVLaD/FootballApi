package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.CompetitionInfoResponse;
import com.test.footballapi.data.model.client.CompetitionInfo;

public class AboutCompetitionMapper {
    SeasonsMapper seasonsMapper = new SeasonsMapper();

    public CompetitionInfo transform(CompetitionInfoResponse competitionInfoResponse) {
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setName(competitionInfoResponse.getName());
        competitionInfo.setId(competitionInfoResponse.getId());
        competitionInfo.setSeasons(seasonsMapper.transform(competitionInfoResponse.getSeasons()));
        return competitionInfo;
    }
}
