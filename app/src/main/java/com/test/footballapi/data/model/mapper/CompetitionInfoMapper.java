package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.CompetitionInfoResponse;
import com.test.footballapi.data.model.client.CompetitionInfo;
import com.test.footballapi.presentation.base.BaseMapper;

public class CompetitionInfoMapper extends BaseMapper {
    private final SeasonsMapper seasonsMapper;

    public CompetitionInfoMapper(SeasonsMapper seasonsMapper) {
        this.seasonsMapper = seasonsMapper;
    }

    public CompetitionInfo transform(CompetitionInfoResponse competitionInfoResponse) {
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setName(competitionInfoResponse.getName());
        competitionInfo.setId(competitionInfoResponse.getId());
        competitionInfo.setSeasons(seasonsMapper.transform(competitionInfoResponse.getSeasons()));
        return competitionInfo;
    }

    @Override
    public CompetitionInfoMapper provideMapper() {
        return getMapperFactory().create(CompetitionInfoMapper.class);
    }
}
