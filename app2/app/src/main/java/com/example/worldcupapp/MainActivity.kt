package com.example.worldcupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import api.MatchAPI
import com.example.worldcupapp.databinding.ActivityMainBinding
import androidx.lifecycle.lifecycleScope
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

suspend fun CoroutineScope.getMatches() {
    println("PRVI")
    val matches = async {
    println("DRUGI")
        val client = HttpClient()
        val httpAsync = "http://localhost:3000/match"
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println("exception")
                        println(ex)
                    }
                    is Result.Success -> {
                        val data = result.get()
                        println("data: $data")
                    }
                }

            }
    }
    matches.await()
    //return matches.await()
}


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var matches: MutableList<Match> = mutableListOf()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            getMatches()
        }
        /*
        val coroutine = GlobalScope.async {
            MatchAPI().find()
        }

         */

/*
        runBlocking {
            withContext(Dispatchers.IO) {
                val matchAPI = MatchAPI()
                try {
                    val matches = matchAPI.find()
                } catch (e: Exception) {
                    println(e)
                }
            }
        }

 */

        setContentView(binding.root)
    }

    /*private fun getMatches() {
        lifecycleScope.launch {
            val matches = MatchAPI().find()
        }
    }

     */
}




