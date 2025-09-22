package com.example.kotlin_mordor

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils

class DataSummaryActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var raceTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var elfPathsTextView: TextView
    private lateinit var equipmentTextView: TextView
    private lateinit var marchDurationTextView: TextView
    private lateinit var ratingTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_summary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameTextView = findViewById(R.id.nameTextView)
        raceTextView = findViewById(R.id.raceTextView)
        dateTextView = findViewById(R.id.dateTextView)
        elfPathsTextView = findViewById(R.id.elfPathsTextView)
        equipmentTextView = findViewById(R.id.equipmentTextView)
        marchDurationTextView = findViewById(R.id.marchDurationTextView)
        ratingTextView = findViewById(R.id.ratingTextView)

        val name = intent.getStringExtra("name") ?: ""
        nameTextView.text = if (name.isBlank()) "No name received" else "Imię: $name"

        val race = intent.getStringExtra("race") ?: ""
        raceTextView.text = if (race.isBlank()) "No race received" else "Rasa: $race"

        val date = intent.getStringExtra("date") ?: ""
        dateTextView.text = if (date.isBlank()) "No date received" else "Data: $date"

        val elfPaths = intent.getStringExtra("elfPaths") ?: "nie"
        elfPathsTextView.text = "Ścieżki elfów: $elfPaths"

        val equipment = intent.getStringExtra("equipment") ?: ""
        equipmentTextView.text = if (equipment.isBlank()) "No equipment received" else "Wyposażenie: $equipment"

        val marchDuration = intent.getIntExtra("marchDuration", 0)
        marchDurationTextView.text = "Czas trwania marszu: $marchDuration"

        val rating = intent.getFloatExtra("rating", 0f)
        ratingTextView.text = "Ocena: $rating"
    }
}