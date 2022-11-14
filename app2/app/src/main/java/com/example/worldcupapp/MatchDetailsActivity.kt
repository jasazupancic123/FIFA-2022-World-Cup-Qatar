package com.example.worldcupapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import api.MatchAPI
import com.example.worldcupapp.databinding.ActivityMatchDetailsBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat

class MatchDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMatchDetailsBinding
    private lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailsBinding.inflate(layoutInflater)

        val extras = intent.extras
        var matchObject = extras?.get("match")
        match = Gson().fromJson(matchObject.toString(), Match::class.java)

        //check if current time is 90 minutes away from match time
        //TO MOGOCE NI NAJBOLJSA RESITEV
        val givenFormat  = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+01:00' yyyy")
        val matchTime = givenFormat.parse(match.date.toString())
        val currentTime = givenFormat.parse(givenFormat.format(System.currentTimeMillis()))
        val timeDifference = currentTime.time - matchTime.time
        val timeDifferenceMinutes = timeDifference / 60000
        if(timeDifferenceMinutes < 120 && timeDifferenceMinutes > 0){
            match.hasStarted = true
        }
        else if(timeDifferenceMinutes > 125) {
            match.hasStarted = false
            match.isFinished = true
        }

        lifecycleScope.launch{
            updateView(this@MatchDetailsActivity)
        }

        val matchNavigation = binding.matchNavigation

        val myFragment = DetailsFragment()
        val args = Bundle()
        args.putString("match", matchObject as String)
        myFragment.arguments = args
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.frameLayout, myFragment)
        fragmentTransaction.commit()

        matchNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.details -> {
                    val detailsFragment = DetailsFragment()
                    val args = Bundle()
                    args.putString("match", matchObject as String)
                    detailsFragment.arguments = args
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout, detailsFragment)
                    fragmentTransaction.commit()
                }
                R.id.standings -> {
                    //matchNavigation.selectedItemId = R.id.standings;
                    val standingsFragment = StandingsFragment()
                    val args = Bundle()
                    args.putString("homeTeam", Gson().toJson(match.homeTeam))
                    args.putString("awayTeam", Gson().toJson(match.awayTeam))
                    standingsFragment.arguments = args
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout, standingsFragment)
                    fragmentTransaction.commit()
                }
                R.id.lineups -> {
                    //matchNavigation.selectedItemId = R.id.lineups;
                    val lineupFragment = LineupFragment()
                    val args = Bundle()
                    args.putString("match", matchObject as String)
                    lineupFragment.arguments = args
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout, lineupFragment)
                    fragmentTransaction.commit()
                }
            }
            false
        }

        /*
        if(match.hasStarted && !match.isFinished && !match.isHalfTime){
            lifecycleScope.launch{
                match = MatchAPI().findById(match._id)!!
                while(!match.isFinished){
                    updateView(this@MatchDetailsActivity)
                    Thread.sleep(60000)
                }
            }
        }

         */

        binding.homeTeamImage.setOnClickListener{
            val intent = Intent(this, TeamActivity::class.java)
            intent.putExtra("team", Gson().toJson(match.homeTeam))
            startActivity(intent)
        }

        binding.awayTeamImage.setOnClickListener{
            val intent = Intent(this, TeamActivity::class.java)
            intent.putExtra("team", Gson().toJson(match.awayTeam))
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    private fun updateView(context: Context) {
        Picasso.with(context).load("https://flagcdn.com/160x120/" + match.homeTeam.iso2.lowercase() + ".png").into(binding.homeTeamImage)
        Picasso.with(context).load("https://flagcdn.com/160x120/" + match.awayTeam.iso2.lowercase() + ".png").into(binding.awayTeamImage)

        binding.homeTeamName.text = match.homeTeam.name
        binding.awayTeamName.text = match.awayTeam.name

        if(match.hasStarted && !match.isFinished){
            binding.timeOfMatch.visibility = View.GONE
            binding.dateOfMatch.visibility = View.GONE

            binding.homeTeamScoreText.text = match.homeTeamScore.toString()
            binding.awayTeamScoreText.text = match.awayTeamScore.toString()

            //IDK CE TO SPODAJ DELA
            //launch every minute
            val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+01:00' yyyy")
            val now = sdf.parse(sdf.format(System.currentTimeMillis()))
            val start = sdf.parse(match.date.toString())
            val diff = now.time - start.time
            val diffMinutes = diff / (60 * 1000) % 60
            binding.minuteText.text = "$diffMinutes'"
        }
        else if (!match.hasStarted && !match.isFinished) {
            binding.homeTeamScoreText.visibility = View.GONE
            binding.awayTeamScoreText.visibility = View.GONE
            binding.againstText.visibility = View.GONE
            binding.minuteText.visibility = View.GONE
            val dateFormat: SimpleDateFormat = SimpleDateFormat("EEE d/MM")
            val timeFormat: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val givenFormat: SimpleDateFormat =
                SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+01:00' yyyy")

            try {
                val date = givenFormat.parse(match.date.toString())
                val dateStr = dateFormat.format(date)
                val timeStr = timeFormat.format(date)

                binding.dateOfMatch.text = dateStr
                binding.timeOfMatch.text = timeStr
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        else {
            binding.minuteText.visibility = View.GONE
            binding.dateOfMatch.visibility = View.GONE
            binding.timeOfMatch.visibility = View.GONE

            binding.homeTeamScoreText.text = match.homeTeamScore.toString()
        }
    }
}