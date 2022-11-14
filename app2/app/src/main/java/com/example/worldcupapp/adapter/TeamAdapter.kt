package com.example.worldcupapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcupapp.R
import com.example.worldcupapp.Team
import com.example.worldcupapp.TeamActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class TeamAdapter : RecyclerView.Adapter<TeamViewHolder> {
    lateinit var context: Context
    lateinit var teams: List<Team>

    constructor(context: Context, teams: List<Team>) {
        this.context = context
        this.teams = teams
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.list_team, parent, false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team, context)
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}

class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var teamImage: ImageView
    var teamName: TextView
    var teamStats: TextView
    var teamButton: Button

    init {
        teamImage = itemView.findViewById(R.id.teamImage)
        teamName = itemView.findViewById(R.id.teamName)
        teamStats = itemView.findViewById(R.id.teamStats)
        teamButton = itemView.findViewById(R.id.teamButton)
    }

    @SuppressLint("SetTextI18n")
    fun bind(team: Team, context: Context) {
        Picasso.with(context).load("https://flagcdn.com/160x120/" + team.iso2.lowercase() + ".png").into(teamImage)
        teamName.text = team.name + " (" + team.fifaCode + ")"
        teamStats.text = team.matchesWon.toString() + "W " + team.matchesDrawn + "D " + team.matchesLost + "L   " + team.points

        teamButton.setOnClickListener {
            println("Team button clicked")
            val intent = Intent(context, TeamActivity::class.java)
            intent.putExtra("team", Gson().toJson(team))
            context.startActivity(intent)
        }
    }
}