package com.example.worldcupinputapp

import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import api.MatchAPI
import com.example.worldcupapp.Match
import com.example.worldcupinputapp.databinding.ActivityMatchUpdateBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

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
            dialog.setTitle("Delete Player")
            dialog.show()
            val yesButton = dialog.findViewById<Button>(R.id.yesButton)
            yesButton.setOnClickListener(){
                lifecycleScope.launch {
                    MatchAPI().updateIsHalfTime(match._id)
                    dialog.dismiss()
                }
            }
        }
        binding.secondHalfStartedButton.setOnClickListener(){
            //dialog
        }
        binding.hasFinishedButton.setOnClickListener(){
            //dialog
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

        setContentView(binding.root)
    }
}