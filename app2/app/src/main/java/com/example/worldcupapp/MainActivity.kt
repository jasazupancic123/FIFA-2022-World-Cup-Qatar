package com.example.worldcupapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.MatchAPI
import com.example.worldcupapp.adapter.MatchAdapter
import com.example.worldcupapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    private var matches: MutableList<Match>? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerMatch

        lifecycleScope.launch {
            matches = MatchAPI().findUpcomingFive()
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = MatchAdapter(this@MainActivity, matches!!)
            binding.progressBar.visibility = View.GONE
            binding.loadingMatchesText.visibility = View.GONE
        }
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNaviagtionView
        bottomNavigationView.selectedItemId = com.example.worldcupapp.R.id.matches;

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.matches -> {
                }
                R.id.teams -> {
                    startActivity(Intent(applicationContext, TeamActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                R.id.statistics -> {
                    startActivity(Intent(applicationContext, StatisticsActivity::class.java))
                    overridePendingTransition(0, 0)
                }
            }
            false
        }
    }
}







