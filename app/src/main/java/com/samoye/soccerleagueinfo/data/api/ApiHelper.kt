package com.samoye.soccerleagueinfo.data.api

class ApiHelper (private val apiService: ApiService) {
    suspend fun getCompetitions() = apiService.getCompetitions()
}