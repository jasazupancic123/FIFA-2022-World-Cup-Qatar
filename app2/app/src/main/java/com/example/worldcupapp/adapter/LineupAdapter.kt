package com.example.worldcupapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
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
    lateinit var goalkeperRecycler: RecyclerView
    lateinit var defendersRecycler: RecyclerView
    lateinit var midfieldersRecycler: RecyclerView
    lateinit var attackersRecycler: RecyclerView
    lateinit var substitutesRecycler: RecyclerView
    lateinit var formationStyle: TextView
    lateinit var teamName: TextView

    init {
        goalkeperRecycler = itemView.findViewById(R.id.goalkeeperRecycler)
        defendersRecycler = itemView.findViewById(R.id.defenderRecycler)
        midfieldersRecycler = itemView.findViewById(R.id.midfielderRecycler)
        attackersRecycler = itemView.findViewById(R.id.forwardsRecycler)
        substitutesRecycler = itemView.findViewById(R.id.substitutionsRecycler)
        formationStyle = itemView.findViewById(R.id.formationStyle)
        teamName = itemView.findViewById(R.id.teamName)
    }

    @SuppressLint("SetTextI18n")
    fun bind(lineup: Lineup, context: Context) {
        teamName.text = lineup.team.name
        formationStyle.text = lineup.type
        goalkeperRecycler.layoutManager = LinearLayoutManager(context)
        goalkeperRecycler.adapter = PlayerAdapter(context, mutableListOf(lineup.goalkeeper))
        for (defender in lineup.defenders) {
            defendersRecycler.layoutManager = LinearLayoutManager(context)
            defendersRecycler.adapter = PlayerAdapter(context, lineup.defenders)
        }
        for (midfielder in lineup.midfielders) {
            midfieldersRecycler.layoutManager = LinearLayoutManager(context)
            midfieldersRecycler.adapter = PlayerAdapter(context, lineup.midfielders)
        }
        for (forward in lineup.attackers) {
            attackersRecycler.layoutManager = LinearLayoutManager(context)
            attackersRecycler.adapter = PlayerAdapter(context, lineup.attackers)
        }
        for (substitute in lineup.substitutes) {
            substitutesRecycler.layoutManager = LinearLayoutManager(context)
            substitutesRecycler.adapter = PlayerAdapter(context, lineup.substitutes)
        }
    }
}