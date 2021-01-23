package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.WinnerTeamResponse;
import com.test.footballapi.data.model.client.WinnerTeam;

public class WinnerTeamMapper {
    public WinnerTeam transform(WinnerTeamResponse winnerTeamResponse) {
        WinnerTeam winnerTeam = new WinnerTeam();
        winnerTeam.setId(winnerTeamResponse.getId());
        winnerTeam.setName(winnerTeamResponse.getName());
        return winnerTeam;
    }
}
