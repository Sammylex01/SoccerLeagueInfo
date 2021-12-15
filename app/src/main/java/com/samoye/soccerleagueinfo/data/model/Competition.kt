package com.samoye.soccerleagueinfo.data.model
import com.google.gson.annotations.SerializedName

data class Competition(
    @SerializedName("name")
    val name: String,
    @SerializedName("area/name")
    val countryName: String,
    @SerializedName("currentSeason/startDate")
    val startDate: String,
    @SerializedName("id")
    val id: String,
)

