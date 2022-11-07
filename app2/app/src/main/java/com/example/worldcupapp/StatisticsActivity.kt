package com.example.worldcupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.worldcupapp.databinding.ActivityStatisticsBinding

class StatisticsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatisticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNaviagtionView)
        bottomNavigationView.selectedItemId = R.id.statistics;


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                com.example.worldcupapp.R.id.matches -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                com.example.worldcupapp.R.id.teams -> {
                    startActivity(Intent(applicationContext, TeamActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                com.example.worldcupapp.R.id.statistics -> {
                }
            }
            false
        }
    }
}