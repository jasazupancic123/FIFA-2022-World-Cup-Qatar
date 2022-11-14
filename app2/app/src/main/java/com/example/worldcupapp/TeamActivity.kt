package com.example.worldcupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.worldcupapp.databinding.ActivityTeamBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class TeamActivity : AppCompatActivity() {
    lateinit var binding: ActivityTeamBinding
    lateinit var team: Team
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        val extras = intent.extras
        var teamObject = extras?.get("team")
        team = Gson().fromJson(teamObject.toString(), Team::class.java)
        val matchNavigation = binding.teamNavigation

        updateView()

        val myFragment = TeamDetailsFragment()
        val args = Bundle()
        args.putString("team", teamObject as String)
        myFragment.arguments = args
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.frameLayout, myFragment)
        fragmentTransaction.commit()

        matchNavigation.setOnNavigationItemSelectedListener{ item ->
            when(item.itemId){
                R.id.details -> {
                    val detailsFragment = TeamDetailsFragment()
                    val args = Bundle()
                    args.putString("team", teamObject as String)
                    detailsFragment.arguments = args
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout, detailsFragment)
                    fragmentTransaction.commit()
                }
                R.id.players -> {
                    val playersFragment = PlayersFragment()
                    val args = Bundle()
                    args.putString("team", teamObject as String)
                    playersFragment.arguments = args
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout, playersFragment)
                    fragmentTransaction.commit()
                }
            }
            false
        }

        binding.backButton.setOnClickListener{
            finish()
        }

        setContentView(binding.root)
    }

    fun updateView(){
        binding.teamName.text = team.name + " (" + team.fifaCode + ")"
        Picasso.with(this).load("https://flagcdn.com/160x120/" + team.iso2.lowercase() + ".png").into(binding.teamFlag)
    }
}