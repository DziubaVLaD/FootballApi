package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;
import com.test.footballapi.presentation.base.BaseMapper;


public class AllMatchesForParticularCompetitionMapper extends BaseMapper {
    private final MatchesMapper matchesMapper;

    public AllMatchesForParticularCompetitionMapper(MatchesMapper matchesMapper) {
        this.matchesMapper = matchesMapper;
    }

    public AllMatchesForParticularCompetition transform(AllMatchesForParticularCompetitionResponse allMatchesForParticularCompetitionResponse) {
        AllMatchesForParticularCompetition allMatchesForParticularCompetition = new AllMatchesForParticularCompetition();
        allMatchesForParticularCompetition.setMatches(matchesMapper.transform(allMatchesForParticularCompetitionResponse.getMatches()));
        return allMatchesForParticularCompetition;
    }

    @Override
    public AllMatchesForParticularCompetitionMapper provideMapper() {
        return getMapperFactory().create(AllMatchesForParticularCompetitionMapper.class);
    }
}
