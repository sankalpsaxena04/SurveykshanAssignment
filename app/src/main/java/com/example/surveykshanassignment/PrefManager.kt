package com.example.surveykshanassignment


import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PrefManager {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private val gson = Gson()

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("surveykshanassignment_Prefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun clearPrefs(){
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
    fun saveData(data: dataItem) {
        var list = getData() ?: mutableListOf()
        list+=data
        val dataFinal = gson.toJson(list)
        editor.putString("dataItem", dataFinal)
        editor.apply()
    }
    fun getData(): List<dataItem>? {
        val dataString = sharedPreferences.getString("dataItem", null)
        val gson = Gson()
        val type = object : TypeToken<List<dataItem>>() {}.type
        return gson.fromJson(dataString, type)
    }

}