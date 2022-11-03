package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Goal
import java.text.SimpleDateFormat

class GoalAPI {
    val url = "http://localhost:3000/goal"

    suspend fun find() : MutableList<Goal> {
        val client = HttpClient()
        val response: HttpResponse = client.get(url) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val goals: MutableList<Goal> = mutableListOf()
        for (goal in data!!.jsonArray) {
            val goalObject = goal.jsonObject
            val _id = goalObject["_id"]!!.jsonPrimitive.content

            val scorerId = goalObject["scorer"]!!.jsonPrimitive.content
            val scorer = PlayerAPI().findById(scorerId)

            val assisterId = goalObject["assister"]!!.jsonPrimitive.content
            val assister = PlayerAPI().findById(assisterId)

            val minute = goalObject["minute"]!!.jsonPrimitive.int

            val matchId = goalObject["match"]!!.jsonPrimitive.content
            val match = MatchAPI().findById(matchId)

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
        return goals
    }

    suspend fun findById(goalId: String) : Goal{
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/" + goalId) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val goalObject = data!!.jsonObject
        val _id = goalObject["_id"]!!.jsonPrimitive.content

        val scorerId = goalObject["scorer"]!!.jsonPrimitive.content
        val scorer = PlayerAPI().findById(scorerId)

        val assisterId = goalObject["assister"]!!.jsonPrimitive.content
        val assister = PlayerAPI().findById(assisterId)

        val minute = goalObject["minute"]!!.jsonPrimitive.int

        val matchId = goalObject["match"]!!.jsonPrimitive.content
        val match = MatchAPI().findById(matchId)

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
        return goal
    }

    suspend fun findByScorer(scorerId: String) : MutableList<Goal> {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/scorer/" + scorerId) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val goals: MutableList<Goal> = mutableListOf()
        for (goal in data!!.jsonArray) {
            val goalObject = goal.jsonObject
            val _id = goalObject["_id"]!!.jsonPrimitive.content

            val scorerId = goalObject["scorer"]!!.jsonPrimitive.content
            val scorer = PlayerAPI().findById(scorerId)

            val assisterId = goalObject["assister"]!!.jsonPrimitive.content
            val assister = PlayerAPI().findById(assisterId)

            val minute = goalObject["minute"]!!.jsonPrimitive.int

            val matchId = goalObject["match"]!!.jsonPrimitive.content
            val match = MatchAPI().findById(matchId)

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
        return goals
    }

    suspend fun findByAsister(asisterId: String) : MutableList<Goal>{
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/assister/" + asisterId) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val goals: MutableList<Goal> = mutableListOf()
        for (goal in data!!.jsonArray) {
            val goalObject = goal.jsonObject
            val _id = goalObject["_id"]!!.jsonPrimitive.content

            val scorerId = goalObject["scorer"]!!.jsonPrimitive.content
            val scorer = PlayerAPI().findById(scorerId)

            val assisterId = goalObject["assister"]!!.jsonPrimitive.content
            val assister = PlayerAPI().findById(assisterId)

            val minute = goalObject["minute"]!!.jsonPrimitive.int

            val matchId = goalObject["match"]!!.jsonPrimitive.content
            val match = MatchAPI().findById(matchId)

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
        return goals
    }

    suspend fun findByMatch(matchId: String) : MutableList<Goal>{
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/match/" + matchId) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val goals: MutableList<Goal> = mutableListOf()
        for (goal in data!!.jsonArray) {
            val goalObject = goal.jsonObject
            val _id = goalObject["_id"]!!.jsonPrimitive.content

            val scorerId = goalObject["scorer"]!!.jsonPrimitive.content
            val scorer = PlayerAPI().findById(scorerId)

            val assisterId = goalObject["assister"]!!.jsonPrimitive.content
            val assister = PlayerAPI().findById(assisterId)

            val minute = goalObject["minute"]!!.jsonPrimitive.int

            val matchId = goalObject["match"]!!.jsonPrimitive.content
            val match = MatchAPI().findById(matchId)

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
        return goals
    }
}