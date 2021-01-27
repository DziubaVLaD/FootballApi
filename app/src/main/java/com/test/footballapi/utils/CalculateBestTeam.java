package com.test.footballapi.utils;

import com.test.footballapi.data.model.client.Matches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateBestTeam {
    private static final Map<Integer, Integer> teamsHashMap = new HashMap<>();
    private static final String AWAY_TEAM = "AWAY_TEAM";
    private static final String HOME_TEAM = "HOME_TEAM";

    public static void calculateWinnerTeam(Matches matches) {
        if (matches.getScore().getWinner() != null) {
            if (matches.getScore().getWinner().equals(HOME_TEAM)) {
                addWinnerTeam(matches.getWinnerHomeTeam().getId());
            } else if (matches.getScore().getWinner().equals(AWAY_TEAM)) {
                addWinnerTeam(matches.getWinnerAwayTeam().getId());
            }
        }
    }

    private static void addWinnerTeam(int idTeam) {
        if (teamsHashMap.containsKey(idTeam)) {
            teamsHashMap.put(idTeam, teamsHashMap.get(idTeam) + 1);
        } else {
            teamsHashMap.put(idTeam, 1);
        }
    }

    public static List<Integer> bestTeamsList() {
        int bestCountWinner = 0;
        ArrayList<Integer> idBestTeamsList = new ArrayList<>();
        for (int key : teamsHashMap.keySet()) {
            int value = teamsHashMap.get(key);
            if (value == bestCountWinner) {
                idBestTeamsList.add(key);

            } else if (value > bestCountWinner) {
                bestCountWinner = value;
                idBestTeamsList.clear();
                idBestTeamsList.add(key);
            }
        }
        return idBestTeamsList;
    }
}
