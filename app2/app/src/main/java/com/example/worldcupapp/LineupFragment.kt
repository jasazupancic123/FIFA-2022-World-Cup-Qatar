package com.example.worldcupapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.LineupAPI
import com.example.worldcupapp.adapter.LineupAdapter
import com.example.worldcupapp.databinding.FragmentLineupBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LineupFragment : Fragment() {
    lateinit var binding: FragmentLineupBinding
    lateinit var recyclerViewHomeLineup: RecyclerView
    lateinit var recyclerViewAwayLineup: RecyclerView
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLineupBinding.inflate(layoutInflater)
        val args = arguments
        val match = Gson().fromJson(args?.getString("match"), Match::class.java)
        val minutes = (match.date.time - System.currentTimeMillis()) / 60000
        if(minutes < 120){ //tu preveri ce se je ena ura pa pol pred tekmo kar je logicen cas za lineupe
            lifecycleScope.launch{
                try {
                    val homeTeamLineup =
                        LineupAPI().findByMatchAndTeam(match._id, match.homeTeam._id)
                    binding.progressBarHomeTeam.visibility = View.GONE
                    binding.loadingHomeTeamLineupsText.visibility = View.GONE
                    if (homeTeamLineup != null) {
                        recyclerViewHomeLineup = binding.recyclerViewHomeTeamLineup
                        recyclerViewHomeLineup.layoutManager = LinearLayoutManager(activity)
                        recyclerViewHomeLineup.adapter = LineupAdapter(mContext, homeTeamLineup)
                    } else {
                        binding.noHomeTeamLineupsText.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    println(e.message)
                    binding.noHomeTeamLineupsText.visibility = View.VISIBLE
                }
                try {
                    val awayTeamLineup = LineupAPI().findByMatchAndTeam(match._id, match.awayTeam._id)
                    binding.progressBarAwayTeam.visibility = View.GONE
                    binding.loadingAwayTeamLineupsText.visibility = View.GONE
                    if(awayTeamLineup != null){
                        recyclerViewAwayLineup = binding.recyclerViewAwayTeamLineup
                        recyclerViewAwayLineup.layoutManager = LinearLayoutManager(activity)
                        recyclerViewAwayLineup.adapter = LineupAdapter(mContext, awayTeamLineup)
                    } else {
                        binding.noAwayTeamLineupsText.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    println(e.message)
                    binding.noAwayTeamLineupsText.visibility = View.VISIBLE
                }
                binding.progressBarHomeTeam.visibility = View.GONE
                binding.loadingHomeTeamLineupsText.visibility = View.GONE
                binding.progressBarAwayTeam.visibility = View.GONE
                binding.loadingAwayTeamLineupsText.visibility = View.GONE
            }
        } else {
            binding.progressBarHomeTeam.visibility = View.GONE
            binding.loadingHomeTeamLineupsText.visibility = View.GONE
            binding.progressBarAwayTeam.visibility = View.GONE
            binding.loadingAwayTeamLineupsText.visibility = View.GONE
            binding.noHomeTeamLineupsText.visibility = View.VISIBLE
            binding.noAwayTeamLineupsText.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}