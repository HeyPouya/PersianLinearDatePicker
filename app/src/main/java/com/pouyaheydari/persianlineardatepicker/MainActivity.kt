package com.pouyaheydari.persianlineardatepicker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pouyaheydari.persianlineardatepicker.databinding.ActivityMainBinding

/**
 * Main activity of demo application
 */
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnFixTime.setOnClickListener {
            binding.datePicker.setDate(1321, 7, 30)
        }

        binding.btnGetTime.setOnClickListener {
            Toast.makeText(this, binding.datePicker.getFormattedDate(), Toast.LENGTH_LONG).show()
        }

        binding.datePicker.setOnDateChangedListener { year, month, day ->
            Toast.makeText(this, binding.datePicker.getFormattedDate(), Toast.LENGTH_LONG).show()
        }
    }
}
