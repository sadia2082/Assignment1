package com.example.assignment1.ui.dashboard

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecord(RECORD: Record)

    @Query("SELECT * FROM RECORD ")
    fun getRecord(): Flow<List<Record>>

    @Update
    suspend fun updateRecord(RECORD: Record)

    @Delete
    suspend fun deleteRecord(RECORD: Record)

}