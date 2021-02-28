package com.example.p4.common

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.MenuItem
import com.example.p4.R
import java.util.*

object Utilities {

    fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getDateTimeFormat(cal: Calendar): String {
        val day = cal.get(Calendar.DAY_OF_MONTH)

        if (day == 11 || day == 12 || day == 13) {
            return Configuration.DATETIME_DISPLAY_PATTERN_OTHER
        }

        return when (day % 10) {
            1 -> Configuration.DATETIME_DISPLAY_PATTERN_ST
            2 -> Configuration.DATETIME_DISPLAY_PATTERN_ND
            3 -> Configuration.DATETIME_DISPLAY_PATTERN_RD
            else -> Configuration.DATETIME_DISPLAY_PATTERN_OTHER
        }
    }
}