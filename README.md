# Persian Date Picker
An easy to use Android library to pick a date.

[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![Build Status](https://travis-ci.org/SirLordPouya/PersianLinearDatePicker.svg?branch=master)](https://travis-ci.org/SirLordPouya/PersianLinearDatePicker)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7e8f094fd77044b5b26bc6c157bfbbc3)](https://www.codacy.com/manual/SirLordPouya/AndroidAppUpdater?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=SirLordPouya/AndroidAppUpdater&amp;utm_campaign=Badge_Grade)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![](https://jitpack.io/v/SirLordPouya/PersianLinearDatePicker.svg)](https://jitpack.io/#SirLordPouya/PersianLinearDatePicker)

<p align="center">
<img src="https://github.com/SirLordPouya/StarWars/blob/master/shots/appicon.png" width="250">
</p>

You can set the minimum and maximum dates to be shown to the user. After user selects the desired date,
you can get the year, month and day separator or joined together by any separator you like.

Also the numbers are shown in Persian by default to users.

<p align="center">
<img src="https://github.com/SirLordPouya/PersianLinearDatePicker/blob/master/shots/Screenshot_3.png" width="450">
</p>

## Description

### Library

This project is made by Kotlin and AndroidX completely and is not a forked or refactored code.
The design is inspired by [PersianDatePicker](https://github.com/alibehzadian/PersianDatePicker).

### Current Functions

Functions that are available in this library are:

*   setMaxYear
*   setMinYear
*   setDate
*   getSelectedYear
*   getSelectedMonth
*   getSelectedDay
*   getFormattedDate
*   getPersianFormattedDate

### Usage

First add maven repository to your project level gradle file:

```groovy
allprojects {
   repositories {
	   ...
		maven { url 'https://jitpack.io' }
		}
	}
 ```
 Then add the dependency:
 
 ```groovy
 dependencies {
	  implementation 'com.github.SirLordPouya:PersianLinearDatePicker:1.0.0'
	}
  ```


### TODO

I will add these features in the future:

*   Calculating leap years
*   Converting a date to Gregorian date

## License

PersianLinearDatePicker is released under the Apache License 2.0. See [LICENSE](https://github.com/SirLordPouya/PersianLinearDatePicker/blob/master/LICENSE.md) for details.

Copyright (c) 2020 Pouya Heydari

### <div>This App icon and style is designed by <a href="https://dribbble.com/Amirgk" title="Amir Gerdakane">Amir Gerdakane</a>
