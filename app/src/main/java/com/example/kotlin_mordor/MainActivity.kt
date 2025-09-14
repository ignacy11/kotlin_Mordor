package com.example.kotlin_mordor

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.os.SystemClock.elapsedRealtime
import android.transition.Slide
import android.widget.Button
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.Chronometer
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var raceSpinner: Spinner
    private lateinit var toggleDatePickerButton: Button
    private lateinit var dateResultTextView: TextView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var elfSwitch: Switch
    private lateinit var coatEquipmentCheckBox: CheckBox
    private lateinit var breadEquipmentCheckbox: CheckBox
    private lateinit var torchEquipmentCheckbox: CheckBox
    private lateinit var marchSpeedRadioGroup: RadioGroup
    private lateinit var marchSpeedSlowRadioButton: RadioButton
    private lateinit var marchSpeedMediumRadioButton: RadioButton
    private lateinit var marchSpeedFastRadioButton: RadioButton
    private lateinit var marchDurationSeekBar: SeekBar
    private lateinit var stopwatchButton: Button
    private lateinit var chronometer: Chronometer
    private lateinit var timerButton: Button
    private lateinit var ratingBarSelection: RatingBar
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nameEditText = findViewById(R.id.nameEditText)
        raceSpinner = findViewById(R.id.raceSpinner)
        toggleDatePickerButton = findViewById(R.id.toggleDatePickerButton)
        dateResultTextView = findViewById(R.id.dateResultTextView)
        elfSwitch = findViewById(R.id.elfSwitch)
        coatEquipmentCheckBox = findViewById(R.id.coatEquipmentCheckbox)
        var coatEquipmentCheckBoxIsChecked = false
        breadEquipmentCheckbox = findViewById(R.id.breadEquipmentCheckbox)
        var breadEquipmentCheckBoxIsChecked = false
        torchEquipmentCheckbox = findViewById(R.id.torchEquipmentCheckbox)
        var torchEquipmentCheckBoxIsChecked = false
        marchSpeedRadioGroup = findViewById(R.id.marchSpeedRadioGroup)
        var marchSpeed = ""
        marchSpeedSlowRadioButton = findViewById(R.id.marchSpeedSlowRadioButton)
        marchSpeedMediumRadioButton = findViewById(R.id.marchSpeedMediumRadioButton)
        marchSpeedFastRadioButton = findViewById(R.id.marchSpeedFastRadioButton)
        marchDurationSeekBar = findViewById(R.id.marchDurationSeekBar)
        stopwatchButton = findViewById(R.id.stopwatchButton)
        chronometer = findViewById(R.id.chronometer)
        timerButton = findViewById(R.id.timerButton)
        ratingBarSelection = findViewById(R.id.ratingBarSelection)
        submitButton = findViewById(R.id.submitButton)


        val username = nameEditText.text.toString()

        // TODO: spinner

        marchSpeedRadioGroup.setOnCheckedChangeListener { _, _ ->
            getSelectedRadioButton()
        }

        coatEquipmentCheckBox.setOnCheckedChangeListener { _, _ ->
            coatEquipmentCheckBoxIsChecked = coatEquipmentCheckBox.isChecked
        }
        breadEquipmentCheckbox.setOnCheckedChangeListener { _, _ ->
            breadEquipmentCheckBoxIsChecked = breadEquipmentCheckbox.isChecked
        }
        torchEquipmentCheckbox.setOnCheckedChangeListener { _, _ ->
            torchEquipmentCheckBoxIsChecked = coatEquipmentCheckBox.isChecked
        }

        toggleDatePickerButton.setOnClickListener {
            showDatePicker()
        }
        startChronometer()
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val createdDatePickerDialog = DatePickerDialog(this,{_, selectedDay, selectedMonth, selectedYear ->
            dateResultTextView.text = "$selectedDay/$selectedMonth/$selectedYear"
        }, year, month, day)

        createdDatePickerDialog.show()
    }

    // ścieżki elfów


    private fun getSelectedCheckboxes(): MutableList<String> {
        val selectedCheckboxes = mutableListOf<String>()
        if(coatEquipmentCheckBox.isChecked) {
            selectedCheckboxes.add("płaszcz")
        }
        if(breadEquipmentCheckbox.isChecked) {
            selectedCheckboxes.add("lambasy")
        }
        if(torchEquipmentCheckbox.isChecked) {
            selectedCheckboxes.add("pochodnia")
        }
        return selectedCheckboxes
    }

    private fun getSelectedRadioButton(): String {
        val selectedID = marchSpeedRadioGroup.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedID)
        return selectedRadioButton.text.toString()
    }

    private var chronometerIsRunning: Boolean = false
    private fun startChronometer() {
        if (!chronometerIsRunning) {
            chronometer.base = elapsedRealtime()
            chronometer.start()
            chronometerIsRunning = true
        }
    }
    private fun stopChronometer() {
        if (chronometerIsRunning) {
            chronometer.stop()
            chronometerIsRunning = false
        }
    }
    private fun getChronometerTime(): Long {
        return (elapsedRealtime() - chronometer.base) / 1000
    }
}