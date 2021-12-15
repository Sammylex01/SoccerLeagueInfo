package com.samoye.soccerleagueinfo.data.repository

import com.samoye.soccerleagueinfo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getCompetitions() = apiHelper.getCompetitions()
}