package com.pouyaheydari.persianlineardatepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFixTime.setOnClickListener {
            datePicker.setDate(1321, 7, 30)
        }
    }
}
