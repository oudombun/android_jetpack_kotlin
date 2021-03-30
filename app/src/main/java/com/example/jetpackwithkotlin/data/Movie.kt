package com.example.jetpackwithkotlin.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "movies")
@Parcelize
data class Movie(
    val title:String,
    val is_favorite:Boolean = false,
    val is_watched:Boolean = false,
    val created:Long= System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
):Parcelable{
    val createdDateFormat:String
        get() = DateFormat.getDateInstance().format(created);
}