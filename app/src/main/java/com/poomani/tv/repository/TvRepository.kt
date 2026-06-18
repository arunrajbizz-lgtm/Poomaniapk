package com.poomani.tv.repository

import com.poomani.tv.data.local.*
import com.poomani.tv.data.remote.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepository @Inject constructor(
    private val api: PoomaniTvApi,
    private val dao: TvDao
) {
    suspend fun getProviders(): Result<List<Provider>> = runCatching { api.getProviders() }
    suspend fun getLiveCategories(): Result<List<LiveCategory>> = runCatching { api.getLiveCategories() }
    suspend fun getLiveChannels(): Result<List<LiveChannel>> = runCatching { api.getLiveChannels() }
    suspend fun getMovies(): Result<List<Movie>> = runCatching { api.getMovies() }
    suspend fun getSeries(): Result<List<Series>> = runCatching { api.getSeries() }
    suspend fun getSeasons(seriesId: String): Result<List<Season>> = runCatching { api.getSeasons(seriesId) }
    suspend fun getEpisodes(seasonId: String): Result<List<Episode>> = runCatching { api.getEpisodes(seasonId) }

    fun getFavorites(): Flow<List<FavoriteEntity>> = dao.getFavorites()
    suspend fun addFavorite(fav: FavoriteEntity) = dao.insertFavorite(fav)
    suspend fun removeFavorite(id: String) = dao.deleteFavorite(id)

    fun getContinueWatching(): Flow<List<ContinueWatchingEntity>> = dao.getContinueWatching()
    suspend fun saveProgress(cw: ContinueWatchingEntity) = dao.saveProgress(cw)

    suspend fun buildSearchIndex(items: List<SearchIndexEntity>) = dao.insertSearchIndex(items)
    suspend fun searchLocal(query: String): List<SearchIndexEntity> = dao.searchLocal(query)
}
