package com.example.worldcupapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcupapp.Player
import com.example.worldcupapp.PlayerActivity
import com.example.worldcupapp.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class PlayerAdapter : RecyclerView.Adapter<PlayerViewHolder> {
    lateinit var context: Context
    lateinit var players: List<Player>

    constructor(context: Context, players: List<Player>) {
        this.context = context
        this.players = players
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.list_player, parent, false))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player, context)
    }

    override fun getItemCount(): Int {
        return players.size
    }

}

class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var playerImage: ImageView
    var playerNameText: TextView
    var playerShitNumberText: TextView
    var playerButton: Button

    init {
        playerImage = itemView.findViewById(R.id.playerImage)
        playerNameText = itemView.findViewById(R.id.playerNameText)
        playerShitNumberText = itemView.findViewById(R.id.playerShitNumberText)
        playerButton = itemView.findViewById(R.id.playerButton)
    }

    fun bind(player: Player, context: Context) {
        playerNameText.text = when (player.firstName.length > 10) {
            true -> {
                player.firstName[0] + ". " + player.firstName[player.firstName.indexOf(" ") - 1] + ". " + player.lastName
            }
            else -> player.firstName + " " + player.lastName
        }
        playerShitNumberText.text = player.shirtNumber.toString()
        Picasso.with(context).load("https://api.sofascore.app/api/v1/player/12994/image").into(playerImage)

        playerButton.setOnClickListener{
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("player", Gson().toJson(player))
            context.startActivity(intent)
        }


        //val resourceId = context.resources.getIdentifier(player.image, "drawable", context.packageName)
        //playerImage.setImageResource(resourceId)
    }

}