package com.example.calculator

import android.app.Application
import androidx.room.Room

import com.example.calculator.common.dataAccess.OperationDatabase


class OperationApplication:Application() {
    companion object{
        lateinit var database:OperationDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database= Room.databaseBuilder(this, OperationDatabase::class.java,"db_operation").build()
    }
}