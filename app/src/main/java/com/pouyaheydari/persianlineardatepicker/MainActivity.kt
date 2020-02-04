package com.pouyaheydari.persianlineardatepicker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity of demo application
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFixTime.setOnClickListener {
            datePicker.setDate(1321, 7, 30)
        }

        btnGetTime.setOnClickListener {
            Toast.makeText(this, datePicker.getFormattedDate(), Toast.LENGTH_LONG).show()
        }
    }
}
