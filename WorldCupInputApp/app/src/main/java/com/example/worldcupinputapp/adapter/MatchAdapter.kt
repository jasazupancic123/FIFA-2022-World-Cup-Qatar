package com.example.worldcupapp.adapter

import android.annotation.SuppressLint
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
import com.example.worldcupinputapp.MatchUpdateActivity
import com.example.worldcupinputapp.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

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

    var homeTeamScore: TextView
    var awayTeamScore: TextView
    var againstText: TextView
    var minuteText: TextView

    init {
        homeTeamImage = itemView.findViewById(R.id.homeTeamImage)
        awayTeamImage = itemView.findViewById(R.id.awayTeamImage)
        homeTeamName = itemView.findViewById(R.id.homeTeamName)
        awayTeamName = itemView.findViewById(R.id.awayTeamName)
        dateOfMatch = itemView.findViewById(R.id.dateOfMatch1)
        timeOfMatch = itemView.findViewById(R.id.timeOfMatch)
        button = itemView.findViewById(R.id.button)
        homeTeamScore = itemView.findViewById(R.id.homeTeamScore)
        awayTeamScore = itemView.findViewById(R.id.awayTeamScore)
        againstText = itemView.findViewById(R.id.againstText)
        minuteText = itemView.findViewById(R.id.minuteText)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(match: Match, context: Context) {
        println(match.homeTeam.name)
        Picasso.with(context).load("https://flagcdn.com/160x120/" + match.homeTeam.iso2.lowercase() + ".png").into(homeTeamImage)
        //println("https://flagcdn.com/16x12/" + match.homeTeam.iso2.lowercase() + ".png")
        Picasso.with(context).load("https://flagcdn.com/160x120/" + match.awayTeam.iso2.lowercase() + ".png").into(awayTeamImage)
        //        Picasso.with(context).load("https://countryflagsapi.com/png/" + match.awayTeam.name).into(awayTeamImage)

        homeTeamName.text = match.homeTeam.fifaCode
        awayTeamName.text = match.awayTeam.fifaCode

        /*
        if(match.homeTeam.name == "Senegal"){
            match.hasStarted = true
            val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+01:00' yyyy")
            val now = sdf.parse(sdf.format(System.currentTimeMillis()))
            val start = sdf.parse(match.date.toString())
            val diff = now.time - start.time
            val diffMinutes = diff / (60 * 1000) % 60
            minuteText.text = "$diffMinutes'"
        }

         */

        if(match.hasStarted && match.isHalfTime){
            dateOfMatch.visibility = View.GONE
            timeOfMatch.visibility = View.GONE
            homeTeamScore.text = match.homeTeamScore.toString()
            awayTeamScore.text = match.awayTeamScore.toString()
            minuteText.text = "HT"
        } else if(match.hasStarted && !match.isFinished && match.halfTimeResumedAt == null){
            dateOfMatch.visibility = View.GONE
            timeOfMatch.visibility = View.GONE
            homeTeamScore.text = match.homeTeamScore.toString()
            awayTeamScore.text = match.awayTeamScore.toString()
            val matchTime: String = match.date.toString()[12].toString() + match.date.toString()[13] + match.date.toString()[14] + match.date.toString()[15] + match.date.toString()[16] + match.date.toString()[17] + match.date.toString()[18] + match.date.toString()[19]
            val sdf = SimpleDateFormat("HH:mm:ss")
            try {
                val now = sdf.parse(sdf.format(System.currentTimeMillis()))
                val start = sdf.parse(matchTime)
                val diff = now.time - start.time
                val diffMinutes = diff / (60 * 1000) % 60
                minuteText.text = "$diffMinutes'"
            }
            catch (e: ParseException){
                println(e)
                println("Date error")
            }
        }
        else if (match.halfTimeResumedAt != null && !match.isFinished){
            dateOfMatch.visibility = View.GONE
            timeOfMatch.visibility = View.GONE
            homeTeamScore.text = match.homeTeamScore.toString()
            awayTeamScore.text = match.awayTeamScore.toString()
            val matchTime: String = match.halfTimeResumedAt.toString()[12].toString() + match.halfTimeResumedAt.toString()[13] + match.halfTimeResumedAt.toString()[14] + match.halfTimeResumedAt.toString()[15] + match.halfTimeResumedAt.toString()[16] + match.halfTimeResumedAt.toString()[17] + match.halfTimeResumedAt.toString()[18] + match.halfTimeResumedAt.toString()[19]
            val sdf = SimpleDateFormat("HH:mm:ss")
            try {
                val now = sdf.parse(sdf.format(System.currentTimeMillis()))
                val start = sdf.parse(matchTime)
                val diff = now.time - start.time
                var diffMinutes = diff / (60 * 1000) % 60
                diffMinutes += 45
                minuteText.text = "$diffMinutes'"
            }
            catch (e: ParseException){
                println(e)
                println("Date error")
            }
        } else if (match.isFinished){
            dateOfMatch.visibility = View.GONE
            timeOfMatch.visibility = View.GONE
            homeTeamScore.text = match.homeTeamScore.toString()
            awayTeamScore.text = match.awayTeamScore.toString()
        }
        else {
            homeTeamScore.visibility = View.GONE
            awayTeamScore.visibility = View.GONE
            againstText.visibility = View.GONE
            minuteText.visibility = View.GONE

            val days = match.date.toString()[8].toString() + match.date.toString()[9].toString()
            val month = match.date.toString()[4].toString() + match.date.toString()[5].toString() + match.date.toString()[6].toString()
            val time = match.date.toString()[11].toString() + match.date.toString()[12].toString() + match.date.toString()[13].toString() + match.date.toString()[14].toString() + match.date.toString()[15].toString()
            dateOfMatch.text = days + " " + month
            timeOfMatch.text = time
        }
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, MatchUpdateActivity::class.java)
            intent.putExtra("match", Gson().toJson(match))
            context.startActivity(intent)
        })
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