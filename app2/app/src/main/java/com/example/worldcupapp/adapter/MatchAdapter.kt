package com.example.worldcupapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcupapp.Match
import com.example.worldcupapp.MatchDetailsActivity
import com.example.worldcupapp.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat

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

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match, context)
    }

    override fun getItemCount(): Int {
        return matches.size
    }
}

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var homeTeamImage: ImageView
    var awayTeamImage: ImageView
    var homeTeamName: TextView
    var awayTeamName: TextView
    var dateOfMatch: TextView
    var timeOfMatch: TextView
    var button: Button

    init {
        homeTeamImage = itemView.findViewById(R.id.homeTeamImage)
        awayTeamImage = itemView.findViewById(R.id.awayTeamImage)
        homeTeamName = itemView.findViewById(R.id.homeTeamName)
        awayTeamName = itemView.findViewById(R.id.awayTeamName)
        dateOfMatch = itemView.findViewById(R.id.dateOfMatch)
        timeOfMatch = itemView.findViewById(R.id.timeOfMatch)
        button = itemView.findViewById(R.id.button)
    }

    fun bind(match: Match, context: Context) {
        val test = match.homeTeam
        println(match.homeTeam.name)
        Picasso.with(context).load("https://flagcdn.com/160x120/" + match.homeTeam.iso2.lowercase() + ".png").into(homeTeamImage)
        //println("https://flagcdn.com/16x12/" + match.homeTeam.iso2.lowercase() + ".png")
        Picasso.with(context).load("https://flagcdn.com/160x120/" + match.awayTeam.iso2.lowercase() + ".png").into(awayTeamImage)
        //        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.awayTeam.name).into(awayTeamImage)

        homeTeamName.text = match.homeTeam.fifaCode
        awayTeamName.text = match.awayTeam.fifaCode

        val dateFormat : SimpleDateFormat = SimpleDateFormat("EEE d/MM")
        val timeFormat : SimpleDateFormat = SimpleDateFormat("HH:mm")
        val givenFormat: SimpleDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+01:00' yyyy")

        try {
            val date = givenFormat.parse(match.date.toString())
            val dateStr = dateFormat.format(date)
            val timeStr = timeFormat.format(date)
            dateOfMatch.text = dateStr
            timeOfMatch.text = timeStr
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        button.setOnClickListener {
            val intent = Intent(context, MatchDetailsActivity::class.java)
            intent.putExtra("match", Gson().toJson(match))
            context.startActivity(intent)
        }
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