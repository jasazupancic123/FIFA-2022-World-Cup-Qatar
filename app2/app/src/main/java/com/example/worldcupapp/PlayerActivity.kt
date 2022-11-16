package com.example.worldcupapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.worldcupapp.databinding.ActivityPlayerBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class PlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    lateinit var player: Player
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        var playerObject = extras?.get("player")
        player = Gson().fromJson(playerObject.toString(), Player::class.java)
        bind()
        setContentView(binding.root)

        binding.backButton.setOnClickListener{ view ->
            finish()
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun bind(){
        Picasso.with(this).load("https://api.sofascore.app/api/v1/player/12994/image").into(binding.playerImage)
        Picasso.with(this).load("https://api.sofascore.app/api/v1/team/1644/image").into(binding.clubImage)
        Picasso.with(this).load("https://flagcdn.com/160x120/" + player.team.iso2.lowercase() + ".png").into(binding.teamImageButton)
        binding.playerName.text = player.firstName + " " + player.lastName
        binding.teamText.text = player.team.fifaCode
        binding.clubText.text = player.club.name
        binding.positionText.text = "Position: " + player.position
        binding.shirtNumberText.text = "Shirt number: " + player.shirtNumber.toString()

        val today: Calendar = Calendar.getInstance()
        val birthDate: Calendar = Calendar.getInstance()
        birthDate.time = player.birthDate
        var years = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            years--
        }

        binding.ageText.text = "Age: $years"
        binding.heighText.text = "Height: " + player.height.toString()
        binding.appearancesText.text = "Appearances: " + player.appearances.toString()
        binding.goalsScoredText.text = "Goals scored: " + player.goals.toString()
        binding.asisstsText.text = "Asists: " + player.assists.toString()
        binding.yellowCardsText.text = "Yellow cards: " + player.yellowCards.toString()
        binding.redCardsText.text = "Red cards: " + player.redCards.toString()

        binding.teamImageButton.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            intent.putExtra("team", Gson().toJson(player.team))
            startActivity(intent)
        }
    }
}