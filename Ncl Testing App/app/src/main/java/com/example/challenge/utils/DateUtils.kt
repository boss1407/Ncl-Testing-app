package com.example.challenge.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateSting(date:String): String {
        var spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        val newDate: Date = spf.parse(date)
        spf = SimpleDateFormat("dd MMM yyyy")
        return spf.format(newDate)
    }
}