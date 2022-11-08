package api

import com.example.worldcupapp.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.google.gson.Gson
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

                    val playerIn = Gson().fromJson(substitutionObject["playerIn"]!!.jsonObject.toString(), Player::class.java)

                    val playerOut = Gson().fromJson(substitutionObject["playerOut"]!!.jsonObject.toString(), Player::class.java)

                    val match = Gson().fromJson(substitutionObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val team = Gson().fromJson(substitutionObject["team"]!!.jsonObject.toString(), Team::class.java)

                    val isHomeTeamSubstitution = substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

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

                val playerIn = Gson().fromJson(substitutionObject["playerIn"]!!.jsonObject.toString(), Player::class.java)

                val playerOut = Gson().fromJson(substitutionObject["playerOut"]!!.jsonObject.toString(), Player::class.java)

                val match = Gson().fromJson(substitutionObject["match"]!!.jsonObject.toString(), Match::class.java)

                val team = Gson().fromJson(substitutionObject["team"]!!.jsonObject.toString(), Team::class.java)

                val isHomeTeamSubstitution = substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

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

                    val playerIn = Gson().fromJson(substitutionObject["playerIn"]!!.jsonObject.toString(), Player::class.java)

                    val playerOut = Gson().fromJson(substitutionObject["playerOut"]!!.jsonObject.toString(), Player::class.java)

                    val match = Gson().fromJson(substitutionObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val team = Gson().fromJson(substitutionObject["team"]!!.jsonObject.toString(), Team::class.java)

                    val isHomeTeamSubstitution = substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

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

                    val playerIn = Gson().fromJson(substitutionObject["playerIn"]!!.jsonObject.toString(), Player::class.java)

                    val playerOut = Gson().fromJson(substitutionObject["playerOut"]!!.jsonObject.toString(), Player::class.java)

                    val match = Gson().fromJson(substitutionObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val team = Gson().fromJson(substitutionObject["team"]!!.jsonObject.toString(), Team::class.java)

                    val isHomeTeamSubstitution = substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

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

                    val playerIn = Gson().fromJson(substitutionObject["playerIn"]!!.jsonObject.toString(), Player::class.java)

                    val playerOut = Gson().fromJson(substitutionObject["playerOut"]!!.jsonObject.toString(), Player::class.java)

                    val match = Gson().fromJson(substitutionObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val team = Gson().fromJson(substitutionObject["team"]!!.jsonObject.toString(), Team::class.java)

                    val isHomeTeamSubstitution = substitutionObject["isHomeTeamSubstitution"]!!.jsonPrimitive.boolean

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