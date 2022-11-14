package com.example.worldcupapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.GoalAPI
import com.example.worldcupapp.adapter.GoalAdapter
import com.example.worldcupapp.databinding.FragmentDetailsBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    lateinit var match : Match
    lateinit var goals : MutableList<Goal>
    lateinit var recyclerView: RecyclerView
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerViewGoals
        val args = arguments
        match = Gson().fromJson(args?.getString("match"), Match::class.java)
        println(match.toString())
        binding.stadium.text = match.stadium
        if(match.homeTeamScore != null && match.awayTeamScore != null){
            if(match.homeTeamScore!! > 0 || match.awayTeamScore!! > 0){
                binding.progressBar.visibility = View.VISIBLE
                binding.loadingGoalsText.visibility = View.VISIBLE
                lifecycleScope.launch{
                    goals = GoalAPI().findByMatch(match._id)
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = GoalAdapter(mContext, goals)
                    binding.progressBar.visibility = View.GONE
                    binding.loadingGoalsText.visibility = View.GONE
                    updateView()
                }
            }
            else {
                updateView()
            }
        } else {
            updateView()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun updateView(){
        binding.homeTeamManagerName.text = match.homeTeam.manager.firstName + " " + match.homeTeam.manager.lastName
        binding.awayTeamManagerName.text = match.awayTeam.manager.firstName + " " + match.awayTeam.manager.lastName
        val homeTeamManagerImageString = match.homeTeam.manager.firstName + " " + match.homeTeam.manager.lastName + ".jpg"
        val homeTeamManagerImageString2 = homeTeamManagerImageString.replace(" ", "_").lowercase()
        val homeTeamManagerImageString3 = homeTeamManagerImageString2.replace("é", "e")
        val homeTeamManagerImageString4 = homeTeamManagerImageString3.replace("á", "a")
        val homeTeamManagerImage = resources.getIdentifier(homeTeamManagerImageString4, "drawable", activity?.packageName)
        binding.homeTeamManagerImage.setImageResource(homeTeamManagerImage)

        val awayTeamManagerImageString = match.awayTeam.manager.firstName + " " + match.awayTeam.manager.lastName + ".jpg"
        val awayTeamManagerImageString2 = awayTeamManagerImageString.replace(" ", "_").lowercase()
        val awayTeamManagerImageString3 = awayTeamManagerImageString2.replace("é", "e")
        val awayTeamManagerImageString4 = awayTeamManagerImageString3.replace("á", "a")
        val awayTeamManagerImage = resources.getIdentifier(awayTeamManagerImageString4, "drawable", activity?.packageName)
        binding.awayTeamManagerImage.setImageResource(awayTeamManagerImage)
    }
}