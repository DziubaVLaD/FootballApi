package com.test.footballapi.data.model.client;

import com.test.footballapi.data.model.SeasonsResponse;

import java.util.List;

public class CompetitionInfo {
    private int id;
    private String name;
    private List<Seasons> seasons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seasons> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Seasons> seasons) {
        this.seasons = seasons;
    }
}
