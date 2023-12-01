package com.dwi.filmid.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dwi.filmid.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getNowPlayingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE is_favorite_movie = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(tourism: List<MovieEntity>)

    @Update
    fun updateFavoriteMovies(movies: MovieEntity)
}