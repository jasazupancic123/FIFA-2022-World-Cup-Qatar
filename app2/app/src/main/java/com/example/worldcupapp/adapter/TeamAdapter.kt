package com.example.worldcupapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcupapp.R
import com.example.worldcupapp.Team
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

    init {
        teamImage = itemView.findViewById(R.id.teamImage)
        teamName = itemView.findViewById(R.id.teamName)
        teamStats = itemView.findViewById(R.id.teamStats)
    }

    @SuppressLint("SetTextI18n")
    fun bind(team: Team, context: Context) {
        Picasso.with(context).load("https://countryflagsapi.com/png/" + team.name).into(teamImage)
        teamName.text = team.name + " (" + team.fifaCode + ")"
        teamStats.text = team.matchesWon.toString() + "W " + team.matchesDrawn + "D " + team.matchesLost + "L   " + team.points
    }
}