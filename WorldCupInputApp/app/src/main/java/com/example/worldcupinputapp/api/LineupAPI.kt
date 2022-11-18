package api

import android.content.Context
import android.widget.Toast
import com.example.worldcupapp.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.google.gson.Gson
import io.ktor.client.plugins.*
import io.ktor.util.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

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

                    val team = Gson().fromJson(lineupObject["team"]!!.jsonObject.toString(), Team::class.java)

                    val match = Gson().fromJson(lineupObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val goalkeeper = Gson().fromJson(lineupObject["goalkeeper"]!!.jsonObject.toString(), Player::class.java)

                    val defenders: MutableList<Player> = mutableListOf()
                    for (defender in lineupObject["defenders"]!!.jsonArray) {
                        val defender = Gson().fromJson(defender.jsonObject.toString(), Player::class.java)
                        defenders.add(defender)
                    }

                    val midfielders: MutableList<Player> = mutableListOf()
                    for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
                        val midfielder = Gson().fromJson(midfielder.jsonObject.toString(), Player::class.java)
                        midfielders.add(midfielder)
                    }

                    val attackers: MutableList<Player> = mutableListOf()
                    for (attacker in lineupObject["attackers"]!!.jsonArray) {
                        val attacker = Gson().fromJson(attacker.jsonObject.toString(), Player::class.java)
                        attackers.add(attacker)
                    }

                    val substitutes: MutableList<Player> = mutableListOf()
                    for (substitute in lineupObject["substitutes"]!!.jsonArray) {
                        val substitute = Gson().fromJson(substitute.jsonObject.toString(), Player::class.java)
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

                val team = Gson().fromJson(lineupObject["team"]!!.jsonObject.toString(), Team::class.java)

                val match = Gson().fromJson(lineupObject["match"]!!.jsonObject.toString(), Match::class.java)

                val goalkeeper = Gson().fromJson(lineupObject["goalkeeper"]!!.jsonObject.toString(), Player::class.java)

                val defenders: MutableList<Player> = mutableListOf()
                for (defender in lineupObject["defenders"]!!.jsonArray) {
                    val defender = Gson().fromJson(defender.jsonObject.toString(), Player::class.java)
                    defenders.add(defender)
                }

                val midfielders: MutableList<Player> = mutableListOf()
                for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
                    val midfielder = Gson().fromJson(midfielder.jsonObject.toString(), Player::class.java)
                    midfielders.add(midfielder)
                }

                val attackers: MutableList<Player> = mutableListOf()
                for (attacker in lineupObject["attackers"]!!.jsonArray) {
                    val attacker = Gson().fromJson(attacker.jsonObject.toString(), Player::class.java)
                    attackers.add(attacker)
                }

                val substitutes: MutableList<Player> = mutableListOf()
                for (substitute in lineupObject["substitutes"]!!.jsonArray) {
                    val substitute = Gson().fromJson(substitute.jsonObject.toString(), Player::class.java)
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
                println("data: $data")
                val lineupObject = data!!.jsonObject
                val _id = lineupObject["_id"]!!.jsonPrimitive.content
                val type = lineupObject["type"]!!.jsonPrimitive.content

                val team = Gson().fromJson(lineupObject["team"]!!.jsonObject.toString(), Team::class.java)

                //println(lineupObject["match"])
                //println(lineupObject["goalkeeper"]!!.jsonObject.toString())

                val matchObject = lineupObject["match"]!!.jsonObject
                println(matchObject.toString())

                //val match = Gson().fromJson(matchObject.jsonObject.toString(), Match::class.java)
                val match = Gson().fromJson(lineupObject["match"]!!.jsonObject.toString(), Match::class.java)

                println(lineupObject["goalkeeper"]!!)
                val goalkeeper = Gson().fromJson(lineupObject["goalkeeper"]!!.toString(), Player::class.java)

                val defenders: MutableList<Player> = mutableListOf()
                for (defender in lineupObject["defenders"]!!.jsonArray) {
                    val defender = Gson().fromJson(defender.jsonObject.toString(), Player::class.java)
                    defenders.add(defender)
                }

                val midfielders: MutableList<Player> = mutableListOf()
                for (midfielder in lineupObject["midfielders"]!!.jsonArray) {
                    val midfielder = Gson().fromJson(midfielder.jsonObject.toString(), Player::class.java)
                    midfielders.add(midfielder)
                }

                val attackers: MutableList<Player> = mutableListOf()
                for (attacker in lineupObject["attackers"]!!.jsonArray) {
                    val attacker = Gson().fromJson(attacker.jsonObject.toString(), Player::class.java)
                    attackers.add(attacker)
                }

                val substitutes: MutableList<Player> = mutableListOf()
                for (substitute in lineupObject["substitutes"]!!.jsonArray) {
                    val substitute = Gson().fromJson(substitute.jsonObject.toString(), Player::class.java)
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

    inner class LineupForApi(
        val type: String,
        val team: String,
        val match: String,
        val goalkeeper: String,
        val defenders: List<String>,
        val midfielders: List<String>,
        val attackers: List<String>,
        val substitutes: List<String>
    )

    @OptIn(InternalAPI::class)
    suspend fun postAndUpdateEverything(
        lineupType: String,
        teamId: String,
        matchId: String,
        goalkeeper: Player,
        defenders: java.util.ArrayList<Player?>,
        midfielders: java.util.ArrayList<Player?>,
        forwards: java.util.ArrayList<Player?>,
        substitutes: ArrayList<Player>,
        context: Context
    ){
        coroutineScope {
            val coroutine = async {
                //make post request
                val client = HttpClient()
                println(Gson().toJson(
                    LineupForApi(
                        lineupType,
                        teamId,
                        matchId,
                        goalkeeper._id,
                        defenders.map { it!!._id },
                        midfielders.map { it!!._id },
                        forwards.map { it!!._id },
                        substitutes.map { it._id }
                    )
                ))
                try {
                    val response: HttpResponse = client.post(url + "/updateEverything") {
                        contentType(ContentType.Application.Json)
                        //method = HttpMethod.Post
                        body = Gson().toJson(
                            LineupForApi(
                                lineupType,
                                teamId,
                                matchId,
                                goalkeeper._id,
                                defenders.map { it!!._id },
                                midfielders.map { it!!._id },
                                forwards.map { it!!._id },
                                substitutes.map { it._id }
                            )
                        )
                    }
                    println(response.body() as String)
                } catch (e: io.ktor.client.plugins.HttpRequestTimeoutException) {
                    Toast.makeText(context, "Successfully Added Lineups", Toast.LENGTH_SHORT).show()
                } catch (e: java.lang.Exception){
                    Toast.makeText(context, "Error Adding Lineups", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
