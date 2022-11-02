package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Lineup
import com.example.worldcupapp.Player
import java.text.SimpleDateFormat

class LineupAPI {
    val url = "http://localhost:3000/lineup"

    suspend fun find(): MutableList<Lineup> {
        val client = HttpClient()
        val response: HttpResponse = client.get(url) {
            method = HttpMethod.Get
        }

        if (response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val lineups: MutableList<Lineup> = mutableListOf()
        for (lineup in data!!.jsonArray) {
            val lineupObject = lineup.jsonObject
            val _id = lineupObject["_id"]!!.jsonPrimitive.content
            val type = lineupObject["type"]!!.jsonPrimitive.content

            val teamId = lineupObject["team"]!!.jsonPrimitive.content
            val team = TeamAPI().findById(teamId)

            val matchId = lineupObject["match"]!!.jsonPrimitive.content
            val match = MatchAPI().findById(matchId)

            val goalkeeperId = lineupObject["goalkeeper"]!!.jsonPrimitive.content
            val goalkeeper = PlayerAPI().findById(goalkeeperId)

            val defenders: MutableList<Player> = mutableListOf()
            for (defender in lineupObject["defenders"]!!.jsonArray) {
                val defenderId = defender.jsonPrimitive.content
                val defender = PlayerAPI().findById(defenderId)
                defenders.add(defender)
            }

            val midfielders: MutableList<Player> = mutableListOf()
            for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
                val midfielderId = midfielder.jsonPrimitive.content
                val midfielder = PlayerAPI().findById(midfielderId)
                midfielders.add(midfielder)
            }

            val attackers: MutableList<Player> = mutableListOf()
            for (attacker in lineupObject["attackers"]!!.jsonArray) {
                val attackerId = attacker.jsonPrimitive.content
                val attacker = PlayerAPI().findById(attackerId)
                attackers.add(attacker)
            }

            val substitutes: MutableList<Player> = mutableListOf()
            for (substitute in lineupObject["substitutes"]!!.jsonArray) {
                val substituteId = substitute.jsonPrimitive.content
                val substitute = PlayerAPI().findById(substituteId)
                substitutes.add(substitute)
            }

            val lineup = Lineup(
                _id,
                type,
                team,
                match,
                goalkeeper,
                defenders,
                midfielders,
                attackers,
                substitutes
            )
            lineups.add(lineup)
        }
        return lineups
    }

    suspend fun findById(lineupId: String) : Lineup {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/" + lineupId) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val lineupObject = data!!.jsonObject
        val _id = lineupObject["_id"]!!.jsonPrimitive.content
        val type = lineupObject["type"]!!.jsonPrimitive.content

        val teamId = lineupObject["team"]!!.jsonPrimitive.content
        val team = TeamAPI().findById(teamId)

        val matchId = lineupObject["match"]!!.jsonPrimitive.content
        val match = MatchAPI().findById(matchId)

        val goalkeeperId = lineupObject["goalkeeper"]!!.jsonPrimitive.content
        val goalkeeper = PlayerAPI().findById(goalkeeperId)

        val defenders: MutableList<Player> = mutableListOf()
        for (defender in lineupObject["defenders"]!!.jsonArray) {
            val defenderId = defender.jsonPrimitive.content
            val defender = PlayerAPI().findById(defenderId)
            defenders.add(defender)
        }

        val midfielders: MutableList<Player> = mutableListOf()
        for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
            val midfielderId = midfielder.jsonPrimitive.content
            val midfielder = PlayerAPI().findById(midfielderId)
            midfielders.add(midfielder)
        }

        val attackers: MutableList<Player> = mutableListOf()
        for (attacker in lineupObject["attackers"]!!.jsonArray) {
            val attackerId = attacker.jsonPrimitive.content
            val attacker = PlayerAPI().findById(attackerId)
            attackers.add(attacker)
        }

        val substitutes: MutableList<Player> = mutableListOf()
        for (substitute in lineupObject["substitutes"]!!.jsonArray) {
            val substituteId = substitute.jsonPrimitive.content
            val substitute = PlayerAPI().findById(substituteId)
            substitutes.add(substitute)
        }

        val lineup = Lineup(
            _id,
            type,
            team,
            match,
            goalkeeper,
            defenders,
            midfielders,
            attackers,
            substitutes
        )
        return lineup
    }

    suspend fun findByMatchAndTeam(matchId: String, teamId: String) : Lineup {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/match/" + matchId + "/team/" + teamId) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val lineupObject = data!!.jsonObject
        val _id = lineupObject["_id"]!!.jsonPrimitive.content
        val type = lineupObject["type"]!!.jsonPrimitive.content

        val team = TeamAPI().findById(teamId)

        val match = MatchAPI().findById(matchId)

        val goalkeeperId = lineupObject["goalkeeper"]!!.jsonPrimitive.content
        val goalkeeper = PlayerAPI().findById(goalkeeperId)

        val defenders: MutableList<Player> = mutableListOf()
        for (defender in lineupObject["defenders"]!!.jsonArray) {
            val defenderId = defender.jsonPrimitive.content
            val defender = PlayerAPI().findById(defenderId)
            defenders.add(defender)
        }

        val midfielders: MutableList<Player> = mutableListOf()
        for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
            val midfielderId = midfielder.jsonPrimitive.content
            val midfielder = PlayerAPI().findById(midfielderId)
            midfielders.add(midfielder)
        }

        val attackers: MutableList<Player> = mutableListOf()
        for (attacker in lineupObject["attackers"]!!.jsonArray) {
            val attackerId = attacker.jsonPrimitive.content
            val attacker = PlayerAPI().findById(attackerId)
            attackers.add(attacker)
        }

        val substitutes: MutableList<Player> = mutableListOf()
        for (substitute in lineupObject["substitutes"]!!.jsonArray) {
            val substituteId = substitute.jsonPrimitive.content
            val substitute = PlayerAPI().findById(substituteId)
            substitutes.add(substitute)
        }

        val lineup = Lineup(
            _id,
            type,
            team,
            match,
            goalkeeper,
            defenders,
            midfielders,
            attackers,
            substitutes
        )
        return lineup
    }
}