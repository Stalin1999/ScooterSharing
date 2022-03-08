package dk.itu.moapd.scootersharing

import android.icu.text.SimpleDateFormat

class Scooter(var name: String, var where: String, var timestamp: Long) {

    var simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    var dateString = simpleDateFormat.format(timestamp)

    var hourMillis = timestamp % 86400000
    var hour = hourMillis / 3600000 + 1

    var minuteMillis = timestamp % 3600000
    var minute = minuteMillis / 60000

    override fun toString () : String {
        return " $name is placed at $where the $dateString at $hour:$minute o'clock"
    }
}