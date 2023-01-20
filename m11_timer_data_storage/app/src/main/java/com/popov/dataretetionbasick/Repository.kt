package com.popov.dataretetionbasick

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Repository(context: Context) {

    private var localVariable: String? = null

    private var preferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    private fun getDataFromSharedPreference(): String?{
        return preferences.getString(KEY_LOCAL_VARIABLE, null)
    }

    private fun getDataFromLocalVariable(): String? {
        return localVariable
    }

    fun saveText(text: String) {
        if (text == ""){
            localVariable = null
        } else{
            localVariable = text
        }
        editor.putString(KEY_LOCAL_VARIABLE, localVariable)
        editor.apply()
    }

    fun clearText() {
        editor.clear()
        editor.apply()
        localVariable = null
    }

    fun getText(): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference() != null -> getDataFromSharedPreference()!!
            else -> "нет сохраненых данных"
        }
    }



    companion object {
        private const val PREFERENCE_NAME = "prefs_name"
        private const val KEY_LOCAL_VARIABLE = "key_local_variable"
    }
}