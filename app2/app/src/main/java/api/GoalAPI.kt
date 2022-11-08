package api

import com.example.worldcupapp.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import kotlinx.coroutines.async
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import java.text.SimpleDateFormat

class GoalAPI {
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/goal"

    suspend fun find() : MutableList<Goal> {
        val goals: MutableList<Goal> = mutableListOf()
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
                for (goal in data!!.jsonArray) {
                    val goalObject = goal.jsonObject
                    val _id = goalObject["_id"]!!.jsonPrimitive.content

                    val scorer = Gson().fromJson(goalObject["scorer"]!!.jsonObject.toString(), Player::class.java)

                    val assister = Gson().fromJson(goalObject["assister"]!!.jsonObject.toString(), Player::class.java)

                    val minute = goalObject["minute"]!!.jsonPrimitive.int

                    val match = Gson().fromJson(goalObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val isHomeTeamGoal = goalObject["isHomeTeamGoal"]!!.jsonPrimitive.boolean
                    val isOwnGoal = goalObject["isOwnGoal"]!!.jsonPrimitive.boolean

                    val goal = Goal(
                        _id,
                        scorer,
                        assister,
                        minute,
                        match,
                        isHomeTeamGoal,
                        isOwnGoal
                    )
                    goals.add(goal)
                }
            }
        }
        return goals
    }

    suspend fun findById(goalId: String) : Goal? {
        var goal: Goal? = null
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/" + goalId) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                val goalObject = data!!.jsonObject
                val _id = goalObject["_id"]!!.jsonPrimitive.content

                val scorer = Gson().fromJson(goalObject["scorer"]!!.jsonObject.toString(), Player::class.java)

                val assister = Gson().fromJson(goalObject["assister"]!!.jsonObject.toString(), Player::class.java)

                val minute = goalObject["minute"]!!.jsonPrimitive.int

                val match = Gson().fromJson(goalObject["match"]!!.jsonObject.toString(), Match::class.java)

                val isHomeTeamGoal = goalObject["isHomeTeamGoal"]!!.jsonPrimitive.boolean
                val isOwnGoal = goalObject["isOwnGoal"]!!.jsonPrimitive.boolean

                goal = Goal(
                    _id,
                    scorer,
                    assister,
                    minute,
                    match,
                    isHomeTeamGoal,
                    isOwnGoal
                )
            }
        }
        return goal
    }

    suspend fun findByScorer(scorerId: String) : MutableList<Goal> {
        val goals: MutableList<Goal> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/scorer/" + scorerId) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (goal in data!!.jsonArray) {
                    val goalObject = goal.jsonObject
                    val _id = goalObject["_id"]!!.jsonPrimitive.content

                    val scorer = Gson().fromJson(goalObject["scorer"]!!.jsonObject.toString(), Player::class.java)

                    val assister = Gson().fromJson(goalObject["assister"]!!.jsonObject.toString(), Player::class.java)

                    val minute = goalObject["minute"]!!.jsonPrimitive.int

                    val match = Gson().fromJson(goalObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val isHomeTeamGoal = goalObject["isHomeTeamGoal"]!!.jsonPrimitive.boolean
                    val isOwnGoal = goalObject["isOwnGoal"]!!.jsonPrimitive.boolean

                    val goal = Goal(
                        _id,
                        scorer,
                        assister,
                        minute,
                        match,
                        isHomeTeamGoal,
                        isOwnGoal
                    )
                    goals.add(goal)
                }
            }
        }
        return goals
    }

    suspend fun findByAsister(asisterId: String) : MutableList<Goal>{
        val goals: MutableList<Goal> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/assister/" + asisterId) {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (goal in data!!.jsonArray) {
                    val goalObject = goal.jsonObject
                    val _id = goalObject["_id"]!!.jsonPrimitive.content

                    val scorer = Gson().fromJson(goalObject["scorer"]!!.jsonObject.toString(), Player::class.java)

                    val assister = Gson().fromJson(goalObject["assister"]!!.jsonObject.toString(), Player::class.java)

                    val minute = goalObject["minute"]!!.jsonPrimitive.int

                    val match = Gson().fromJson(goalObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val isHomeTeamGoal = goalObject["isHomeTeamGoal"]!!.jsonPrimitive.boolean
                    val isOwnGoal = goalObject["isOwnGoal"]!!.jsonPrimitive.boolean

                    val goal = Goal(
                        _id,
                        scorer,
                        assister,
                        minute,
                        match,
                        isHomeTeamGoal,
                        isOwnGoal
                    )
                    goals.add(goal)
                }
            }
        }
        return goals
    }

    suspend fun findByMatch(matchId: String) : MutableList<Goal>{
        val goals: MutableList<Goal> = mutableListOf()
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
                println(data)
                for (goal in data!!.jsonArray) {
                    val goalObject = goal.jsonObject
                    val _id = goalObject["_id"]!!.jsonPrimitive.content

                    println(goalObject["scorer"]!!.jsonObject)

                    val scorerObject = goalObject["scorer"]!!.jsonObject
                    val scorer = Gson().fromJson(scorerObject.toString(), Player::class.java)

                    val assister = Gson().fromJson(goalObject["assister"]!!.jsonObject.toString(), Player::class.java)

                    val minute = goalObject["minute"]!!.jsonPrimitive.int

                    val match = Gson().fromJson(goalObject["match"]!!.jsonObject.toString(), Match::class.java)

                    val isHomeTeamGoal = goalObject["isHomeTeamGoal"]!!.jsonPrimitive.boolean
                    val isOwnGoal = goalObject["isOwnGoal"]!!.jsonPrimitive.boolean

                    val goal = Goal(
                        _id,
                        scorer,
                        assister,
                        minute,
                        match,
                        isHomeTeamGoal,
                        isOwnGoal
                    )
                    goals.add(goal)
                }
            }
        }
        return goals
    }
}