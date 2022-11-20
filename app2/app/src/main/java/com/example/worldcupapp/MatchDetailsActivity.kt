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

        if(match.hasStarted && match.isHalfTime){
            binding.timeOfMatch.visibility = View.GONE
            binding.dateOfMatch.visibility = View.GONE

            binding.homeTeamScoreText.text = match.homeTeamScore.toString()
            binding.awayTeamScoreText.text = match.awayTeamScore.toString()
            binding.minuteText.text = "HT"
        }
        else if(match.hasStarted && !match.isFinished && match.halfTimeResumedAt == null){
            binding.timeOfMatch.visibility = View.GONE
            binding.dateOfMatch.visibility = View.GONE

            binding.homeTeamScoreText.text = match.homeTeamScore.toString()
            binding.awayTeamScoreText.text = match.awayTeamScore.toString()

            val matchTime: String = match.date.toString()[12].toString() + match.date.toString()[13] + match.date.toString()[14] + match.date.toString()[15] + match.date.toString()[16] + match.date.toString()[17] + match.date.toString()[18] + match.date.toString()[19]
            val sdf = SimpleDateFormat("HH:mm:ss")
            try {
                val now = sdf.parse(sdf.format(System.currentTimeMillis()))
                val start = sdf.parse(matchTime)
                val diff = now.time - start.time
                val diffMinutes = diff / (60 * 1000) % 60
                binding.minuteText.text = "$diffMinutes'"
            }
            catch (e: ParseException){
                println(e)
                println("Date error")
            }
        }
        else if (match.halfTimeResumedAt != null && !match.isFinished){
            binding.timeOfMatch.visibility = View.GONE
            binding.dateOfMatch.visibility = View.GONE

            binding.homeTeamScoreText.text = match.homeTeamScore.toString()
            binding.awayTeamScoreText.text = match.awayTeamScore.toString()

            val matchTime: String = match.halfTimeResumedAt.toString()[12].toString() + match.halfTimeResumedAt.toString()[13] + match.halfTimeResumedAt.toString()[14] + match.halfTimeResumedAt.toString()[15] + match.halfTimeResumedAt.toString()[16] + match.halfTimeResumedAt.toString()[17] + match.halfTimeResumedAt.toString()[18] + match.halfTimeResumedAt.toString()[19]
            val sdf = SimpleDateFormat("HH:mm:ss")
            try {
                val now = sdf.parse(sdf.format(System.currentTimeMillis()))
                val start = sdf.parse(matchTime)
                val diff = now.time - start.time
                var diffMinutes = diff / (60 * 1000) % 60
                diffMinutes += 45
                binding.minuteText.text = "$diffMinutes'"
            }
            catch (e: ParseException){
                println(e)
                println("Date error")
            }
        }
        else if (match.isFinished){
            println("palacinke")
            binding.timeOfMatch.visibility = View.GONE
            binding.dateOfMatch.visibility = View.GONE
            binding.minuteText.text = "FT"

            binding.homeTeamScoreText.text = match.homeTeamScore.toString()
            binding.awayTeamScoreText.text = match.awayTeamScore.toString()
        }
        else {
            binding.homeTeamScoreText.visibility = View.GONE
            binding.awayTeamScoreText.visibility = View.GONE
            binding.againstText.visibility = View.GONE
            binding.minuteText.visibility = View.GONE
            val dateFormat: SimpleDateFormat = SimpleDateFormat("EEE d/MM")
            val timeFormat: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val givenFormat: SimpleDateFormat =
                SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+01:00' yyyy")

            val days = match.date.toString()[8].toString() + match.date.toString()[9].toString()
            val month = match.date.toString()[4].toString() + match.date.toString()[5].toString() + match.date.toString()[6].toString()
            val time = match.date.toString()[11].toString() + match.date.toString()[12].toString() + match.date.toString()[13].toString() + match.date.toString()[14].toString() + match.date.toString()[15].toString()
            binding.dateOfMatch.text = days + " " + month
            binding.timeOfMatch.text = time
        }
    }
}