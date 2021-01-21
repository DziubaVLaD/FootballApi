package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.ScoreResponse;
import com.test.footballapi.data.model.client.Score;

public class ScoreMapper {
    public Score transform(ScoreResponse scoreResponse) {
        Score score = new Score();
        score.setWinner(scoreResponse.getWinner());
        return score;
    }
}
