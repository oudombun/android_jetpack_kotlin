package com.example.jetpackwithkotlin.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackwithkotlin.data.MovieDao
import com.example.jetpackwithkotlin.data.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app:Application,
        callback:MovieDatabase.Callback
    ) = Room.databaseBuilder(app,MovieDatabase::class.java,"moviedb")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideDao(db:MovieDatabase) =  db.getDao()

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}