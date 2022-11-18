package com.example.worldcupinputapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import api.GoalAPI
import api.PlayerAPI
import com.example.worldcupapp.Match
import com.example.worldcupapp.Player
import com.example.worldcupinputapp.databinding.ActivityGoalBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class GoalActivity : AppCompatActivity() {
    lateinit var binding: ActivityGoalBinding
    private lateinit var match: Match
    private var isHomeGoal: Boolean = false
    private lateinit var players: MutableList<Player>
    private var playerNames: Array<String> = arrayOf()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalBinding.inflate(layoutInflater)

        val extras = intent.extras
        var matchObject = extras?.get("match")
        match = Gson().fromJson(matchObject.toString(), Match::class.java)
        var homeGoal = extras?.get("homeOrAway")
        isHomeGoal = homeGoal == "home"
        if (isHomeGoal){
            lifecycleScope.launch {
                players = PlayerAPI().findByTeam(match.homeTeam._id)
                for (p in players){
                    playerNames += p.firstName + " " + p.lastName
                }
                println(playerNames)
                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this@GoalActivity, android.R.layout.simple_spinner_dropdown_item, playerNames)
                val dropdown: Spinner = findViewById(R.id.spinnerPlayers)
                dropdown.adapter = adapter
                binding.progressBar.visibility = Spinner.GONE
                binding.loadingPlayersText.visibility = TextView.GONE
                binding.choosePlayerText.visibility = TextView.VISIBLE
                binding.spinnerPlayers.visibility = Spinner.VISIBLE
                binding.inputMinuteText.visibility = TextView.VISIBLE
                binding.matchMinuteEditText.visibility = TextView.VISIBLE
                binding.addButton.visibility = TextView.VISIBLE

                binding.addButton.setOnClickListener{
                    val minute = binding.matchMinuteEditText.text.toString().toInt()
                    val player = players[binding.spinnerPlayers.selectedItemPosition]
                    if(minute > 0 && minute <= 150){
                        lifecycleScope.launch {
                            GoalAPI().postAndUpdateEverything(
                                player._id,
                                minute,
                                match._id,
                                isHomeGoal
                            )
                            val intent = Intent(this@GoalActivity, MatchUpdateActivity::class.java)
                            intent.putExtra("match", Gson().toJson(match))
                            startActivity(intent)
                        }
                    }
                }
            }
        }
        else {

        }


        setContentView(binding.root)
    }
}