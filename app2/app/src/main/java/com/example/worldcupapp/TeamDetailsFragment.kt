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
import com.example.worldcupapp.databinding.FragmentTeamDetailsBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class TeamDetailsFragment : Fragment() {
    lateinit var binding: FragmentTeamDetailsBinding
    lateinit var team: Team
    lateinit var mContext: Context
    lateinit var recyclerViewGroup: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTeamDetailsBinding.inflate(layoutInflater)
        val args = arguments
        team = Gson().fromJson(args?.getString("team"), Team::class.java)
        lifecycleScope.launch{
            try {
                val teams = TeamAPI().findByGroup(team.group)
                recyclerViewGroup = binding.recyclerViewGroup
                recyclerViewGroup.layoutManager = LinearLayoutManager(activity)
                recyclerViewGroup.adapter = TeamAdapter(mContext, teams)
                binding.progressBar.visibility = View.GONE
                binding.loadingGroupText.visibility = View.GONE
            } catch (e: Exception) {
                println(e.message)
            }
        }
        updateView()
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

    @SuppressLint("SetTextI18n")
    fun updateView() {
        val region = binding.regionText
        val noOfTitles = binding.numberOfTitlesText
        val manager = binding.managerText
        val group = binding.groupText

        region.text = team.region
        noOfTitles.text = team.noOfTitles.toString()
        manager.text = team.manager.firstName + " " + team.manager.lastName
        group.text = "Group " + team.group
    }
}