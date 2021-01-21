package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.SeasonResponse;
import com.test.footballapi.data.model.client.Season;

public class SeasonMapper {
    public Season transform(SeasonResponse seasonResponse) {
        Season season = new Season();
        season.setEndDate(seasonResponse.getEndDate());
        season.setStartDate(seasonResponse.getStartDate());
        return season;
    }
}
