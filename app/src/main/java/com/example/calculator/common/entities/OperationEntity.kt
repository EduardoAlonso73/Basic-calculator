package com.example.calculator.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OperationEntity")
data class OperationEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var operationExpr: String,
    var resultOperation: String
)
