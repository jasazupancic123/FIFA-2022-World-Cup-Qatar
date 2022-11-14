package com.example.worldcupapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.MatchAPI
import com.example.worldcupapp.adapter.MatchAdapter
import com.example.worldcupapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewUpomingMatches: RecyclerView
    lateinit var recyclerViewFinishedMatches: RecyclerView
    private var upcomingMatches: MutableList<Match>? = null
    private var finishedMatches: MutableList<Match>? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerViewUpomingMatches = binding.recyclerMatch
        recyclerViewFinishedMatches = binding.recyclerMatchFinished

        lifecycleScope.launch {
            upcomingMatches = MatchAPI().findUpcomingFive()
            recyclerViewUpomingMatches.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewUpomingMatches.adapter = MatchAdapter(this@MainActivity, upcomingMatches!!)
            binding.progressBar.visibility = View.GONE
            binding.loadingMatchesText.visibility = View.GONE

            finishedMatches = MatchAPI().findFinished()
            val firstFiveFinished = finishedMatches?.take(5)
            recyclerViewFinishedMatches.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewFinishedMatches.adapter = MatchAdapter(this@MainActivity, firstFiveFinished!!)
            binding.progressBarFinishedMatches.visibility = View.GONE
            binding.loadingFinishedMatchesText.visibility = View.GONE
        }

        val bottomNavigationView = binding.bottomNaviagtionView
        bottomNavigationView.selectedItemId = com.example.worldcupapp.R.id.matches;

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.matches -> {
                }
                R.id.teams -> {
                    startActivity(Intent(applicationContext, GroupsActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                R.id.statistics -> {
                    startActivity(Intent(applicationContext, StatisticsActivity::class.java))
                    overridePendingTransition(0, 0)
                }
            }
            false
        }
        setContentView(binding.root)
    }
}





