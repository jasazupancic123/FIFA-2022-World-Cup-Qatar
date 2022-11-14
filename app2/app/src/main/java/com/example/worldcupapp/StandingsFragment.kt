package com.example.worldcupapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.TeamAPI
import com.example.worldcupapp.adapter.TeamAdapter
import com.example.worldcupapp.databinding.FragmentStandingsBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class StandingsFragment : Fragment() {
    lateinit var binding: FragmentStandingsBinding
    lateinit var mContext: Context
    lateinit var homeTeamTeams: MutableList<Team>
    lateinit var awayTeamTeams: MutableList<Team>
    lateinit var recyclerTeamsHome: RecyclerView
    lateinit var recyclerTeamsAway: RecyclerView
    lateinit var teams: MutableList<Team>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentStandingsBinding.inflate(layoutInflater)
        val args = arguments
        val homeTeam = Gson().fromJson(args?.getString("homeTeam"), Team::class.java)
        val awayTeam = Gson().fromJson(args?.getString("awayTeam"), Team::class.java)
        println(homeTeam.toString())
        println(awayTeam.toString())
        recyclerTeamsHome = binding.homeStandingsRecyclerView
        recyclerTeamsAway = binding.awayStandingsRecyclerView
        lifecycleScope.launch{
            if(homeTeam.group == awayTeam.group){
                binding.awayTeamCard.visibility = View.GONE
                teams = TeamAPI().findByGroup(homeTeam.group)
                recyclerTeamsHome.layoutManager = LinearLayoutManager(activity)
                recyclerTeamsHome.adapter = TeamAdapter(mContext, teams)
                binding.homeTeamGroupText.text = "Group " + homeTeam.group
            } else {
                homeTeamTeams = TeamAPI().findByGroup(homeTeam.group)
                awayTeamTeams = TeamAPI().findByGroup(awayTeam.group)
                binding.homeTeamGroupText.text = "Group " + homeTeam.group
                binding.awayTeamGroupText.text = "Group " + awayTeam.group
                binding.awayTeamCard.visibility = View.VISIBLE
            }
            binding.progressBar.visibility = View.GONE
            binding.loadingStandingsText.visibility = View.GONE
            binding.homeTeamCard.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}