package com.example.worldcupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.TeamAPI
import com.example.worldcupapp.adapter.TeamAdapter
import com.example.worldcupapp.databinding.ActivityGroupsBinding
import com.example.worldcupapp.databinding.ActivityTeamBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class GroupsActivity : AppCompatActivity() {
    private val scope = CoroutineScope(newSingleThreadContext("name"))
    private lateinit var binding: ActivityGroupsBinding
    lateinit var recyclerViewGroupA: RecyclerView
    lateinit var recyclerViewGroupB: RecyclerView
    private var teamsA: MutableList<Team>? = null
    private var teamsB: MutableList<Team>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupsBinding.inflate(layoutInflater)
        recyclerViewGroupA = binding.recyclerTeamGroupA
        recyclerViewGroupB = binding.recyclerTeamGroupB

        lifecycleScope.launch {
            teamsA = TeamAPI().findByGroup("A")
            teamsB = TeamAPI().findByGroup("B")
            recyclerViewGroupA.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupB.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupA.adapter = TeamAdapter(this@GroupsActivity, teamsA!!)
            recyclerViewGroupB.adapter = TeamAdapter(this@GroupsActivity, teamsB!!)
            binding.progressBar.visibility = View.GONE
            binding.loadingTeamsText.visibility = View.GONE

            binding.GroupA.visibility = View.VISIBLE
            binding.GroupB.visibility = View.VISIBLE
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