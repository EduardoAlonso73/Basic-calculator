package com.example.calculator.mainModul.model

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.example.calculator.OperationApplication
import com.example.calculator.common.entities.OperationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository {

    private val roomDatabase = RoomDatabase()
    suspend fun addOperation(operation: OperationEntity) {
        try {
            roomDatabase.addOperation(operation)
        } catch (e: SQLiteConstraintException) {
            println("====================================" + e.message)
        }
    }

    fun getAllOperations():LiveData<MutableList<OperationEntity>> =roomDatabase.listAllOperation
}