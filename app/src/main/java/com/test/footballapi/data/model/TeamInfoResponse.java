package com.test.footballapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamInfoResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String shortName;
    @JsonProperty
    private String tla;
    @JsonProperty
    private String crestUrl;
    @JsonProperty
    private String address;
    @JsonProperty
    private String phone;
    @JsonProperty
    private String website;
    @JsonProperty
    private String email;
    @JsonProperty
    private int founded;
    @JsonProperty
    private String clubColors;
    @JsonProperty
    private String venue;

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

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTla() {
        return tla;
    }

    public void setTla(String tla) {
        this.tla = tla;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClubColors() {
        return clubColors;
    }

    public void setClubColors(String clubColors) {
        this.clubColors = clubColors;
    }
}
