package com.example.worldcupinputapp

import android.app.Dialog
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
                        MatchAPI().updateIsHalfTime(match._id)
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
                        MatchAPI().updateSecondHalfStarted(match._id)
                        dialog.dismiss()
                    }
                }
            }
        }

        binding.hasFinishedButton.setOnClickListener(){
            //noNeedToUpdateHasFinishedText
            val dialog: Dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_second_half_started)
            dialog.setTitle("Has Second Half Started")
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
                        MatchAPI().updateHasFinished(match._id)
                        dialog.dismiss()
                    }
                }
            }
        }
        binding.addGoalButton.setOnClickListener(){
            //dialog
        }
        binding.addLineupButton.setOnClickListener(){
            //dialog
        }
        binding.addYellowCardButton.setOnClickListener(){
            //dialog
        }
        binding.addRedCardButton.setOnClickListener(){
            //dialog
        }

        binding.backButton.setOnClickListener(){
            finish()
        }

        setContentView(binding.root)
    }
}