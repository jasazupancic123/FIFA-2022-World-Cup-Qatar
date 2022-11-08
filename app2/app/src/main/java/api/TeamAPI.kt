package api

import com.example.worldcupapp.Manager
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*
import com.example.worldcupapp.Team
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.*
import java.io.IOException

class TeamAPI(){
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/team"

    suspend fun find() : MutableList<Team> {
        val teams: MutableList<Team> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url) {
                    method = HttpMethod.Get
                }

                if(response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (team in data!!.jsonArray) {
                    val teamObject = team.jsonObject
                    val _id = teamObject["_id"]!!.jsonPrimitive.content
                    val name = teamObject["name"]!!.jsonPrimitive.content
                    val region = teamObject["region"]!!.jsonPrimitive.content
                    val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
                    val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

                    val manager = Gson().fromJson(teamObject["manager"]!!.jsonObject.toString(), Manager::class.java)

                    val flag = teamObject["flag"]!!.jsonPrimitive.content
                    val group = teamObject["group"]!!.jsonPrimitive.content
                    val noOfTitles = teamObject["noOfTitles"]!!.jsonPrimitive.int
                    val isEliminated = teamObject["isEliminated"]!!.jsonPrimitive.boolean
                    val points = teamObject["points"]!!.jsonPrimitive.int
                    val goalsScored = teamObject["goalsScored"]!!.jsonPrimitive.int
                    val matchesPlayed = teamObject["matchesPlayed"]!!.jsonPrimitive.int
                    val matchesWon = teamObject["matchesWon"]!!.jsonPrimitive.int
                    val matchesDrawn = teamObject["matchesDrawn"]!!.jsonPrimitive.int
                    val matchesLost = teamObject["matchesLost"]!!.jsonPrimitive.int
                    val goalsAgainst = teamObject["goalsAgainst"]!!.jsonPrimitive.int
                    teams.add(
                        Team(
                            _id,
                            name,
                            region,
                            fifaCode,
                            iso2,
                            manager!!,
                            flag,
                            group,
                            noOfTitles,
                            isEliminated,
                            points,
                            goalsScored,
                            matchesPlayed,
                            matchesWon,
                            matchesDrawn,
                            matchesLost,
                            goalsAgainst
                        )
                    )
                }
            }
        }
        return teams
    }

    suspend fun findById(_id: String) : Team?{
        var team: Team? = null
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]

                val teamObject = data!!.jsonObject
                val _id = teamObject["_id"]!!.jsonPrimitive.content
                val name = teamObject["name"]!!.jsonPrimitive.content
                val region = teamObject["region"]!!.jsonPrimitive.content
                val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
                val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

                val manager = Gson().fromJson(teamObject["manager"]!!.jsonObject.toString(), Manager::class.java)

                val flag = teamObject["flag"]!!.jsonPrimitive.content
                val group = teamObject["group"]!!.jsonPrimitive.content
                val noOfTitles = teamObject["noOfTitles"]!!.jsonPrimitive.int
                val isEliminated = teamObject["isEliminated"]!!.jsonPrimitive.boolean
                val points = teamObject["points"]!!.jsonPrimitive.int
                val goalsScored = teamObject["goalsScored"]!!.jsonPrimitive.int
                val matchesPlayed = teamObject["matchesPlayed"]!!.jsonPrimitive.int
                val matchesWon = teamObject["matchesWon"]!!.jsonPrimitive.int
                val matchesDrawn = teamObject["matchesDrawn"]!!.jsonPrimitive.int
                val matchesLost = teamObject["matchesLost"]!!.jsonPrimitive.int
                val goalsAgainst = teamObject["goalsAgainst"]!!.jsonPrimitive.int
                team = Team(
                    _id,
                    name,
                    region,
                    fifaCode,
                    iso2,
                    manager!!,
                    flag,
                    group,
                    noOfTitles,
                    isEliminated,
                    points,
                    goalsScored,
                    matchesPlayed,
                    matchesWon,
                    matchesDrawn,
                    matchesLost,
                    goalsAgainst
                )
            }
        }
        return team
    }

    suspend fun findByGroup(groupName: String): MutableList<Team> {
        val teams: MutableList<Team> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/group/" + groupName) {
                    method = HttpMethod.Get
                }

                if(response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (team in data!!.jsonArray) {
                    val teamObject = team.jsonObject
                    val _id = teamObject["_id"]!!.jsonPrimitive.content
                    val name = teamObject["name"]!!.jsonPrimitive.content
                    val region = teamObject["region"]!!.jsonPrimitive.content
                    val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
                    val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

                    val manager = Gson().fromJson(teamObject["manager"]!!.jsonObject.toString(), Manager::class.java)

                    val flag = teamObject["flag"]!!.jsonPrimitive.content
                    val group = teamObject["group"]!!.jsonPrimitive.content
                    val noOfTitles = teamObject["noOfTitles"]!!.jsonPrimitive.int
                    val isEliminated = teamObject["isEliminated"]!!.jsonPrimitive.boolean
                    val points = teamObject["points"]!!.jsonPrimitive.int
                    val goalsScored = teamObject["goalsScored"]!!.jsonPrimitive.int
                    val matchesPlayed = teamObject["matchesPlayed"]!!.jsonPrimitive.int
                    val matchesWon = teamObject["matchesWon"]!!.jsonPrimitive.int
                    val matchesDrawn = teamObject["matchesDrawn"]!!.jsonPrimitive.int
                    val matchesLost = teamObject["matchesLost"]!!.jsonPrimitive.int
                    val goalsAgainst = teamObject["goalsAgainst"]!!.jsonPrimitive.int
                    teams.add(
                        Team(
                            _id,
                            name,
                            region,
                            fifaCode,
                            iso2,
                            manager!!,
                            flag,
                            group,
                            noOfTitles,
                            isEliminated,
                            points,
                            goalsScored,
                            matchesPlayed,
                            matchesWon,
                            matchesDrawn,
                            matchesLost,
                            goalsAgainst
                        )
                    )
                }
            }
        }
        return teams
    }

    suspend fun findByAtLeastOneTitle() : MutableList<Team> {
        val teams: MutableList<Team> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/findBy/atLeastOneTitle") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (team in data!!.jsonArray) {
                    val teamObject = team.jsonObject
                    val _id = teamObject["_id"]!!.jsonPrimitive.content
                    val name = teamObject["name"]!!.jsonPrimitive.content
                    val region = teamObject["region"]!!.jsonPrimitive.content
                    val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
                    val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

                    val manager = Gson().fromJson(teamObject["manager"]!!.jsonObject.toString(), Manager::class.java)

                    val flag = teamObject["flag"]!!.jsonPrimitive.content
                    val group = teamObject["group"]!!.jsonPrimitive.content
                    val noOfTitles = teamObject["noOfTitles"]!!.jsonPrimitive.int
                    val isEliminated = teamObject["isEliminated"]!!.jsonPrimitive.boolean
                    val points = teamObject["points"]!!.jsonPrimitive.int
                    val goalsScored = teamObject["goalsScored"]!!.jsonPrimitive.int
                    val matchesPlayed = teamObject["matchesPlayed"]!!.jsonPrimitive.int
                    val matchesWon = teamObject["matchesWon"]!!.jsonPrimitive.int
                    val matchesDrawn = teamObject["matchesDrawn"]!!.jsonPrimitive.int
                    val matchesLost = teamObject["matchesLost"]!!.jsonPrimitive.int
                    val goalsAgainst = teamObject["goalsAgainst"]!!.jsonPrimitive.int
                    teams.add(
                        Team(
                            _id,
                            name,
                            region,
                            fifaCode,
                            iso2,
                            manager!!,
                            flag,
                            group,
                            noOfTitles,
                            isEliminated,
                            points,
                            goalsScored,
                            matchesPlayed,
                            matchesWon,
                            matchesDrawn,
                            matchesLost,
                            goalsAgainst
                        )
                    )
                }
            }
        }
        return teams
    }
}