package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Match
import com.example.worldcupapp.Team
import java.text.SimpleDateFormat

class MatchAPI {
    val url = "http://localhost:3000/match"

    suspend fun find(): MutableList<Match>{
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
        val matches: MutableList<Match> = mutableListOf()
        for (match in data!!.jsonArray) {
            val matchObject = match.jsonObject
            val _id = matchObject["_id"]!!.jsonPrimitive.content

            val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
            val homeTeam = TeamAPI().findById(homeTeamId)

            val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
            val awayTeam = TeamAPI().findById(awayTeamId)

            val date = matchObject["date"]!!.jsonPrimitive.content
            val stadium = matchObject["stadium"]!!.jsonPrimitive.content
            val referee = matchObject["referee"]!!.jsonPrimitive.content
            val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content
            val homeTeamScore = matchObject["homeTeamScore"]!!.jsonPrimitive.int
            val awayTeamScore = matchObject["awayTeamScore"]!!.jsonPrimitive.int

            val homeTeamGoals = matchObject["homeTeamGoals"]!!.jsonArray
            val awayTeamGoals = matchObject["awayTeamGoals"]!!.jsonArray

            //goal = GoalAPI.findById(homeTeamGoals[i])
            //goals.add(goal)

            val homeTeamSubstitutions = matchObject["homeTeamSubstitutions"]!!.jsonArray
            val awayTeamSubstitutions = matchObject["awayTeamSubstitutions"]!!.jsonArray
            //substitution = SubstitutionAPI().findById(homeTeamSubstitutions[i])
            //substitutions.add(substitution)

            val homeTeamLineup = matchObject["homeTeamLineup"]?.jsonObject
            val awayTeamLineup = matchObject["awayTeamLineup"]?.jsonObject
            //lineup = LineupAPI().findById(homeTeamLineup)

            val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
            val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

            val winner = matchObject["winner"]!!.jsonPrimitive.content
            //winner = TeamAPI().findById(winner)


            val match = Match(
                _id,
                homeTeam,
                awayTeam,
                SimpleDateFormat("yyyy-MM-dd").parse(date),
                stadium,
                referee,
                roundOrGroup)
            matches.add(match)
        }
        return matches
    }

    suspend fun findByTeam(teamId: String): MutableList<Match>{
        val client = HttpClient()
        val response: HttpResponse = client.get("$url/team/$teamId") {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val matches: MutableList<Match> = mutableListOf()
        for (match in data!!.jsonArray) {
            val matchObject = match.jsonObject
            val _id = matchObject["_id"]!!.jsonPrimitive.content

            val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
            val homeTeam = TeamAPI().findById(homeTeamId)

            val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
            val awayTeam = TeamAPI().findById(awayTeamId)

            val date = matchObject["date"]!!.jsonPrimitive.content
            val stadium = matchObject["stadium"]!!.jsonPrimitive.content
            val referee = matchObject["referee"]!!.jsonPrimitive.content
            val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content
            val homeTeamScore = matchObject["homeTeamScore"]!!.jsonPrimitive.int
            val awayTeamScore = matchObject["awayTeamScore"]!!.jsonPrimitive.int

            val homeTeamGoals = matchObject["homeTeamGoals"]!!.jsonArray
            val awayTeamGoals = matchObject["awayTeamGoals"]!!.jsonArray

            //goal = GoalAPI.findById(homeTeamGoals[i])
            //goals.add(goal)

            val homeTeamSubstitutions = matchObject["homeTeamSubstitutions"]!!.jsonArray
            val awayTeamSubstitutions = matchObject["awayTeamSubstitutions"]!!.jsonArray
            //substitution = SubstitutionAPI().findById(homeTeamSubstitutions[i])
            //substitutions.add(substitution)

            val homeTeamLineup = matchObject["homeTeamLineup"]?.jsonObject
            val awayTeamLineup = matchObject["awayTeamLineup"]?.jsonObject
            //lineup = LineupAPI().findById(homeTeamLineup)

            val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
            val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

            val winner = matchObject["winner"]!!.jsonPrimitive.content
            //winner = TeamAPI().findById(winner)

            val match = Match(
                _id,
                homeTeam,
                awayTeam,
                SimpleDateFormat("yyyy-MM-dd").parse(date),
                stadium,
                referee,
                roundOrGroup
            )
            println("round or group: $roundOrGroup")
            matches.add(match)
        }
        return matches
    }

    suspend fun findByRound(round: String): MutableList<Match>{
        val client = HttpClient()
        val response: HttpResponse = client.get("$url/round/GroupA") { //ne sme biti presledek
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        println(response.body() as String)

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val matches: MutableList<Match> = mutableListOf()
        for (match in data!!.jsonArray) {
            val matchObject = match.jsonObject
            val _id = matchObject["_id"]!!.jsonPrimitive.content

            val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
            val homeTeam = TeamAPI().findById(homeTeamId)

            val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
            val awayTeam = TeamAPI().findById(awayTeamId)

            val date = matchObject["date"]!!.jsonPrimitive.content
            val stadium = matchObject["stadium"]!!.jsonPrimitive.content
            val referee = matchObject["referee"]!!.jsonPrimitive.content
            val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content
            val homeTeamScore = matchObject["homeTeamScore"]!!.jsonPrimitive.int
            val awayTeamScore = matchObject["awayTeamScore"]!!.jsonPrimitive.int

            val homeTeamGoals = matchObject["homeTeamGoals"]!!.jsonArray
            val awayTeamGoals = matchObject["awayTeamGoals"]!!.jsonArray

            //goal = GoalAPI.findById(homeTeamGoals[i])
            //goals.add(goal)

            val homeTeamSubstitutions = matchObject["homeTeamSubstitutions"]!!.jsonArray
            val awayTeamSubstitutions = matchObject["awayTeamSubstitutions"]!!.jsonArray
            //substitution = SubstitutionAPI().findById(homeTeamSubstitutions[i])
            //substitutions.add(substitution)

            val homeTeamLineup = matchObject["homeTeamLineup"]?.jsonObject
            val awayTeamLineup = matchObject["awayTeamLineup"]?.jsonObject
            //lineup = LineupAPI().findById(homeTeamLineup)

            val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
            val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

            val winner = matchObject["winner"]!!.jsonPrimitive.content
            //winner = TeamAPI().findById(winner)

            val match = Match(
                _id,
                homeTeam,
                awayTeam,
                SimpleDateFormat("yyyy-MM-dd").parse(date),
                stadium,
                referee,
                roundOrGroup
            )
            matches.add(match)
        }
        return matches
    }

    suspend fun findFinished() : MutableList<Match>{
        val client = HttpClient()
        val response: HttpResponse = client.get("$url/status/finished") {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val matches: MutableList<Match> = mutableListOf()
        for (match in data!!.jsonArray) {
            val matchObject = match.jsonObject
            val _id = matchObject["_id"]!!.jsonPrimitive.content

            val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
            val homeTeam = TeamAPI().findById(homeTeamId)

            val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
            val awayTeam = TeamAPI().findById(awayTeamId)

            val date = matchObject["date"]!!.jsonPrimitive.content
            val stadium = matchObject["stadium"]!!.jsonPrimitive.content
            val referee = matchObject["referee"]!!.jsonPrimitive.content
            val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content
            val homeTeamScore = matchObject["homeTeamScore"]!!.jsonPrimitive.int
            val awayTeamScore = matchObject["awayTeamScore"]!!.jsonPrimitive.int

            val homeTeamGoals = matchObject["homeTeamGoals"]!!.jsonArray
            val awayTeamGoals = matchObject["awayTeamGoals"]!!.jsonArray

            //goal = GoalAPI.findById(homeTeamGoals[i])
            //goals.add(goal)

            val homeTeamSubstitutions = matchObject["homeTeamSubstitutions"]!!.jsonArray
            val awayTeamSubstitutions = matchObject["awayTeamSubstitutions"]!!.jsonArray
            //substitution = SubstitutionAPI().findById(homeTeamSubstitutions[i])
            //substitutions.add(substitution)

            val homeTeamLineup = matchObject["homeTeamLineup"]?.jsonObject
            val awayTeamLineup = matchObject["awayTeamLineup"]?.jsonObject
            //lineup = LineupAPI().findById(homeTeamLineup)

            val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
            val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

            val winner = matchObject["winner"]!!.jsonPrimitive.content
            //winner = TeamAPI().findById(winner)

            val match = Match(
                _id,
                homeTeam,
                awayTeam,
                SimpleDateFormat("yyyy-MM-dd").parse(date),
                stadium,
                referee,
                roundOrGroup
            )
            matches.add(match)
        }
        return matches
    }

    suspend fun findUnfinished(): MutableList<Match>{
        val client = HttpClient()
        val response: HttpResponse = client.get("$url/status/unfinished") {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val matches: MutableList<Match> = mutableListOf()
        for (match in data!!.jsonArray) {
            val matchObject = match.jsonObject
            val _id = matchObject["_id"]!!.jsonPrimitive.content

            val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
            val homeTeam = TeamAPI().findById(homeTeamId)

            val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
            val awayTeam = TeamAPI().findById(awayTeamId)

            val date = matchObject["date"]!!.jsonPrimitive.content
            val stadium = matchObject["stadium"]!!.jsonPrimitive.content
            val referee = matchObject["referee"]!!.jsonPrimitive.content
            val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content
            val homeTeamScore = matchObject["homeTeamScore"]!!.jsonPrimitive.int
            val awayTeamScore = matchObject["awayTeamScore"]!!.jsonPrimitive.int

            val homeTeamGoals = matchObject["homeTeamGoals"]!!.jsonArray
            val awayTeamGoals = matchObject["awayTeamGoals"]!!.jsonArray

            //goal = GoalAPI.findById(homeTeamGoals[i])
            //goals.add(goal)

            val homeTeamSubstitutions = matchObject["homeTeamSubstitutions"]!!.jsonArray
            val awayTeamSubstitutions = matchObject["awayTeamSubstitutions"]!!.jsonArray
            //substitution = SubstitutionAPI().findById(homeTeamSubstitutions[i])
            //substitutions.add(substitution)

            val homeTeamLineup = matchObject["homeTeamLineup"]?.jsonObject
            val awayTeamLineup = matchObject["awayTeamLineup"]?.jsonObject
            //lineup = LineupAPI().findById(homeTeamLineup)

            val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
            val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

            val winner = matchObject["winner"]!!.jsonPrimitive.content
            //winner = TeamAPI().findById(winner)

            val match = Match(
                _id,
                homeTeam,
                awayTeam,
                SimpleDateFormat("yyyy-MM-dd").parse(date),
                stadium,
                referee,
                roundOrGroup
            )
            matches.add(match)
        }
        return matches
    }
}