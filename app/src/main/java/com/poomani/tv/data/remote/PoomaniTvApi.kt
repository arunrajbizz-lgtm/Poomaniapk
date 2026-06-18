package com.poomani.tv.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

data class Provider(val id: String, val name: String, val isActive: Boolean)
data class LiveCategory(val id: String, val name: String)
data class LiveChannel(val id: String, val name: String, val logo: String?, val streamUrl: String, val categoryId: String)
data class Movie(val id: String, val title: String, val poster: String?, val streamUrl: String, val categoryId: String)
data class Series(val id: String, val title: String, val poster: String?)
data class Season(val id: String, val seriesId: String, val name: String)
data class Episode(val id: String, val seasonId: String, val name: String, val streamUrl: String)

interface PoomaniTvApi {
    @GET("/api/providers")
    suspend fun getProviders(): List<Provider>

    @GET("/api/live-categories")
    suspend fun getLiveCategories(): List<LiveCategory>

    @GET("/api/live-channels")
    suspend fun getLiveChannels(): List<LiveChannel>

    @GET("/api/movies")
    suspend fun getMovies(): List<Movie>

    @GET("/api/series")
    suspend fun getSeries(): List<Series>

    @GET("/api/seasons")
    suspend fun getSeasons(@Query("seriesId") seriesId: String): List<Season>

    @GET("/api/episodes")
    suspend fun getEpisodes(@Query("seasonId") seasonId: String): List<Episode>
}
