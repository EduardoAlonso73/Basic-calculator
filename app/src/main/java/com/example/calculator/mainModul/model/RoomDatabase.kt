package com.example.calculator.mainModul.model


import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.calculator.OperationApplication
import com.example.calculator.common.dataAccess.OperationDao
import com.example.calculator.common.entities.OperationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDatabase {

    private val dao: OperationDao by lazy { OperationApplication.database.operationDao() }

    suspend fun addOperation(operation: OperationEntity) = withContext(Dispatchers.IO) {
        try {
            dao.addOperation(operation)
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
        }
    }

    val listAllOperation: LiveData<MutableList<OperationEntity>> = liveData {
        val operationLiveData=dao.getAllOperations()
        emitSource(operationLiveData.map { operation->
            operation.sortedBy { it.id }.toMutableList().asReversed()
        })
    }

}