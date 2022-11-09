package com.example.worldcupapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcupapp.Lineup
import com.example.worldcupapp.R
import org.w3c.dom.Text

class LineupAdapter : RecyclerView.Adapter<LineupViewHolder> {
    lateinit var context: Context
    lateinit var lineup: Lineup

    constructor(context: Context, lineup : Lineup) {
        this.context = context
        this.lineup = lineup
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineupViewHolder {
        return LineupViewHolder(LayoutInflater.from(context).inflate(R.layout.list_lineup, parent, false))
    }

    override fun onBindViewHolder(holder: LineupViewHolder, position: Int) {
        holder.bind(lineup, context)
    }

    override fun getItemCount(): Int {
        return 1
    }
}

class LineupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var goalkeeperName: TextView
    lateinit var defendersName: TextView
    lateinit var midfieldersName: TextView
    lateinit var forwardsName: TextView
    lateinit var formationStyle: TextView
    lateinit var teamName: TextView

    init {
        goalkeeperName = itemView.findViewById(R.id.goalkeeperName)
        defendersName = itemView.findViewById(R.id.defenderNames)
        midfieldersName = itemView.findViewById(R.id.midfielderNames)
        forwardsName = itemView.findViewById(R.id.forwardNames)
        formationStyle = itemView.findViewById(R.id.formationStyle)
        teamName = itemView.findViewById(R.id.teamName)
    }

    @SuppressLint("SetTextI18n")
    fun bind(lineup: Lineup, context: Context) {
        teamName.text = lineup.team.name
        formationStyle.text = lineup.type
        goalkeeperName.text = lineup.goalkeeper.shirtNumber.toString() + "     " + lineup.goalkeeper.firstName + " " + lineup.goalkeeper.lastName
        for (defender in lineup.defenders) {
            defendersName.text = defender.shirtNumber.toString() + "     " + defendersName.text.toString() + defender.firstName + " " + defender.lastName + "\n"
        }
        for (midfielder in lineup.midfielders) {
            midfieldersName.text = midfielder.shirtNumber.toString() + "     " + midfieldersName.text.toString() + midfielder.firstName + " " + midfielder.lastName + "\n"
        }
        for (forward in lineup.attackers) {
            forwardsName.text = forward.shirtNumber.toString() + "     " + forwardsName.text.toString() + forward.firstName + " " + forward.lastName + "\n"
        }
    }
}