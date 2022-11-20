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
import api.PlayerAPI
import com.example.worldcupapp.adapter.PlayerAdapter
import com.example.worldcupapp.databinding.FragmentPlayersBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class PlayersFragment : Fragment() {
    lateinit var binding: FragmentPlayersBinding
    lateinit var mContext: Context
    lateinit var team: Team
    lateinit var goalkepers: MutableList<Player>
    lateinit var defenders: MutableList<Player>
    lateinit var midfielders: MutableList<Player>
    lateinit var forwards: MutableList<Player>
    lateinit var recyclerViewGoalkepers: RecyclerView
    lateinit var recyclerViewDefenders: RecyclerView
    lateinit var recyclerViewMidfielders: RecyclerView
    lateinit var recyclerViewForwards: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPlayersBinding.inflate(layoutInflater)
        val args = arguments
        team = Gson().fromJson(args?.getString("team"), Team::class.java)
        lifecycleScope.launch{
            val players = PlayerAPI().findByTeam(team._id)
            goalkepers = players.filter { it.position == "goalkeeper" }.toMutableList()
            defenders = players.filter { it.position == "defender" }.toMutableList()
            midfielders = players.filter { it.position == "midfielder" }.toMutableList()
            forwards = players.filter { it.position == "forward" }.toMutableList()

            binding.progressBar.visibility = View.GONE
            binding.loadingPlayersText.visibility = View.GONE

            binding.goalkeepersCard.visibility = View.VISIBLE
            recyclerViewGoalkepers = binding.recyclerViewGoalkeepers
            recyclerViewGoalkepers.layoutManager = LinearLayoutManager(activity)
            recyclerViewGoalkepers.adapter = PlayerAdapter(mContext, goalkepers, 0)

            binding.defendersCard.visibility = View.VISIBLE
            recyclerViewDefenders = binding.recyclerViewDefenders
            recyclerViewDefenders.layoutManager = LinearLayoutManager(activity)
            recyclerViewDefenders.adapter = PlayerAdapter(mContext, defenders, 0)

            binding.midfieldersCard.visibility = View.VISIBLE
            recyclerViewMidfielders = binding.recyclerViewMidfielders
            recyclerViewMidfielders.layoutManager = LinearLayoutManager(activity)
            recyclerViewMidfielders.adapter = PlayerAdapter(mContext, midfielders, 0)

            binding.forwardsCard.visibility = View.VISIBLE
            recyclerViewForwards = binding.recyclerViewForwards
            recyclerViewForwards.layoutManager = LinearLayoutManager(activity)
            recyclerViewForwards.adapter = PlayerAdapter(mContext, forwards, 0)
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