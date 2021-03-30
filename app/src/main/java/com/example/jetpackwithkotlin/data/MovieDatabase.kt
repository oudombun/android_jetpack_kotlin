package com.example.jetpackwithkotlin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Movie::class],version = 2)
abstract class MovieDatabase:RoomDatabase(){
    abstract fun getDao():MovieDao

    class Callback @Inject constructor(
        private val database:Provider<MovieDatabase>,
        private val applicationScope:CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().getDao()
            applicationScope.launch{
              dao.insert(Movie( "Alita",true,false))
              dao.insert(Movie( "Justice League",false,true))
              dao.insert(Movie( "Avenger Endgame",false,false))
              dao.insert(Movie( "Doctor Strange",false,true))
              dao.insert(Movie( "Thor Love and Thunder",false,false))
            }
        }
    }
}