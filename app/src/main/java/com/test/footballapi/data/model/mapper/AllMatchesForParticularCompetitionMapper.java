package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;


public class AllMatchesForParticularCompetitionMapper extends BaseMapper{
    public AllMatchesForParticularCompetition transform(AllMatchesForParticularCompetitionResponse allMatchesForParticularCompetitionResponse) {
    AllMatchesForParticularCompetition allMatchesForParticularCompetition = new AllMatchesForParticularCompetition();
    allMatchesForParticularCompetition.setCompetition(allMatchesForParticularCompetitionResponse.getCompetition());
    allMatchesForParticularCompetition.setMatches(allMatchesForParticularCompetitionResponse.getMatches());
    return allMatchesForParticularCompetition;
    }
}
