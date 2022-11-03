package com.example.worldcupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.worldcupapp.databinding.ActivityMainBinding
import androidx.lifecycle.lifecycleScope
import api.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class MainActivity : AppCompatActivity(){
    private val scope = CoroutineScope(newSingleThreadContext("name"))
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var matches: MutableList<Match> = mutableListOf()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        scope.launch {
            println("IN SCOPE")
            val lineups = LineupAPI().find()
            println(lineups.toString())
        }

        setContentView(binding.root)
    }
}




