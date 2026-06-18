package com.poomani.tv.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "favorites")
data class FavoriteEntity(@PrimaryKey val id: String, val title: String, val type: String, val posterUrl: String?)

@Entity(tableName = "continue_watching")
data class ContinueWatchingEntity(@PrimaryKey val id: String, val title: String, val progress: Long, val totalDuration: Long, val streamUrl: String)

@Entity(tableName = "search_index", primaryKeys = ["id", "type"])
data class SearchIndexEntity(val id: String, val title: String, val type: String)

@Dao
interface TvDao {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteFavorite(id: String)

    @Query("SELECT * FROM continue_watching ORDER BY id DESC")
    fun getContinueWatching(): Flow<List<ContinueWatchingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: ContinueWatchingEntity)

    @Query("SELECT * FROM search_index WHERE title LIKE '%' || :query || '%'")
    suspend fun searchLocal(query: String): List<SearchIndexEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchIndex(items: List<SearchIndexEntity>)
}

@Database(entities = [FavoriteEntity::class, ContinueWatchingEntity::class, SearchIndexEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tvDao(): TvDao
}
