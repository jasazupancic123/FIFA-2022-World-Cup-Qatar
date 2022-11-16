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
    private lateinit var binding: ActivityGroupsBinding
    lateinit var recyclerViewGroupA: RecyclerView
    lateinit var recyclerViewGroupB: RecyclerView
    lateinit var recyclerViewGroupC: RecyclerView
    lateinit var recyclerViewGroupD: RecyclerView
    lateinit var recyclerViewGroupE: RecyclerView
    lateinit var recyclerViewGroupF: RecyclerView
    lateinit var recyclerViewGroupG: RecyclerView
    lateinit var recyclerViewGroupH: RecyclerView
    private var teamsA: MutableList<Team>? = null
    private var teamsB: MutableList<Team>? = null
    private var teamsC: MutableList<Team>? = null
    private var teamsD: MutableList<Team>? = null
    private var teamsE: MutableList<Team>? = null
    private var teamsF: MutableList<Team>? = null
    private var teamsG: MutableList<Team>? = null
    private var teamsH: MutableList<Team>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupsBinding.inflate(layoutInflater)
        recyclerViewGroupA = binding.recyclerTeamGroupA
        recyclerViewGroupB = binding.recyclerTeamGroupB
        recyclerViewGroupC = binding.recyclerTeamGroupC
        recyclerViewGroupD = binding.recyclerTeamGroupD
        recyclerViewGroupE = binding.recyclerTeamGroupE
        recyclerViewGroupF = binding.recyclerTeamGroupF
        recyclerViewGroupG = binding.recyclerTeamGroupG
        recyclerViewGroupH = binding.recyclerTeamGroupH

        lifecycleScope.launch {
            teamsA = TeamAPI().findByGroup("A")
            recyclerViewGroupA.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupA.adapter = TeamAdapter(this@GroupsActivity, teamsA!!)
            binding.GroupA.visibility = View.VISIBLE
            teamsB = TeamAPI().findByGroup("B")
            recyclerViewGroupB.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupB.adapter = TeamAdapter(this@GroupsActivity, teamsB!!)
            binding.GroupB.visibility = View.VISIBLE
            teamsC = TeamAPI().findByGroup("C")
            recyclerViewGroupC.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupC.adapter = TeamAdapter(this@GroupsActivity, teamsC!!)
            binding.GroupC.visibility = View.VISIBLE
            teamsD = TeamAPI().findByGroup("D")
            recyclerViewGroupD.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupD.adapter = TeamAdapter(this@GroupsActivity, teamsD!!)
            binding.GroupD.visibility = View.VISIBLE
            teamsE = TeamAPI().findByGroup("E")
            recyclerViewGroupE.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupE.adapter = TeamAdapter(this@GroupsActivity, teamsE!!)
            binding.GroupE.visibility = View.VISIBLE
            teamsF = TeamAPI().findByGroup("F")
            recyclerViewGroupF.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupF.adapter = TeamAdapter(this@GroupsActivity, teamsF!!)
            binding.GroupF.visibility = View.VISIBLE
            teamsG = TeamAPI().findByGroup("G")
            recyclerViewGroupG.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupG.adapter = TeamAdapter(this@GroupsActivity, teamsG!!)
            binding.GroupG.visibility = View.VISIBLE
            teamsH = TeamAPI().findByGroup("H")
            recyclerViewGroupH.layoutManager = LinearLayoutManager(this@GroupsActivity)
            recyclerViewGroupH.adapter = TeamAdapter(this@GroupsActivity, teamsH!!)
            binding.GroupH.visibility = View.VISIBLE
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