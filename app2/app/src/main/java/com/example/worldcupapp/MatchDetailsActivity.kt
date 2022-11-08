package com.example.worldcupapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
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
        matchNavigation.selectedItemId = R.id.details;

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
                    matchNavigation.selectedItemId = R.id.details;
                }
                R.id.standings -> {
                    matchNavigation.selectedItemId = R.id.standings;
                }
                R.id.lineups -> {
                    matchNavigation.selectedItemId = R.id.lineups;
                }
            }
            false
        }
        setContentView(binding.root)
    }

    private fun updateView(context: Context) {
        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.homeTeam.name).into(binding.homeTeamImage)
        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.awayTeam.name).into(binding.awayTeamImage)
        binding.homeTeamName.text = match.homeTeam.name
        binding.awayTeamName.text = match.awayTeam.name
        binding.stadium.text = match.stadium

        val dateFormat : SimpleDateFormat = SimpleDateFormat("EEE d/MM")
        val timeFormat : SimpleDateFormat = SimpleDateFormat("HH:mm")
        val givenFormat: SimpleDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+01:00' yyyy")

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
}