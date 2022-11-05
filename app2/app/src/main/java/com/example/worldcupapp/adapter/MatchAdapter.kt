package com.example.worldcupapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcupapp.Match
import com.example.worldcupapp.R
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat

//the app generates a card for only the first match, make it generate a card for each match



public class MatchAdapter : RecyclerView.Adapter<MatchViewHolder> {
    lateinit var context: Context
    lateinit var matches: List<Match>

    constructor(context: Context, matches: List<Match>) {
        this.context = context
        this.matches = matches
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_match, parent, false))
    }

    //add a card for each match
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match, context)
    }

    override fun getItemCount(): Int {
        println("matches size: ${matches.size}")
        return matches.size
    }

    /*
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches.get(position)
        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.homeTeam.iso2).into(holder.homeTeamImage)
        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.awayTeam.iso2).into(holder.awayTeamImage)

        holder.homeTeamName.text = match.homeTeam.name
        holder.awayTeamName.text = match.awayTeam.name

        val dateFormat : SimpleDateFormat = SimpleDateFormat("EEE, d, MMM")
        val timeFormat : SimpleDateFormat = SimpleDateFormat("hh:mm a")
        val givenFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }



     */
}

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var homeTeamImage: ImageView
    var awayTeamImage: ImageView
    var homeTeamName: TextView
    var awayTeamName: TextView
    var dateAndTimeOfMatch: TextView

    init {
        homeTeamImage = itemView.findViewById(R.id.homeTeamImage)
        awayTeamImage = itemView.findViewById(R.id.awayTeamImage)
        homeTeamName = itemView.findViewById(R.id.homeTeamName)
        awayTeamName = itemView.findViewById(R.id.awayTeamName)
        dateAndTimeOfMatch = itemView.findViewById(R.id.dateAndTimeOfMatch)
    }

    fun bind(match: Match, context: Context) {
        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.homeTeam.name).into(homeTeamImage)
        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.awayTeam.name).into(awayTeamImage)

        homeTeamName.text = match.homeTeam.name
        awayTeamName.text = match.awayTeam.name

        val dateFormat : SimpleDateFormat = SimpleDateFormat("EEE, d, MMM")
        val timeFormat : SimpleDateFormat = SimpleDateFormat("hh:mm a")
        val givenFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }
}

/*
class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder {
    lateinit var homeTeamName: TextView
    lateinit var awayTeamName: TextView
    lateinit var dateAndTimeOfMatch: TextView
    //lateinit var stadiumText: TextView ???
    lateinit var homeTeamImage: ImageView
    lateinit var awayTeamImage: ImageView

    constructor(itemView: View) : super(itemView) {
        homeTeamName = itemView.findViewById(R.id.homeTeamName)
        awayTeamName = itemView.findViewById(R.id.awayTeamName)
        dateAndTimeOfMatch = itemView.findViewById(R.id.dateAndTimeOfMatch)
        //stadiumText = itemView.findViewById(R.id.stadiumName)
        homeTeamImage = itemView.findViewById(R.id.homeTeamImage)
        awayTeamImage = itemView.findViewById(R.id.awayTeamImage)
    }

    fun bind(match: Match) {
        team1.text = match.team1
        team2.text = match.team2
        stadium.text = match.stadium
        val date = match.date
        val time = match.time
        val dateAndTime = date + " " + time
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val mDate = sdf.parse(dateAndTime)
            sdf.applyPattern("dd MMM yyyy")
            val dateText = sdf.format(mDate)
            sdf.applyPattern("hh:mm a")
            val timeText = sdf.format(mDate)
            this.date.text = dateText
            this.time.text = timeText
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        Picasso.get().load(match.team1Image).into(team1Image)
        Picasso.get().load(match.team2Image).into(team2Image)
    }
}
 */