package com.stefanoq21.whatsappgemini.domain.utils

import android.content.Context
import com.stefanoq21.whatsappgemini.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object TimeDateUtils {


    fun Long?.toChatsItemString(context: Context): String {
        if (this == null) return ""

        val currentDate = Date(this)
        val cal = Calendar.getInstance()
        cal.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        // If timestamp is of today, return hh:mm format
        if (currentDate.after(cal.time)) {
            return SimpleDateFormat("kk:mm", Locale.getDefault()).format(currentDate)
        }

        cal.add(Calendar.DATE, -1)
        return if (currentDate.after(cal.time)) {
            // If timestamp is of yesterday return "Yesterday"
            context.getString(R.string.yesterday)
        } else {
            // If timestamp is older return the date, for eg. "Jul 05"
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(currentDate)
        }
    }

    fun Long?.toMessageBubbleString(): String {
        if (this == null) return ""

        val currentDate = Date(this)

        return SimpleDateFormat("kk:mm", Locale.getDefault()).format(currentDate)

    }


    fun Long?.toMessageListString(context: Context): String {
        if (this == null) return ""

        val currentDate = Date(this)
        val cal = Calendar.getInstance()
        cal.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        // If timestamp is of today, return hh:mm format
        if (currentDate.after(cal.time)) {
            return context.getString(R.string.today)
        }

        cal.add(Calendar.DATE, -1)
        return if (currentDate.after(cal.time)) {
            // If timestamp is of yesterday return "Yesterday"
            context.getString(R.string.yesterday)
        } else {
            // If timestamp is older return the date, for eg. "Jul 05"
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(currentDate)
        }
    }

    fun Long?.toDay(): Long? {
        if (this == null) return null

        val calendar = Calendar.getInstance().apply {
            timeInMillis = this@toDay
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

}