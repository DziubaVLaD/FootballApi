package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.AwayTeamResponse;
import com.test.footballapi.data.model.client.AwayTeam;

public class AwayTeamMapper {
    public AwayTeam transform(AwayTeamResponse awayTeamResponse) {
        AwayTeam awayTeam = new AwayTeam();
        awayTeam.setId(awayTeamResponse.getId());
        awayTeam.setName(awayTeamResponse.getName());
        return awayTeam;
    }
}
