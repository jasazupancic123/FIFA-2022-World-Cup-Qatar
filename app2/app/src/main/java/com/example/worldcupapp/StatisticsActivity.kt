package com.example.worldcupapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.PlayerAPI
import com.example.worldcupapp.adapter.PlayerAdapter
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

        lifecycleScope.launch {
            val topScorers = PlayerAPI().findByMostGoals()
            val topScorersList = topScorers?.take(3)
            topScorersRecycler.layoutManager = LinearLayoutManager(this@StatisticsActivity)
            topScorersRecycler.adapter = PlayerAdapter(this@StatisticsActivity, topScorersList!!, 1)
            binding.loadingTopScorers.visibility = View.GONE
            binding.loadingTopScorersText.visibility = View.GONE

            binding.topScorersButton.setOnClickListener {
                val dialog: Dialog = Dialog(this@StatisticsActivity)
                dialog.setContentView(R.layout.dialog_player_stats)
                dialog.setTitle("Top Scorers")
                dialog.window?.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                val titleText = dialog.findViewById<android.widget.TextView>(R.id.titleText)
                titleText.text = "Top Scorers"
                val topScorersRecyclerDialog = dialog.findViewById<RecyclerView>(R.id.recyclerPlayerStats)
                topScorersRecyclerDialog.layoutManager = LinearLayoutManager(this@StatisticsActivity)
                topScorersRecyclerDialog.adapter = PlayerAdapter(this@StatisticsActivity, topScorers!!, 1)
                dialog.show()
                val closeButton = dialog.findViewById<android.widget.Button>(R.id.closeButton)
                closeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }

            val topAssisters = PlayerAPI().findByMostAssists()
            val topAssistersList = topAssisters?.take(3)
            topAssistersRecycler.layoutManager = LinearLayoutManager(this@StatisticsActivity)
            topAssistersRecycler.adapter = PlayerAdapter(this@StatisticsActivity, topAssistersList!!, 2)
            binding.loadingTopAsisters.visibility = View.GONE
            binding.loadingTopAsistersText.visibility = View.GONE

            binding.topAssistersButton.setOnClickListener {
                val dialog: Dialog = Dialog(this@StatisticsActivity)
                dialog.setContentView(R.layout.dialog_player_stats)
                dialog.setTitle("Top Assisters")
                dialog.window?.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                val titleText = dialog.findViewById<android.widget.TextView>(R.id.titleText)
                titleText.text = "Top Assisters"
                val topAssistersRecyclerDialog = dialog.findViewById<RecyclerView>(R.id.recyclerPlayerStats)
                topAssistersRecyclerDialog.layoutManager = LinearLayoutManager(this@StatisticsActivity)
                topAssistersRecyclerDialog.adapter = PlayerAdapter(this@StatisticsActivity, topAssisters!!, 2)
                dialog.show()
                val closeButton = dialog.findViewById<android.widget.Button>(R.id.closeButton)
                closeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }

            val topYellowCards = PlayerAPI().findByMostYellowCards()
            val topYellowCardsList = topYellowCards?.take(3)
            topYellowCardsRecycler.layoutManager = LinearLayoutManager(this@StatisticsActivity)
            topYellowCardsRecycler.adapter = PlayerAdapter(this@StatisticsActivity, topYellowCardsList!!, 3)
            binding.loadingTopYellowCards.visibility = View.GONE
            binding.loadingMostYellowCardsText.visibility = View.GONE

            binding.mostYellowCardsButton.setOnClickListener {
                val dialog: Dialog = Dialog(this@StatisticsActivity)
                dialog.setContentView(R.layout.dialog_player_stats)
                dialog.setTitle("Most Yellow Cards")
                dialog.window?.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                val titleText = dialog.findViewById<android.widget.TextView>(R.id.titleText)
                titleText.text = "Most Yellow Cards"
                val topYellowCardsRecyclerDialog = dialog.findViewById<RecyclerView>(R.id.recyclerPlayerStats)
                topYellowCardsRecyclerDialog.layoutManager = LinearLayoutManager(this@StatisticsActivity)
                topYellowCardsRecyclerDialog.adapter = PlayerAdapter(this@StatisticsActivity, topYellowCards!!, 3)
                dialog.show()
                val closeButton = dialog.findViewById<android.widget.Button>(R.id.closeButton)
                closeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }

            val topRedCards = PlayerAPI().findByMostRedCards()
            val topRedCardsList = topRedCards?.take(3)
            topRedCardsRecycler.layoutManager = LinearLayoutManager(this@StatisticsActivity)
            topRedCardsRecycler.adapter = PlayerAdapter(this@StatisticsActivity, topRedCardsList!!, 4)
            binding.loadingTopRedCards.visibility = View.GONE
            binding.loadingMostRedCardsText.visibility = View.GONE

            binding.mostRedCardsButton.setOnClickListener {
                val dialog: Dialog = Dialog(this@StatisticsActivity)
                dialog.setContentView(R.layout.dialog_player_stats)
                dialog.setTitle("Most Red Cards")
                dialog.window?.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                val titleText = dialog.findViewById<android.widget.TextView>(R.id.titleText)
                titleText.text = "Most Red Cards"
                val topRedCardsRecyclerDialog = dialog.findViewById<RecyclerView>(R.id.recyclerPlayerStats)
                topRedCardsRecyclerDialog.layoutManager = LinearLayoutManager(this@StatisticsActivity)
                topRedCardsRecyclerDialog.adapter = PlayerAdapter(this@StatisticsActivity, topRedCards!!, 4)
                dialog.show()
                val closeButton = dialog.findViewById<android.widget.Button>(R.id.closeButton)
                closeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }

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