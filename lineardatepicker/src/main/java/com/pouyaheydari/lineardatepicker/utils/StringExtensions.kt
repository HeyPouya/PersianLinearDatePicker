package com.pouyaheydari.lineardatepicker.utils

fun String.toPersianNumber(): String {
    var newValue = replace("1", "۱")
    newValue = newValue.replace("2", "۲")
    newValue = newValue.replace("3", "۳")
    newValue = newValue.replace("4", "۴")
    newValue = newValue.replace("5", "۵")
    newValue = newValue.replace("6", "۶")
    newValue = newValue.replace("7", "۷")
    newValue = newValue.replace("8", "۸")
    newValue = newValue.replace("9", "۹")
    newValue = newValue.replace("0", "۰")
    return newValue
}
