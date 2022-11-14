package com.example.worldcupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import api.PlayerAPI
import com.example.worldcupapp.databinding.ActivityStatisticsBinding
import kotlinx.coroutines.launch

class StatisticsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatisticsBinding
    private lateinit var topScorersRecycler: RecyclerView
    private lateinit var topAssistersRecycler: RecyclerView
    private lateinit var topYellowCardsRecycler: RecyclerView
    private lateinit var topRedCardsRecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNaviagtionView)
        bottomNavigationView.selectedItemId = R.id.statistics;
        topScorersRecycler = binding.topScorersRecycler
        topAssistersRecycler = binding.topAssistersRecycler
        topYellowCardsRecycler = binding.mostYellowCardsRecycler
        topRedCardsRecycler = binding.mostRedCardsRecycler

        /*
        lifecycleScope.launch {
            val topScorers = PlayerAPI().findTopScorers()
            val topAssisters = PlayerAPI().findTopAssisters()
            val topYellowCards = PlayerAPI().findTopYellowCards()
            val topRedCards = PlayerAPI().findTopRedCards()
        }
         */


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.matches -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                R.id.teams -> {
                    startActivity(Intent(applicationContext, GroupsActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                R.id.statistics -> {
                }
            }
            false
        }

    }
}