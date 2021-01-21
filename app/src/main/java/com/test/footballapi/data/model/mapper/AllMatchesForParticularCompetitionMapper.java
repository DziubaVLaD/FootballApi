package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.client.AllMatchesForParticularCompetition;


public class AllMatchesForParticularCompetitionMapper extends BaseMapper{
    private CompetitionMapper competitionMapper = new CompetitionMapper();
    private MatchesMapper matchesMapper = new MatchesMapper();

    public AllMatchesForParticularCompetition transform(AllMatchesForParticularCompetitionResponse allMatchesForParticularCompetitionResponse) {
    AllMatchesForParticularCompetition allMatchesForParticularCompetition = new AllMatchesForParticularCompetition();
    allMatchesForParticularCompetition.setCompetition(competitionMapper.transform(allMatchesForParticularCompetitionResponse.getCompetition()));
    allMatchesForParticularCompetition.setMatches(matchesMapper.transform(allMatchesForParticularCompetitionResponse.getMatches()));
    return allMatchesForParticularCompetition;
    }
}
