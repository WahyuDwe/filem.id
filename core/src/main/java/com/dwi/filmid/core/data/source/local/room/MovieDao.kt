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
    @Query("SELECT * FROM movie WHERE movie_type = 'now_playing' ")
    fun getNowPlayingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE movie_type = 'popular'")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id_movie = :movieId")
    fun getDetailMovie(movieId: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movie WHERE is_favorite_movie = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE title_movie LIKE '%' || :query || '%'")
    fun getSearchMovies(query: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(tourism: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(tourism: MovieEntity)

    @Update
    fun updateFavoriteMovies(movies: MovieEntity)
}