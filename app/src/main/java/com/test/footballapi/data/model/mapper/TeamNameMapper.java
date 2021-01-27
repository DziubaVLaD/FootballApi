package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.TeamNameResponse;
import com.test.footballapi.data.model.client.TeamName;

public class TeamNameMapper {
    public TeamName transform(TeamNameResponse teamNameResponse) {
        TeamName teamName = new TeamName();
        teamName.setId(teamNameResponse.getId());
        teamName.setName(teamNameResponse.getName());
        return teamName;
    }
}
