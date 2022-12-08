package nktns.spacex.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.convertLongToTime(): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ENGLISH)
    return format.format(date)
}