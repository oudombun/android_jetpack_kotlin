package com.example.jetpackwithkotlin.data

import androidx.room.*
import com.example.jetpackwithkotlin.ui.movies.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie:Movie)

    @Update
    suspend fun update(movie:Movie)

    @Delete
    suspend fun delete(movie:Movie)

    @Query("SELECT * FROM movies where (is_watched != :watched or is_watched=0) and title LIKE '%' || :search || '%' order by title")
    fun getMoviesByName(search:String,watched:Boolean):Flow<List<Movie>>

    @Query("SELECT * FROM movies where (is_watched != :watched or is_watched=0) and title LIKE '%' || :search || '%' order by created")
    fun getMoviesByDate(search:String,watched:Boolean):Flow<List<Movie>>

    fun getMovies(search:String,sort:SortOrder,watched:Boolean):Flow<List<Movie>> =
        when(sort){
            SortOrder.BY_DATE-> getMoviesByDate(search,watched)
            SortOrder.BY_NAME-> getMoviesByName(search,watched)
        }
}