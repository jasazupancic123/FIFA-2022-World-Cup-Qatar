package api

import com.example.worldcupapp.Match
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Substitution
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.text.SimpleDateFormat

class SubstitutionAPI {
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/substitution"

    suspend fun find() : MutableList<Substitution> {
        val substitutions: MutableList<Substitution> = mutableListOf()
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
                for (substitution in data!!.jsonArray) {
                    val substitutionObject = substitution.jsonObject
                    val _id = substitutionObject["_id"]!!.jsonPrimitive.content
                    val minute = substitutionObject["minute"]!!.jsonPrimitive.int

                    val playerInId = substitutionObject["playerIn"]!!.jsonPrimitive.content
                    val playerIn = PlayerAPI().findById(playerInId) ?: throw Exception("Player in not found")

                    val playerOutId = substitutionObject["playerOut"]!!.jsonPrimitive.content
                    val playerOut = PlayerAPI().findById(playerOutId) ?: throw Exception("Player out not found")

                    val matchId = substitutionObject["match"]!!.jsonPrimitive.content
                    val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                    val teamId = substitutionObject["team"]!!.jsonPrimitive.content
                    val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                    val isHomeTeamSubstitution =
                        substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

                    val substitution = Substitution(
                        _id,
                        minute,
                        playerIn,
                        playerOut,
                        match,
                        team,
                        isHomeTeamSubstitution
                    )
                    substitutions.add(substitution)
                }
            }
        }
        return substitutions
    }

    suspend fun findById(substitutionId: String): Substitution? {
        var substitution : Substitution? = null
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/" + substitutionId) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                val substitutionObject = data!!.jsonObject
                val _id = substitutionObject["_id"]!!.jsonPrimitive.content
                val minute = substitutionObject["minute"]!!.jsonPrimitive.int

                val playerInId = substitutionObject["playerIn"]!!.jsonPrimitive.content
                val playerIn = PlayerAPI().findById(playerInId) ?: throw Exception("Player in not found")

                val playerOutId = substitutionObject["playerOut"]!!.jsonPrimitive.content
                val playerOut = PlayerAPI().findById(playerOutId) ?: throw Exception("Player out not found")

                val matchId = substitutionObject["match"]!!.jsonPrimitive.content
                val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                val teamId = substitutionObject["team"]!!.jsonPrimitive.content
                val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                val isHomeTeamSubstitution =
                    substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

                substitution = Substitution(
                    _id,
                    minute,
                    playerIn,
                    playerOut,
                    match,
                    team,
                    isHomeTeamSubstitution
                )
            }
        }
        return substitution
    }

    suspend fun findByMatch(matchId: String): MutableList<Substitution> {
        val substitutions: MutableList<Substitution> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/match/" + matchId) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (substitution in data!!.jsonArray) {
                    val substitutionObject = substitution.jsonObject
                    val _id = substitutionObject["_id"]!!.jsonPrimitive.content
                    val minute = substitutionObject["minute"]!!.jsonPrimitive.int

                    val playerInId = substitutionObject["playerIn"]!!.jsonPrimitive.content
                    val playerIn = PlayerAPI().findById(playerInId) ?: throw Exception("Player in not found")

                    val playerOutId = substitutionObject["playerOut"]!!.jsonPrimitive.content
                    val playerOut = PlayerAPI().findById(playerOutId) ?: throw Exception("Player out not found")

                    val matchId = substitutionObject["match"]!!.jsonPrimitive.content
                    val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                    val teamId = substitutionObject["team"]!!.jsonPrimitive.content
                    val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                    val isHomeTeamSubstitution =
                        substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

                    val substitution = Substitution(
                        _id,
                        minute,
                        playerIn,
                        playerOut,
                        match,
                        team,
                        isHomeTeamSubstitution
                    )
                    substitutions.add(substitution)
                }
            }
        }
        return substitutions
    }

    suspend fun findByTeam(teamId: String) : MutableList<Substitution>{
        val substitutions: MutableList<Substitution> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/team/" + teamId) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (substitution in data!!.jsonArray) {
                    val substitutionObject = substitution.jsonObject
                    val _id = substitutionObject["_id"]!!.jsonPrimitive.content
                    val minute = substitutionObject["minute"]!!.jsonPrimitive.int

                    val playerInId = substitutionObject["playerIn"]!!.jsonPrimitive.content
                    val playerIn = PlayerAPI().findById(playerInId) ?: throw Exception("Player in not found")

                    val playerOutId = substitutionObject["playerOut"]!!.jsonPrimitive.content
                    val playerOut = PlayerAPI().findById(playerOutId) ?: throw Exception("Player out not found")

                    val matchId = substitutionObject["match"]!!.jsonPrimitive.content
                    val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                    val teamId = substitutionObject["team"]!!.jsonPrimitive.content
                    val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                    val isHomeTeamSubstitution =
                        substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

                    val substitution = Substitution(
                        _id,
                        minute,
                        playerIn,
                        playerOut,
                        match,
                        team,
                        isHomeTeamSubstitution
                    )
                    substitutions.add(substitution)
                }
            }
        }
        return substitutions
    }

    suspend fun findByMatchAndTeam(matchId: String, teamId: String) : MutableList<Substitution> {
        val substitutions: MutableList<Substitution> = mutableListOf()
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
                for (substitution in data!!.jsonArray) {
                    val substitutionObject = substitution.jsonObject
                    val _id = substitutionObject["_id"]!!.jsonPrimitive.content
                    val minute = substitutionObject["minute"]!!.jsonPrimitive.int

                    val playerInId = substitutionObject["playerIn"]!!.jsonPrimitive.content
                    val playerIn = PlayerAPI().findById(playerInId) ?: throw Exception("Player in not found")

                    val playerOutId = substitutionObject["playerOut"]!!.jsonPrimitive.content
                    val playerOut = PlayerAPI().findById(playerOutId) ?: throw Exception("Player out not found")

                    val matchId = substitutionObject["match"]!!.jsonPrimitive.content
                    val match = MatchAPI().findById(matchId) ?: throw Exception("Match not found")

                    val teamId = substitutionObject["team"]!!.jsonPrimitive.content
                    val team = TeamAPI().findById(teamId) ?: throw Exception("Team not found")

                    val isHomeTeamSubstitution =
                        substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

                    val substitution = Substitution(
                        _id,
                        minute,
                        playerIn,
                        playerOut,
                        match,
                        team!!,
                        isHomeTeamSubstitution
                    )
                    substitutions.add(substitution)
                }
            }
        }
        return substitutions
    }
}