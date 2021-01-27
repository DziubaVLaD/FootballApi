package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeasonsResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("endDate")
    private String endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
