package api

import com.example.worldcupapp.Goal
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Lineup
import com.example.worldcupapp.Player
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.text.SimpleDateFormat

class LineupAPI {
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/lineup"

    suspend fun find(): MutableList<Lineup> {
        val lineups: MutableList<Lineup> = mutableListOf()
        coroutineScope {
            val coroutine = async {
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
                for (lineup in data!!.jsonArray) {
                    val lineupObject = lineup.jsonObject
                    val _id = lineupObject["_id"]!!.jsonPrimitive.content
                    val type = lineupObject["type"]!!.jsonPrimitive.content

                    val teamId = lineupObject["team"]!!.jsonPrimitive.content
                    val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                    val matchId = lineupObject["match"]!!.jsonPrimitive.content
                    val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                    val goalkeeperId = lineupObject["goalkeeper"]!!.jsonPrimitive.content
                    val goalkeeper = PlayerAPI().findById(goalkeeperId) ?: throw Exception("Goalkeeper not found")

                    val defenders: MutableList<Player> = mutableListOf()
                    for (defender in lineupObject["defenders"]!!.jsonArray) {
                        val defenderId = defender.jsonPrimitive.content
                        val defender = PlayerAPI().findById(defenderId) ?: throw Exception("Defender not found")
                        defenders.add(defender)
                    }

                    val midfielders: MutableList<Player> = mutableListOf()
                    for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
                        val midfielderId = midfielder.jsonPrimitive.content
                        val midfielder = PlayerAPI().findById(midfielderId) ?: throw Exception("Midfielder not found")
                        midfielders.add(midfielder)
                    }

                    val attackers: MutableList<Player> = mutableListOf()
                    for (attacker in lineupObject["attackers"]!!.jsonArray) {
                        val attackerId = attacker.jsonPrimitive.content
                        val attacker = PlayerAPI().findById(attackerId) ?: throw Exception("Attacker not found")
                        attackers.add(attacker)
                    }

                    val substitutes: MutableList<Player> = mutableListOf()
                    for (substitute in lineupObject["substitutes"]!!.jsonArray) {
                        val substituteId = substitute.jsonPrimitive.content
                        val substitute = PlayerAPI().findById(substituteId) ?: throw Exception("Substitute not found")
                        substitutes.add(substitute)
                    }

                    val lineup = Lineup(
                        _id,
                        type,
                        team!!,
                        match,
                        goalkeeper,
                        defenders,
                        midfielders,
                        attackers,
                        substitutes
                    )
                    lineups.add(lineup)
                }
            }
        }
        return lineups
    }

    suspend fun findById(lineupId: String) : Lineup? {
        var lineup: Lineup? = null
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/" + lineupId) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                val lineupObject = data!!.jsonObject
                val _id = lineupObject["_id"]!!.jsonPrimitive.content
                val type = lineupObject["type"]!!.jsonPrimitive.content

                val teamId = lineupObject["team"]!!.jsonPrimitive.content
                val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                val matchId = lineupObject["match"]!!.jsonPrimitive.content
                val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                val goalkeeperId = lineupObject["goalkeeper"]!!.jsonPrimitive.content
                val goalkeeper = PlayerAPI().findById(goalkeeperId) ?: throw Exception("Goalkeeper not found")

                val defenders: MutableList<Player> = mutableListOf()
                for (defender in lineupObject["defenders"]!!.jsonArray) {
                    val defenderId = defender.jsonPrimitive.content
                    val defender = PlayerAPI().findById(defenderId) ?: throw Exception("Defender not found")
                    defenders.add(defender)
                }

                val midfielders: MutableList<Player> = mutableListOf()
                for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
                    val midfielderId = midfielder.jsonPrimitive.content
                    val midfielder = PlayerAPI().findById(midfielderId) ?: throw Exception("Midfielder not found")
                    midfielders.add(midfielder)
                }

                val attackers: MutableList<Player> = mutableListOf()
                for (attacker in lineupObject["attackers"]!!.jsonArray) {
                    val attackerId = attacker.jsonPrimitive.content
                    val attacker = PlayerAPI().findById(attackerId) ?: throw Exception("Attacker not found")
                    attackers.add(attacker)
                }

                val substitutes: MutableList<Player> = mutableListOf()
                for (substitute in lineupObject["substitutes"]!!.jsonArray) {
                    val substituteId = substitute.jsonPrimitive.content
                    val substitute = PlayerAPI().findById(substituteId) ?: throw Exception("Substitute not found")
                    substitutes.add(substitute)
                }

                lineup = Lineup(
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
            }
        }
        return lineup
    }

    suspend fun findByMatchAndTeam(matchId: String, teamId: String) : Lineup? {
        var lineup: Lineup? = null
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse =
                    client.get(url + "/match/" + matchId + "/team/" + teamId) {
                        method = HttpMethod.Get
                    }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                val lineupObject = data!!.jsonObject
                val _id = lineupObject["_id"]!!.jsonPrimitive.content
                val type = lineupObject["type"]!!.jsonPrimitive.content

                val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                val goalkeeperId = lineupObject["goalkeeper"]!!.jsonPrimitive.content
                val goalkeeper = PlayerAPI().findById(goalkeeperId) ?: throw Exception("Goalkeeper not found")

                val defenders: MutableList<Player> = mutableListOf()
                for (defender in lineupObject["defenders"]!!.jsonArray) {
                    val defenderId = defender.jsonPrimitive.content
                    val defender = PlayerAPI().findById(defenderId) ?: throw Exception("Defender not found")
                    defenders.add(defender)
                }

                val midfielders: MutableList<Player> = mutableListOf()
                for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
                    val midfielderId = midfielder.jsonPrimitive.content
                    val midfielder = PlayerAPI().findById(midfielderId) ?: throw Exception("Midfielder not found")
                    midfielders.add(midfielder)
                }

                val attackers: MutableList<Player> = mutableListOf()
                for (attacker in lineupObject["attackers"]!!.jsonArray) {
                    val attackerId = attacker.jsonPrimitive.content
                    val attacker = PlayerAPI().findById(attackerId) ?: throw Exception("Attacker not found")
                    attackers.add(attacker)
                }

                val substitutes: MutableList<Player> = mutableListOf()
                for (substitute in lineupObject["substitutes"]!!.jsonArray) {
                    val substituteId = substitute.jsonPrimitive.content
                    val substitute = PlayerAPI().findById(substituteId) ?: throw Exception("Substitute not found")
                    substitutes.add(substitute)
                }

                lineup = Lineup(
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
            }
        }
        return lineup
    }
}