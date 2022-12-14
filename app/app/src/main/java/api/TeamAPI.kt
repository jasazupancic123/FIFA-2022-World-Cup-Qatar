package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*
import com.example.worldcupapp.Team

class TeamAPI(){
    val url = "http://localhost:3000/team"

    suspend fun find() : List<Team> {
        val client = HttpClient()
        val response: HttpResponse = client.get(url) {
            method = HttpMethod.Get
        }
        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val teams: MutableList<Team> = mutableListOf()
        for (team in data!!.jsonArray) {
            val teamObject = team.jsonObject
            val _id = teamObject["_id"]!!.jsonPrimitive.content
            val name = teamObject["name"]!!.jsonPrimitive.content
            val region = teamObject["region"]!!.jsonPrimitive.content
            val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
            val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

            val managerId = teamObject["manager"]!!.jsonPrimitive.content
            val manager = ManagerAPI().findById(managerId)

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
            teams.add(Team(_id, name, region, fifaCode, iso2, manager, flag, group, noOfTitles, isEliminated, points, goalsScored, matchesPlayed, matchesWon, matchesDrawn, matchesLost, goalsAgainst))
        }
        return teams
    }

    suspend fun findById(_id: String) : Team{
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/" + _id) {
            method = HttpMethod.Get
        }
        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val teamObject = data!!.jsonObject
        val _id = teamObject["_id"]!!.jsonPrimitive.content
        val name = teamObject["name"]!!.jsonPrimitive.content
        val region = teamObject["region"]!!.jsonPrimitive.content
        val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
        val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

        val managerId = teamObject["manager"]!!.jsonPrimitive.content
        val manager = ManagerAPI().findById(managerId)

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
        return Team(
            _id,
            name,
            region,
            fifaCode,
            iso2,
            manager,
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
            goalsAgainst)
    }

    suspend fun findByGroup(groupName: String): MutableList<Team> {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/group/" + groupName) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val teams: MutableList<Team> = mutableListOf()
        for (team in data!!.jsonArray) {
            val teamObject = team.jsonObject
            val _id = teamObject["_id"]!!.jsonPrimitive.content
            val name = teamObject["name"]!!.jsonPrimitive.content
            val region = teamObject["region"]!!.jsonPrimitive.content
            val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
            val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

            val managerId = teamObject["manager"]!!.jsonPrimitive.content
            val manager = ManagerAPI().findById(managerId)

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
            teams.add(Team(_id, name, region, fifaCode, iso2, manager, flag, group, noOfTitles, isEliminated, points, goalsScored, matchesPlayed, matchesWon, matchesDrawn, matchesLost, goalsAgainst))
        }
        return teams
    }

    suspend fun findByAtLeastOneTitle() : MutableList<Team> {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/findBy/atLeastOneTitle") {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val teams: MutableList<Team> = mutableListOf()
        for (team in data!!.jsonArray) {
            val teamObject = team.jsonObject
            val _id = teamObject["_id"]!!.jsonPrimitive.content
            val name = teamObject["name"]!!.jsonPrimitive.content
            val region = teamObject["region"]!!.jsonPrimitive.content
            val fifaCode = teamObject["fifaCode"]!!.jsonPrimitive.content
            val iso2 = teamObject["iso2"]!!.jsonPrimitive.content

            val managerId = teamObject["manager"]!!.jsonPrimitive.content
            val manager = ManagerAPI().findById(managerId)

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
            teams.add(Team(_id, name, region, fifaCode, iso2, manager, flag, group, noOfTitles, isEliminated, points, goalsScored, matchesPlayed, matchesWon, matchesDrawn, matchesLost, goalsAgainst))
        }
        return teams
    }
}