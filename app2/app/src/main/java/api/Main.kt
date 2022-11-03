package api

import com.example.worldcupapp.Goal
import com.example.worldcupapp.Player

suspend fun main() {
    val matches = MatchAPI().find()
    println(matches)
}