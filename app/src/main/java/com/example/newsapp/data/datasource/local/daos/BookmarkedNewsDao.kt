package com.example.newsapp.data.datasource.local.daos

import androidx.room.*
import com.example.newsapp.data.datasource.local.entities.BookmarkedNewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: BookmarkedNewsEntity)
    @Delete
    suspend fun delete(news: BookmarkedNewsEntity)
    @Query("SELECT * FROM news")
    fun getAllNews() : Flow<List<BookmarkedNewsEntity>>
    @Query("SELECT title FROM news")
    fun getAllBookmarkedTitle() : Flow<List<String?>>
    @Query("SELECT * FROM news WHERE id = :id")
    fun getNews(id: Int) : Flow<BookmarkedNewsEntity>
}