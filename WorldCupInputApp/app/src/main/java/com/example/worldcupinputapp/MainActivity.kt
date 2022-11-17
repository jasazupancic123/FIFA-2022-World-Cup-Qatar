package com.example.worldcupinputapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.MatchAPI
import com.example.worldcupapp.Match
import com.example.worldcupapp.adapter.MatchAdapter
import com.example.worldcupinputapp.databinding.ActivityMainBinding
import io.ktor.client.call.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewMatches: RecyclerView
    var upcomingMatches: MutableList<Match>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerViewMatches = binding.recyclerMatches
        lifecycleScope.launch {
            upcomingMatches = MatchAPI().findUpcomingFive()
            recyclerViewMatches.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewMatches.adapter = MatchAdapter(this@MainActivity, upcomingMatches!!)
            binding.progressBar.visibility = View.GONE
            binding.loadingMatchesText.visibility = View.GONE
        }
        setContentView(binding.root)

    }
}