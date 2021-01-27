package com.test.footballapi.data.model.mapper;

import com.test.footballapi.data.model.SeasonsResponse;
import com.test.footballapi.data.model.client.Seasons;

import java.util.ArrayList;
import java.util.List;

public class SeasonsMapper {
    public List<Seasons> transform(List<SeasonsResponse> seasonsResponseList) {
        List<Seasons> seasonsList = new ArrayList<>();
        for (SeasonsResponse seasonsResponse : seasonsResponseList) {
            seasonsList.add(transform(seasonsResponse));
        }
        return seasonsList;
    }

    private Seasons transform(SeasonsResponse seasonsResponse) {
        Seasons seasons = new Seasons();
        seasons.setEndDate(seasonsResponse.getEndDate());
        seasons.setId(seasonsResponse.getId());
        return seasons;
    }
}
