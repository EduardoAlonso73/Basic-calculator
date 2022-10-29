package com.example.calculator.common.utils

import androidx.appcompat.app.AppCompatDelegate

object utlils {
    fun darkMode(isChecked:Boolean){
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}