package com.test.footballapi.data.network;

import com.test.footballapi.data.model.AllMatchesForParticularCompetitionResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RestService {
    @GET("competitions/{id}/matches")
    Single<AllMatchesForParticularCompetitionResponse> getAllMatchesForParticularCompetition(
            @Header("Authorization") String token, @Query("dateFrom") String dateFrom, @Query("dateTo") String dateTo);
}