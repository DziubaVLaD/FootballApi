package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.TeamResponse;
import com.test.footballapi.data.model.client.Team;

public class BestTeamMapper {
    public Team transform(TeamResponse teamResponse) {
        Team team = new Team();
        team.setCrestUrl(teamResponse.getCrestUrl());
        team.setFounded(teamResponse.getFounded());
        team.setId(teamResponse.getId());
        team.setName(teamResponse.getName());
        team.setVenue(teamResponse.getVenue());
        team.setWebsite(teamResponse.getWebsite());
        team.setAddress(teamResponse.getAddress());
        team.setClubColors(teamResponse.getClubColors());
        team.setEmail(teamResponse.getEmail());
        team.setShortName(teamResponse.getShortName());
        team.setTla(teamResponse.getTla());
        team.setPhone(teamResponse.getPhone());
        return team;
    }
}
