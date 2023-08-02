package com.packagename.newsapp.utils

import android.content.Context
import com.packagename.newsapp.utils.Constants.PREFS_TOKEN_FILE
import com.packagename.newsapp.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context : Context) { //here with the help of hilt we get context of application and activity

    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE) //only this file can acess thats y we use private
    //SharedPreferences is a way to store key-value pairs persistently across app sessions.

    fun saveToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)    //we have to use our token against this key user_token
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_TOKEN,null)   //here if we have token then use it or we dont have its null
    }
}