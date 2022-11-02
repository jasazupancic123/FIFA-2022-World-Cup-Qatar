package api

import com.example.worldcupapp.Goal
import com.example.worldcupapp.Player

suspend fun main() {
    val teamAPI = TeamAPI()
    val teams = teamAPI.find()
    for (team in teams) {
        println(team.toString())
    }
}