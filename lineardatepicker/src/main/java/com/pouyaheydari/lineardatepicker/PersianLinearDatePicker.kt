package com.pouyaheydari.lineardatepicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pouyaheydari.lineardatepicker.utils.toPersianNumber
import kotlinx.android.synthetic.main.layout_persian_linear_date_picker.view.*

/**
 * A persian date picker that is made by NumberPicker
 *
 * @param context: Context that will be provided by Android system itself
 * @param attr: AttributeSet to let user set the needed properties inside xml
 *
 * @author: Pouya Heydari -> https://pouyaheydari.com
 */
class PersianLinearDatePicker(context: Context, attr: AttributeSet?) : LinearLayout(context, attr) {

    constructor(context: Context) : this(context, null)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_persian_linear_date_picker, this)

        val typedArray =
            context.obtainStyledAttributes(attr, R.styleable.PersianLinearDatePicker)
        val minYear =
            typedArray.getInt(R.styleable.PersianLinearDatePicker_minYear, 1320)
        val maxYear =
            typedArray.getInt(R.styleable.PersianLinearDatePicker_maxYear, 1420)

        setMinYear(minYear)
        setMaxYear(maxYear)
        setMonths()
        setDays(31)
        dayMonthAdapter()
        changeShowingNumbersToPersian()
        typedArray.recycle()
    }

    private fun dayMonthAdapter() {
        monthPicker.setOnValueChangedListener { _, _, newVal ->
            if (newVal > 6)
                setDays(30)
            else
                setDays(31)
        }
    }

    private fun setDays(maxDay: Int) {
        dayPicker.minValue = 1
        dayPicker.maxValue = maxDay
    }

    private fun setMonths() {
        monthPicker.minValue = 1
        monthPicker.maxValue = 12
    }

    private fun changeShowingNumbersToPersian() {
        yearPicker.setFormatter {
            it.toString().toPersianNumber()
        }
        monthPicker.setFormatter {
            it.toString().toPersianNumber()
        }
        dayPicker.setFormatter {
            it.toString().toPersianNumber()
        }
    }

    /**
     * To let user set the max year of date picker
     *
     * @param maxYear Maximum year that should be included in the picker
     *
     * @throws IllegalArgumentException if the max value is lesser than 1000 or min year
     */
    @Throws(IllegalArgumentException::class)
    fun setMaxYear(maxYear: Int) {
        yearPicker.maxValue = getMaxYearOrError(maxYear)
    }

    @Throws(IllegalArgumentException::class)
    private fun getMaxYearOrError(maxYear: Int) =
        if (maxYear > 1000 && maxYear > yearPicker.minValue) maxYear else
            throw IllegalArgumentException(
                "Maximum Year cant be less than 1000 or less than min year"
            )

    /**
     * To let user set the min year of date picker
     *
     * @param minYear Minimum year that should be included in the picker
     *
     * @throws IllegalArgumentException if the min value is lesser than 1000
     */
    @Throws(IllegalArgumentException::class)
    fun setMinYear(minYear: Int) {
        yearPicker.minValue = getMinYearOrError(minYear)
    }

    /**
     * To let user set the exact date for date picker
     *
     * @param year a year to be set in picker
     * @param month a month to be set in picker
     * @param day a day to be set in picker
     *
     * @throws IllegalArgumentException if the date cant be set int pickers
     */
    @Throws(IllegalArgumentException::class)
    fun setDate(year: Int, month: Int, day: Int) {

        dateCorrectnessChecker(year, month, day)

        if (month > 6)
            setDays(30)

        yearPicker.value = year
        monthPicker.value = month
        dayPicker.value = day
    }

    @Throws(IllegalArgumentException::class)
    private fun dateCorrectnessChecker(year: Int, month: Int, day: Int) {
        if (year > yearPicker.maxValue || year < yearPicker.minValue)
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

    @Throws(IllegalArgumentException::class)
    private fun getMinYearOrError(minYear: Int) =
        if (minYear > 1000) minYear else
            throw IllegalArgumentException("Minimum Year cant be less than 1000")

    /**
     * Returns selected year to user
     */
    fun getSelectedYear() = yearPicker.value

    /**
     * Returns selected month to user
     */
    fun getSelectedMonth() = monthPicker.value

    /**
     * Returns selected day to user
     */
    fun getSelectedDay() = dayPicker.value

    /**
     *  Returns the date in order of: Year Month Day
     *
     *  @param format: The format to be put between segments. If user doesn't pass anything,
     *  slash will be used by default
     */
    fun getFormattedDate(format: Char = '/') =
        "${yearPicker.value}$format${monthPicker.value}$format${dayPicker.value}"

    /**
     *  Returns the date in order of: Year Month Day in Persian numbers
     *
     *  @param format: The format to be put between segments. If user doesn't pass anything,
     *  slash will be used by default
     */
    fun getPersianFormattedDate(format: Char = '/') =
        "${yearPicker.value}$format${monthPicker.value}$format${dayPicker.value}".toPersianNumber()

    /**
     * Informs the listener that date is changed
     *
     * @param listener accepts a lambda to pass the year, month and day
     */
    fun setOnDateChangedListener(listener: (year: Int, month: Int, day: Int) -> Unit) {
        yearPicker.setOnValueChangedListener { _, _, _ ->
            informListener(listener)
        }
        monthPicker.setOnValueChangedListener { _, _, _ ->
            informListener(listener)
        }
        dayPicker.setOnValueChangedListener { _, _, _ ->
            informListener(listener)
        }
    }

    private fun informListener(listener: (Int, Int, Int) -> Unit) {
        listener(yearPicker.value, monthPicker.value, dayPicker.value)
    }
}
