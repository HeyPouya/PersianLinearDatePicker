# Persian Date Picker

**An easy to use Android library to pick a date.**

[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7e8f094fd77044b5b26bc6c157bfbbc3)](https://www.codacy.com/manual/SirLordPouya/AndroidAppUpdater?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=SirLordPouya/AndroidAppUpdater&amp;utm_campaign=Badge_Grade)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![](https://jitpack.io/v/SirLordPouya/PersianLinearDatePicker.svg)](https://jitpack.io/#SirLordPouya/PersianLinearDatePicker)

<p align="center">
<img src="https://github.com/SirLordPouya/PersianLinearDatePicker/blob/master/shots/icon.png" width="250">
</p>

You can set the minimum and maximum dates to be shown to the user. After user selects the desired
date, you can get the year, month and day separated, or joined together by any separator character you like.

Also the numbers are shown in Persian characters by default to users.

<p align="center">
<img src="https://github.com/SirLordPouya/PersianLinearDatePicker/blob/master/shots/Screenshot_3.png" width="450">
</p>

## Description

### Library

This project is made in Kotlin and AndroidX.

### Current Functions

Functions that are available in this library are:

* setMaxYear
* setMinYear
* setDate
* getSelectedYear
* getSelectedMonth
* getSelectedDay
* getSelectedGregorianYear
* getSelectedGregorianMonth
* getSelectedGregorianDay
* getFormattedDate
* getPersianFormattedDate
* setOnDateChangedListener

### Usage

First add maven repository to your project level gradle file:

```groovy
   allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
 ```

Then add the dependency:

 ```groovy
   dependencies {
    implementation ("com.github.SirLordPouya:PersianLinearDatePicker:latest_version")
}
  ```

Add PersianLinearDatePicker view to your xml:

```xml

<com.pouyaheydari.lineardatepicker.PersianLinearDatePicker
    android:id="@+id/datePicker"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:defaultDay="20"
    app:defaultMonth="6"
    app:defaultYear="1400"
    app:maxYear="1420"
    app:minYear="1320" />
```

Note that maxYear and minYear are optional. If you don't set any values for minYear or maxYear,
1320..1420 will be used as default values.

## License

PersianLinearDatePicker is released under the Apache License 2.0.
See [LICENSE](https://github.com/SirLordPouya/PersianLinearDatePicker/blob/master/LICENSE.md) for
details.

Copyright (c) 2020 Pouya Heydari

### <div>This App icon and style is designed by <a href="https://dribbble.com/Amirgk" title="Amir Gerdakane">Amir Gerdakane</a>
