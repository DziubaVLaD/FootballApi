package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.TeamInfoResponse;
import com.test.footballapi.data.model.client.TeamInfo;

public class BestTeamMapper {
    public TeamInfo transform(TeamInfoResponse teamInfoResponse) {
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setCrestUrl(teamInfoResponse.getCrestUrl());
        teamInfo.setFounded(teamInfoResponse.getFounded());
        teamInfo.setId(teamInfoResponse.getId());
        teamInfo.setName(teamInfoResponse.getName());
        teamInfo.setVenue(teamInfoResponse.getVenue());
        teamInfo.setWebsite(teamInfoResponse.getWebsite());
        teamInfo.setAddress(teamInfoResponse.getAddress());
        teamInfo.setClubColors(teamInfoResponse.getClubColors());
        teamInfo.setEmail(teamInfoResponse.getEmail());
        teamInfo.setShortName(teamInfoResponse.getShortName());
        teamInfo.setTla(teamInfoResponse.getTla());
        teamInfo.setPhone(teamInfoResponse.getPhone());
        return teamInfo;
    }
}
