package com.example.worldcupapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcupapp.Goal
import com.example.worldcupinputapp.R

public class GoalAdapter: RecyclerView.Adapter<GoalViewHolder> {
    lateinit var goals: MutableList<Goal>
    lateinit var context: Context

    constructor(context: Context, goals: MutableList<Goal>) {
        this.context = context
        this.goals = goals
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(LayoutInflater.from(context).inflate(R.layout.list_goal, parent, false))
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = goals[position]
        println(goal.toString())
        holder.bind(goal, context)
    }

    override fun getItemCount(): Int {
        return goals.size
    }
}

class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var homeGoalImage: ImageView
    var homeMinuteText: TextView
    var homeScoreText: TextView
    var homePlayerName: TextView

    var awayGoalImage: ImageView
    var awayMinuteText: TextView
    var awayScoreText: TextView
    var awayPlayerName: TextView

    init {
        homeGoalImage = itemView.findViewById(R.id.homeGoalImage)
        homeMinuteText = itemView.findViewById(R.id.homeMinuteText)
        homeScoreText = itemView.findViewById(R.id.homeScoreText)
        homePlayerName = itemView.findViewById(R.id.homePlayerName)

        awayGoalImage = itemView.findViewById(R.id.awayGoalImage)
        awayMinuteText = itemView.findViewById(R.id.awayMinuteText)
        awayScoreText = itemView.findViewById(R.id.awayScoreText)
        awayPlayerName = itemView.findViewById(R.id.awayPlayerName)
    }

    fun bind(goal: Goal, context: Context) {
        if(goal.isHomeTeamGoal){
            awayGoalImage.visibility = View.GONE
            awayMinuteText.visibility = View.GONE
            awayScoreText.visibility = View.GONE
            awayPlayerName.visibility = View.GONE

            homeMinuteText.text = goal.minute.toString()
            homeScoreText.text = goal.currentScore
            homePlayerName.text = goal.scorer.firstName[0] + ". " + goal.scorer.lastName
        } else {
            homeGoalImage.visibility = View.GONE
            homeMinuteText.visibility = View.GONE
            homeScoreText.visibility = View.GONE
            homePlayerName.visibility = View.GONE

            awayMinuteText.text = goal.minute.toString()
            awayScoreText.text = goal.currentScore
            awayPlayerName.text = goal.scorer.firstName[0] + ". " + goal.scorer.lastName
        }
    }
}