package com.samoye.soccerleagueinfo.data.api

import com.samoye.soccerleagueinfo.data.model.Competition
import retrofit2.http.GET

interface ApiService {
    @GET("competitions")
    suspend fun getCompetitions(): List<Competition>
}