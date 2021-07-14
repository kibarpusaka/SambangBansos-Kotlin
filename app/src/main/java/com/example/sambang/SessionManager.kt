package com.example.sambang

import android.content.Context
import android.content.SharedPreferences
import com.example.sambang.Login.Data.ModelLogin
import com.google.gson.Gson

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun signOut(){
        prefs.edit().apply{
            clear()
        }.apply()
    }



    fun saveAuthToken(token: String?) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saveUserData(user: ModelLogin){
        val editor = prefs.edit()
        val userAsJson = Gson().toJson(user)
        editor.putString("user_data", userAsJson)
        editor.apply()
    }

    fun getUserToken() : String {
        if(prefs.getString(USER_TOKEN, "").isNullOrEmpty()){
            return ""
        }
        return prefs.getString(USER_TOKEN, "")!!
    }

    fun getUserData(): ModelLogin? {
        val user = prefs.getString("user_data", "")
        if (user.isNullOrEmpty()) {
            return null
        }
        return Gson().fromJson(user, ModelLogin::class.java)
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}