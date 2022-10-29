package com.example.calculator.common.dataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator.common.entities.OperationEntity

@Database(entities = [OperationEntity::class], version = 1)
abstract class OperationDatabase:RoomDatabase() {
    abstract fun operationDao():OperationDao
}