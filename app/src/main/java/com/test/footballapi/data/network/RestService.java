package com.test.footballapi.data.network;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;
import com.test.footballapi.data.model.CompetitionInfoResponse;
import com.test.footballapi.data.model.TeamInfoResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {
    @GET("competitions/{id}/matches")
    Single<AllMatchesForParticularCompetitionResponse> getAllMatchesForParticularCompetition(
            @Header("X-Auth-Token") String token, @Path("id") int id, @Query("dateFrom") String dateFrom, @Query("dateTo") String dateTo);

    @GET("teams/{id}")
    Single<TeamInfoResponse> getBestTeamInfo(
            @Header("X-Auth-Token") String token, @Path("id") int id);

    @GET("competitions/{id}")
    Single<CompetitionInfoResponse> getCompetitionInfo(
            @Header("X-Auth-Token") String token, @Path("id") int id);

}