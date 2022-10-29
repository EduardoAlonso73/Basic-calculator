package com.example.calculator.common.dataAccess

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calculator.common.entities.OperationEntity
@Dao
interface OperationDao {
    @Query("SELECT * FROM OperationEntity ")
    fun getAllOperations(): LiveData<MutableList<OperationEntity>>
    @Insert
    suspend fun addOperation(operation: OperationEntity)
}