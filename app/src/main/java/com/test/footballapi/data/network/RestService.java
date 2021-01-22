package com.test.footballapi.data.network;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.CompetitionInfoResponse;
import com.test.footballapi.data.model.CompetitionResponse;
import com.test.footballapi.data.model.TeamResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {
    @GET("competitions/{id}/matches")
    Single<AllMatchesForParticularCompetitionResponse> getAllMatchesForParticularCompetition(
            @Header("X-Auth-Token") String token, @Path("id") String id, @Query("dateFrom") String dateFrom, @Query("dateTo") String dateTo);

    @GET("teams/{id}")
    Single<TeamResponse> getInfoAboutBestTeam(
            @Header("X-Auth-Token") String token, @Path("id") String id);

    @GET("competitions/{id}")
    Single<CompetitionInfoResponse> getInfoAboutCompetition(
            @Header("X-Auth-Token") String token, @Path("id") String id);

}