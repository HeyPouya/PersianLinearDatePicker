package com.pouyaheydari.lineardatepicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pouyaheydari.lineardatepicker.databinding.LayoutPersianLinearDatePickerBinding
import com.pouyaheydari.lineardatepicker.utils.CalendarTool
import com.pouyaheydari.lineardatepicker.utils.toPersianNumber
import java.util.GregorianCalendar


const val DEFAULT_MIN_YEAR = 1320
const val DEFAULT_MAX_YEAR = 1420
const val DEFAULT_YEAR = 1400
const val DEFAULT_MONTH = 1
const val DEFAULT_DAY = 1

/**
 * A persian date picker that is made by NumberPicker
 *
 * @param context: Context that will be provided by Android system itself
 * @param attr: AttributeSet to let user set the needed properties inside xml
 *
 * @author: Pouya Heydari -> https://pouyaheydari.com
 */
class PersianLinearDatePicker(context: Context, attr: AttributeSet?) : LinearLayout(context, attr) {

    private var minYear = DEFAULT_MIN_YEAR
    private var maxYear = DEFAULT_MAX_YEAR
    private val gregorianCalendar = GregorianCalendar()
    private val calendar = CalendarTool(gregorianCalendar)

    private var binding: LayoutPersianLinearDatePickerBinding

    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        binding = LayoutPersianLinearDatePickerBinding.inflate(inflater, this, true)

        val typedArray =
            context.obtainStyledAttributes(attr, R.styleable.PersianLinearDatePicker)
        minYear =
            typedArray.getInt(R.styleable.PersianLinearDatePicker_minYear, DEFAULT_MIN_YEAR)
        maxYear =
            typedArray.getInt(R.styleable.PersianLinearDatePicker_maxYear, DEFAULT_MAX_YEAR)
        val defaultYear =
            typedArray.getInt(R.styleable.PersianLinearDatePicker_defaultYear, DEFAULT_YEAR)
        val defaultMonth =
            typedArray.getInt(R.styleable.PersianLinearDatePicker_defaultMonth, DEFAULT_MONTH)
        val defaultDay =
            typedArray.getInt(R.styleable.PersianLinearDatePicker_defaultDay, DEFAULT_DAY)

        typedArray.recycle()

        setMinYear(minYear)
        setMaxYear(maxYear, minYear)
        setMonths()
        daysOfMonthFixer(defaultYear, defaultMonth, defaultDay)
        setOnDateChangedListener { _, _, _ -> }
        changeShowingNumbersToPersian()
        setDate(defaultYear, defaultMonth, defaultDay)
    }

    private fun daysOfMonthFixer(year: Int, month: Int, day: Int) =
        if (month == 12 && !calendar.setIranianDate(year, month, day).isLeap())
            setDays(29)
        else if (month > 6)
            setDays(30)
        else
            setDays(31)


    private fun setDays(maxDay: Int) {
        binding.dayPicker.minValue = 1
        binding.dayPicker.maxValue = maxDay
    }

    private fun setMonths() {
        binding.monthPicker.minValue = 1
        binding.monthPicker.maxValue = 12
    }

    private fun changeShowingNumbersToPersian() {
        binding.yearPicker.setFormatter { it.toString().toPersianNumber() }
        binding.monthPicker.setFormatter { it.toString().toPersianNumber() }
        binding.dayPicker.setFormatter { it.toString().toPersianNumber() }
    }

    /**
     * To let user set the max year of date picker
     *
     * @param maxYear Maximum year that should be included in the picker
     */
    fun setMaxYear(maxYear: Int, minYear: Int) {
        binding.yearPicker.maxValue =
            if (maxYear >= minYear) maxYear else
                throw IllegalArgumentException("maxYear must be equals or greater that minYear")
    }

    /**
     * To let user set the min year of date picker
     *
     * @param minYear Minimum year that should be included in the picker
     */
    fun setMinYear(minYear: Int) {
        binding.yearPicker.minValue =
            if (minYear > 1000) minYear else
                throw IllegalArgumentException("minYear must be greater that 1000")
    }

    /**
     * To let user set the exact date for date picker
     *
     * @param year a year to be set in picker
     * @param month a month to be set in picker
     * @param day a day to be set in picker
     *
     */
    fun setDate(year: Int, month: Int, day: Int) {
        dateCorrectnessChecker(year, month, day)
        daysOfMonthFixer(year, month, day)

        binding.yearPicker.value = year
        binding.monthPicker.value = month
        binding.dayPicker.value = day
    }

    private fun dateCorrectnessChecker(year: Int, month: Int, day: Int) {
        if (year > maxYear || year < minYear)
            throw IllegalArgumentException(
                "Year must be greater than Min year and lesser than Max year"
            )
        if (month < 1 || month > 12)
            throw IllegalArgumentException("Month number must be between 1 and 12")
        if (day < 1 || day > 31)
            throw IllegalArgumentException("Day number must be between 1 and 31")
        if (month > 6 && day == 31)
            throw IllegalArgumentException("Passes day 31 for a month in first half of year")
    }

    /**
     * Returns selected year to user
     */
    fun getSelectedYear() = binding.yearPicker.value

    /**
     * Returns selected month to user
     */
    fun getSelectedMonth() = binding.monthPicker.value

    /**
     * Returns selected day to user
     */
    fun getSelectedDay() = binding.dayPicker.value

    /**
     * Returns selected year in Gregorian to user
     */
    fun getSelectedGregorianYear() =
        calendar.setIranianDate(
            binding.yearPicker.value,
            binding.monthPicker.value,
            binding.dayPicker.value
        ).gregorianYear

    /**
     * Returns selected month in Gregorian to user
     */
    fun getSelectedGregorianMonth() =
        calendar.setIranianDate(
            binding.yearPicker.value,
            binding.monthPicker.value,
            binding.dayPicker.value
        ).gregorianMonth

    /**
     * Returns selected day to user in Gregorian
     */
    fun getSelectedGregorianDay() =
        calendar.setIranianDate(
            binding.yearPicker.value,
            binding.monthPicker.value,
            binding.dayPicker.value
        ).gregorianDay

    /**
     *  Returns the date in order of: Year Month Day
     *
     *  @param format: The format to be put between segments. If user doesn't pass anything,
     *  slash will be used by default
     */
    fun getFormattedDate(format: Char = '/') =
        "${binding.yearPicker.value}$format${binding.monthPicker.value}$format${binding.dayPicker.value}"

    /**
     *  Returns the date in order of: Year Month Day in Persian numbers
     *
     *  @param format: The format to be put between segments. If user doesn't pass anything,
     *  slash will be used by default
     */
    fun getPersianFormattedDate(format: Char = '/') =
        "${binding.yearPicker.value}$format${binding.monthPicker.value}$format${binding.dayPicker.value}".toPersianNumber()

    /**
     * Informs the listener that date is changed
     *
     * @param listener accepts a lambda to pass the year, month and day
     */
    fun setOnDateChangedListener(listener: (year: Int, month: Int, day: Int) -> Unit) {
        binding.yearPicker.setOnValueChangedListener { _, _, newYear ->
            if (binding.monthPicker.value == 12)
                daysOfMonthFixer(newYear, binding.monthPicker.value, binding.dayPicker.value)
            listener(binding.yearPicker.value, binding.monthPicker.value, binding.dayPicker.value)
        }
        binding.monthPicker.setOnValueChangedListener { _, _, newMonth ->
            daysOfMonthFixer(binding.yearPicker.value, newMonth, binding.dayPicker.value)
            listener(binding.yearPicker.value, binding.monthPicker.value, binding.dayPicker.value)
        }
        binding.dayPicker.setOnValueChangedListener { _, _, _ ->
            listener(binding.yearPicker.value, binding.monthPicker.value, binding.dayPicker.value)
        }
    }
}
