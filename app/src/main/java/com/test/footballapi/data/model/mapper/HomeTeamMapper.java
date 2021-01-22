package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.HomeTeamResponse;
import com.test.footballapi.data.model.client.HomeTeam;

public class HomeTeamMapper {
    public HomeTeam transform(HomeTeamResponse homeTeamResponse) {
        HomeTeam homeTeam = new HomeTeam();
        homeTeam.setId(homeTeamResponse.getId());
        homeTeam.setName(homeTeamResponse.getName());
        return homeTeam;
    }
}
