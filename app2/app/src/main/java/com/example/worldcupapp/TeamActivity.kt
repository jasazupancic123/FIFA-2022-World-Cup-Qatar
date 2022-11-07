package com.example.worldcupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.MatchAPI
import api.TeamAPI
import com.example.worldcupapp.adapter.MatchAdapter
import com.example.worldcupapp.adapter.TeamAdapter
import com.example.worldcupapp.databinding.ActivityTeamBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class TeamActivity : AppCompatActivity() {
    private val scope = CoroutineScope(newSingleThreadContext("name"))
    private lateinit var binding: ActivityTeamBinding
    lateinit var recyclerViewGroupA: RecyclerView
    lateinit var recyclerViewGroupB: RecyclerView
    private var teamsA: MutableList<Team>? = null
    private var teamsB: MutableList<Team>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        recyclerViewGroupA = binding.recyclerTeamGroupA
        recyclerViewGroupB = binding.recyclerTeamGroupB

        lifecycleScope.launch {
            teamsA = TeamAPI().findByGroup("A")
            teamsB = TeamAPI().findByGroup("B")
            recyclerViewGroupA.layoutManager = LinearLayoutManager(this@TeamActivity)
            recyclerViewGroupB.layoutManager = LinearLayoutManager(this@TeamActivity)
            recyclerViewGroupA.adapter = TeamAdapter(this@TeamActivity, teamsA!!)
            recyclerViewGroupB.adapter = TeamAdapter(this@TeamActivity, teamsB!!)
            binding.progressBar.visibility = View.GONE
            binding.loadingTeamsText.visibility = View.GONE
        }
        setContentView(binding.root)

        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNaviagtionView)
        bottomNavigationView.selectedItemId = R.id.teams;

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.matches -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                R.id.teams -> {
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