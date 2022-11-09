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
        println(match.toString())
        println("time : ${match.date.time} - ${System.currentTimeMillis()}") //TO JE ZA SPODAJ
        if(true){
            lifecycleScope.launch{
                val homeTeamLineup = LineupAPI().findByMatchAndTeam(match._id, match.homeTeam._id)
                binding.progressBar.visibility = View.GONE
                binding.loadingLineupsText.visibility = View.GONE
                if(homeTeamLineup != null){
                    recyclerViewHomeLineup = binding.recyclerViewHomeTeamLineup
                    recyclerViewHomeLineup.layoutManager = LinearLayoutManager(activity)
                    recyclerViewHomeLineup.adapter = LineupAdapter(mContext, homeTeamLineup)
                } else {
                    binding.noLineupsText.visibility = View.VISIBLE
                }
                val awayTeamLineup = LineupAPI().findByMatchAndTeam(match._id, match.awayTeam._id)
                if(awayTeamLineup != null){
                    recyclerViewAwayLineup = binding.recyclerViewAwayTeamLineup
                    recyclerViewAwayLineup.layoutManager = LinearLayoutManager(activity)
                    recyclerViewAwayLineup.adapter = LineupAdapter(mContext, awayTeamLineup)
                } else {
                    //do nothing, just display the no lineup text
                }
            }
        } else {
            //binding?.lineupText?.text = "Lineups are not available until after the match has started."
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