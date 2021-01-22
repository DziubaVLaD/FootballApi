package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompetitionInfoResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("seasons")
    private List<SeasonsResponse> seasons;

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

    public List<SeasonsResponse> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsResponse> seasons) {
        this.seasons = seasons;
    }
}
