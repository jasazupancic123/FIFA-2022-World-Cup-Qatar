package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Player
import java.text.SimpleDateFormat

class PlayerAPI {
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/player"

    suspend fun find() : MutableList<Player> {
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
        val players: MutableList<Player> = mutableListOf()
        for (player in data!!.jsonArray) {
            val playerObject = player.jsonObject
            val _id = playerObject["_id"]!!.jsonPrimitive.content
            val firstName = playerObject["firstName"]!!.jsonPrimitive.content
            val lastName = playerObject["lastName"]!!.jsonPrimitive.content
            val image = playerObject["image"]!!.jsonPrimitive.content

            val nationalityId = playerObject["nationality"]!!.jsonPrimitive.content
            val nationality = TeamAPI().findById(nationalityId)

            val position = playerObject["position"]!!.jsonPrimitive.content
            val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
            val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
            val height = playerObject["height"]!!.jsonPrimitive.float
            val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

            val clubId = playerObject["club"]!!.jsonPrimitive.content
            val club = ClubAPI().findById(clubId)

            val appearances = playerObject["appearances"]!!.jsonPrimitive.int
            val goals = playerObject["goals"]!!.jsonPrimitive.int
            val assists = playerObject["assists"]!!.jsonPrimitive.int
            val yellowCards = playerObject["yellowCards"]!!.jsonPrimitive.int
            val redCards = playerObject["redCards"]!!.jsonPrimitive.int

            val player = Player(
                _id,
                firstName,
                lastName,
                image,
                nationality,
                position,
                shirtNumber,
                SimpleDateFormat("yyyy-MM-dd").parse(birthDate),
                height,
                prefferedFoot,
                club,
                appearances,
                goals,
                assists,
                yellowCards,
                redCards
            )
            players.add(player)
        }
        return players
    }

    suspend fun findById(_id: String) : Player{
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
        val playerObject = data!!.jsonObject
        val _id = playerObject["_id"]!!.jsonPrimitive.content
        val firstName = playerObject["firstName"]!!.jsonPrimitive.content
        val lastName = playerObject["lastName"]!!.jsonPrimitive.content
        val image = playerObject["image"]!!.jsonPrimitive.content

        val nationalityId = playerObject["nationality"]!!.jsonPrimitive.content
        val nationality = TeamAPI().findById(nationalityId)

        val position = playerObject["position"]!!.jsonPrimitive.content
        val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
        val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
        val height = playerObject["height"]!!.jsonPrimitive.float
        val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

        val clubId = playerObject["club"]!!.jsonPrimitive.content
        val club = ClubAPI().findById(clubId)

        val appearances = playerObject["appearances"]!!.jsonPrimitive.int
        val goals = playerObject["goals"]!!.jsonPrimitive.int
        val assists = playerObject["assists"]!!.jsonPrimitive.int
        val yellowCards = playerObject["yellowCards"]!!.jsonPrimitive.int
        val redCards = playerObject["redCards"]!!.jsonPrimitive.int

        val player = Player(
            _id,
            firstName,
            lastName,
            image,
            nationality,
            position,
            shirtNumber,
            SimpleDateFormat("yyyy-MM-dd").parse(birthDate),
            height,
            prefferedFoot,
            club,
            appearances,
            goals,
            assists,
            yellowCards,
            redCards
        )
        return player
    }

    suspend fun findByFirstAndLastName(firstName: String, lastName: String): Player {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/name/" + firstName + "/" + lastName) {
            method = HttpMethod.Get
        }

        if (response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val playerObject = data!!.jsonObject
        val _id = playerObject["_id"]!!.jsonPrimitive.content
        val firstName = playerObject["firstName"]!!.jsonPrimitive.content
        val lastName = playerObject["lastName"]!!.jsonPrimitive.content
        val image = playerObject["image"]!!.jsonPrimitive.content

        val nationalityId = playerObject["nationality"]!!.jsonPrimitive.content
        val nationality = TeamAPI().findById(nationalityId)

        val position = playerObject["position"]!!.jsonPrimitive.content
        val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
        val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
        val height = playerObject["height"]!!.jsonPrimitive.float
        val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

        val clubId = playerObject["club"]!!.jsonPrimitive.content
        val club = ClubAPI().findById(clubId)

        val appearances = playerObject["appearances"]!!.jsonPrimitive.int
        val goals = playerObject["goals"]!!.jsonPrimitive.int
        val assists = playerObject["assists"]!!.jsonPrimitive.int
        val yellowCards = playerObject["yellowCards"]!!.jsonPrimitive.int
        val redCards = playerObject["redCards"]!!.jsonPrimitive.int

        val player = Player(
            _id,
            firstName,
            lastName,
            image,
            nationality,
            position,
            shirtNumber,
            SimpleDateFormat("yyyy-MM-dd").parse(birthDate),
            height,
            prefferedFoot,
            club,
            appearances,
            goals,
            assists,
            yellowCards,
            redCards
        )
        return player
    }

    suspend fun findByTeam(teamId: String) : MutableList<Player> {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/team/" + teamId) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val players: MutableList<Player> = mutableListOf()
        for (player in data!!.jsonArray) {
            val playerObject = player.jsonObject
            val _id = playerObject["_id"]!!.jsonPrimitive.content
            val firstName = playerObject["firstName"]!!.jsonPrimitive.content
            val lastName = playerObject["lastName"]!!.jsonPrimitive.content
            val image = playerObject["image"]!!.jsonPrimitive.content

            val nationalityId = playerObject["nationality"]!!.jsonPrimitive.content
            val nationality = TeamAPI().findById(nationalityId)

            val position = playerObject["position"]!!.jsonPrimitive.content
            val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
            val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
            val height = playerObject["height"]!!.jsonPrimitive.float
            val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

            val clubId = playerObject["club"]!!.jsonPrimitive.content
            val club = ClubAPI().findById(clubId)

            val appearances = playerObject["appearances"]!!.jsonPrimitive.int
            val goals = playerObject["goals"]!!.jsonPrimitive.int
            val assists = playerObject["assists"]!!.jsonPrimitive.int
            val yellowCards = playerObject["yellowCards"]!!.jsonPrimitive.int
            val redCards = playerObject["redCards"]!!.jsonPrimitive.int

            val player = Player(
                _id,
                firstName,
                lastName,
                image,
                nationality,
                position,
                shirtNumber,
                SimpleDateFormat("yyyy-MM-dd").parse(birthDate),
                height,
                prefferedFoot,
                club,
                appearances,
                goals,
                assists,
                yellowCards,
                redCards
            )
            players.add(player)
        }
        return players
    }
}