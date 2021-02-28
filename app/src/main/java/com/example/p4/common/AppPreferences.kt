package com.example.p4.common


import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringDef
import com.example.p4.common.AppApplication.Companion.context
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

class AppPreferences {
    internal constructor() {}
    private constructor(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Synchronized
    fun getBoolean(name: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(name, defaultValue)
    }

    @Synchronized
    fun getBoolean(name: String?): Boolean {
        return getBoolean(name, false)
    }

    @Synchronized
    fun getString(name: String?, defaultValue: String?): String? {
        return sharedPreferences!!.getString(name, defaultValue)
    }

    @Synchronized
    fun getLong(name: String?, defaultValue: Long): Long {
        return sharedPreferences!!.getLong(name, defaultValue)
    }

    @Synchronized
    fun getFloat(name: String?, defaultValue: Float): Float {
        return sharedPreferences!!.getFloat(name, defaultValue)
    }

    @Synchronized
    fun getInt(name: String?, defaultValue: Int): Int {
        return sharedPreferences!!.getInt(name, defaultValue)
    }

    @Synchronized
    fun setString(name: String?, s: String?, isAsync: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putString(name, s)
        save(editor, isAsync)
    }

    @Synchronized
    fun setLong(name: String?, l: Long, isAsync: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putLong(name, l)
        save(editor, isAsync)
    }

    @Synchronized
    fun setInt(name: String?, i: Int, isAsync: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putInt(name, i)
        save(editor, isAsync)
    }

    @Synchronized
    fun setBoolean(name: String?, b: Boolean, isAsync: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(name, b)
        save(editor, isAsync)
    }

    @Synchronized
    fun setFloat(name: String?, f: Float, isAsync: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putFloat(name, f)
        save(editor, isAsync)
    }

    private fun save(editor: SharedPreferences.Editor, isAsync: Boolean) {
        if (isAsync) {
            editor.apply()
        } else {
            editor.commit()
        }
    }

    @get:Synchronized
    val allSettings: Map<String, *>
        get() = sharedPreferences!!.all

    @StringDef(USER_DETAILS)
    @Retention(RetentionPolicy.SOURCE)
    private annotation class Settings
    companion object {
        private const val TAG = "AppPreferences"
        const val SHARED_PREFERENCES_NAME = "userDetails"
        const val USER_DETAILS = "user_details"
        var sharedPreferences: SharedPreferences? = null
        private var preferences: AppPreferences? = null

        @JvmStatic
        @get:Synchronized
        val instance: AppPreferences?
            get() {
                if (preferences == null) {
                    preferences = AppPreferences(context)
                }
                return preferences
            }
    }
}