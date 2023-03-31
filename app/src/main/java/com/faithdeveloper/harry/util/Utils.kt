package com.faithdeveloper.harry.util

object Utils {

    fun formatListOfStrings(list: List<String?>): String {
        var string = ""
        var size = list.size
        list.forEach {
            it?.let {
                string += it + if (size > 1) ", "
                else ""
                size -= 1
            }
        }
        return string
    }

}