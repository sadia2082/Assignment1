package com.example.assignment1.ui.dashboard

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RECORD")
data class Record(
    @PrimaryKey
    @ColumnInfo(name = "NAME")
    val NAME: String,
    @ColumnInfo(name = "DESCRIPTION")
    val DESCRIPTION: String,

)