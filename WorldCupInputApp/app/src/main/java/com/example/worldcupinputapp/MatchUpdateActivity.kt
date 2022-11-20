package com.example.worldcupinputapp

import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import api.MatchAPI
import com.example.worldcupapp.Match
import com.example.worldcupinputapp.databinding.ActivityMatchUpdateBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MatchUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityMatchUpdateBinding
    private lateinit var match: Match

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchUpdateBinding.inflate(layoutInflater)

        val extras = intent.extras
        var matchObject = extras?.get("match")
        match = Gson().fromJson(matchObject.toString(), Match::class.java)

        binding.isHalfTimeButton.setOnClickListener(){
            val dialog: Dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_is_half_time)
            dialog.setTitle("Is Half Time")
            dialog.show()
            if(!match.hasStarted || match.isFinished || match.isHalfTime){
                val noNeedToUpdateHalfTimeText = dialog.findViewById<TextView>(R.id.noNeedToUpdateHalfTimeText)
                noNeedToUpdateHalfTimeText.visibility = TextView.VISIBLE
                val yesButton = dialog.findViewById<Button>(R.id.yesButton)
                yesButton.visibility = Button.GONE
                val isHalfTimeText = dialog.findViewById<TextView>(R.id.isHalfTimeText)
                isHalfTimeText.visibility = TextView.GONE
            } else {
                val yesButton = dialog.findViewById<Button>(R.id.yesButton)
                yesButton.setOnClickListener() {
                    lifecycleScope.launch {
                        MatchAPI().updateIsHalfTime(match._id, this@MatchUpdateActivity)
                        dialog.dismiss()
                    }
                }
            }
        }

        binding.secondHalfStartedButton.setOnClickListener(){
            val dialog: Dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_second_half_started)
            dialog.setTitle("Has Second Half Started")
            dialog.show()
            if(!match.hasStarted || match.isFinished || !match.isHalfTime){
                val noNeedToUpdateSecondHalfText = dialog.findViewById<TextView>(R.id.noNeedToStartSecondHalfText)
                noNeedToUpdateSecondHalfText.visibility = TextView.VISIBLE
                val yesButton = dialog.findViewById<Button>(R.id.yesButton)
                yesButton.visibility = Button.GONE
                val secondHalfStartedText = dialog.findViewById<TextView>(R.id.hasHalfTimeResumedText)
                secondHalfStartedText.visibility = TextView.GONE
            } else {
                val yesButton = dialog.findViewById<Button>(R.id.yesButton)
                yesButton.setOnClickListener() {
                    lifecycleScope.launch {
                        MatchAPI().updateSecondHalfStarted(match._id, this@MatchUpdateActivity)
                        dialog.dismiss()
                    }
                }
            }
        }

        binding.hasFinishedButton.setOnClickListener(){
            //noNeedToUpdateHasFinishedText
            val dialog: Dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_has_finished)
            dialog.setTitle("Has Finished")
            dialog.show()
            if(!match.hasStarted || match.isFinished || match.halfTimeResumedAt == null){
                val noNeedToUpdateHasFinishedText = dialog.findViewById<TextView>(R.id.noNeedToUpdateHasFinishedText)
                noNeedToUpdateHasFinishedText.visibility = TextView.VISIBLE
                val yesButton = dialog.findViewById<Button>(R.id.yesButton)
                yesButton.visibility = Button.GONE
                val hasFinishedText = dialog.findViewById<TextView>(R.id.hasMatchFinishedText)
                hasFinishedText.visibility = TextView.GONE
            } else {
                val yesButton = dialog.findViewById<Button>(R.id.yesButton)
                yesButton.setOnClickListener() {
                    lifecycleScope.launch {
                        MatchAPI().updateHasFinished(match._id, this@MatchUpdateActivity)
                        dialog.dismiss()
                    }
                }
            }
        }

        binding.addGoalButton.setOnClickListener(){
            val dialog: Dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_home_or_away_goal)
            dialog.setTitle("Home Or Away Goal")
            dialog.show()
            if(!match.hasStarted || match.isFinished || match.isHalfTime) {
                val noNeedToAddGoalText = dialog.findViewById<TextView>(R.id.cannotUpdateHomeOrAwayGoalText)
                noNeedToAddGoalText.visibility = TextView.VISIBLE
                val homeGoalButton = dialog.findViewById<Button>(R.id.homeGoalButton)
                homeGoalButton.visibility = Button.GONE
                val awayGoalButton = dialog.findViewById<Button>(R.id.awayGoalButton)
                awayGoalButton.visibility = Button.GONE
                val homeOrAwayGoalText = dialog.findViewById<TextView>(R.id.isHomeOrAwayGoalText)
                homeOrAwayGoalText.visibility = TextView.GONE
            }
            else {
                val homeGoalButton = dialog.findViewById<Button>(R.id.homeGoalButton)
                homeGoalButton.text = match.homeTeam.name + " Goal"
                homeGoalButton.setOnClickListener() {
                    val intent = Intent(this, GoalActivity::class.java)
                    intent.putExtra("match", Gson().toJson(match))
                    intent.putExtra("homeOrAway", "home")
                    startActivity(intent)
                }
                val awayGoalButton = dialog.findViewById<Button>(R.id.awayGoalButton)
                awayGoalButton.text = match.awayTeam.name + " Goal"
                awayGoalButton.setOnClickListener() {
                    val intent = Intent(this, GoalActivity::class.java)
                    intent.putExtra("match", Gson().toJson(match))
                    intent.putExtra("homeOrAway", "away")
                    startActivity(intent)
                }
            }
        }

        binding.addLineupButton.setOnClickListener(){
            val dialog: Dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_home_or_away_lineup)
            dialog.setTitle("Home Or Away Lineup")
            dialog.show()
            if(match.isFinished) {
                val noNeedToAddLineupText = dialog.findViewById<TextView>(R.id.cannotUpdateHomeOrAwayLineupText)
                noNeedToAddLineupText.visibility = TextView.VISIBLE
                val homeLineupButton = dialog.findViewById<Button>(R.id.homeLineupButton)
                homeLineupButton.visibility = Button.GONE
                val awayLineupButton = dialog.findViewById<Button>(R.id.awayLineupButton)
                awayLineupButton.visibility = Button.GONE
                val homeOrAwayLineupText = dialog.findViewById<TextView>(R.id.isHomeOrAwayLineupText)
                homeOrAwayLineupText.visibility = TextView.GONE
            }
            else {
                val homeLineupButton = dialog.findViewById<Button>(R.id.homeLineupButton)
                homeLineupButton.text = match.homeTeam.name + " Lineup"
                homeLineupButton.setOnClickListener() {
                    val intent = Intent(this, LineupActivity::class.java)
                    intent.putExtra("match", Gson().toJson(match))
                    intent.putExtra("homeOrAway", "home")
                    dialog.dismiss()
                    startActivity(intent)
                }
                val awayGoalButton = dialog.findViewById<Button>(R.id.awayLineupButton)
                awayGoalButton.text = match.awayTeam.name + " Lineup"
                awayGoalButton.setOnClickListener() {
                    val intent = Intent(this, LineupActivity::class.java)
                    intent.putExtra("match", Gson().toJson(match))
                    intent.putExtra("homeOrAway", "away")
                    dialog.dismiss()
                    startActivity(intent)
                }
            }
        }

        binding.backButton.setOnClickListener(){
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}