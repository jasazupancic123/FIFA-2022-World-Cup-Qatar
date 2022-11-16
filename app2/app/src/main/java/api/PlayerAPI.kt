package api

import com.example.worldcupapp.Club
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Player
import com.example.worldcupapp.Team
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.text.SimpleDateFormat

class PlayerAPI {
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/player"

    suspend fun find() : MutableList<Player> {
        val players: MutableList<Player> = mutableListOf<Player>()
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
                for (player in data!!.jsonArray) {
                    val playerObject = player.jsonObject
                    val _id = playerObject["_id"]!!.jsonPrimitive.content
                    val firstName = playerObject["firstName"]!!.jsonPrimitive.content
                    val lastName = playerObject["lastName"]!!.jsonPrimitive.content
                    val image = playerObject["image"]!!.jsonPrimitive.content

                    val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                    val position = playerObject["position"]!!.jsonPrimitive.content
                    val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                    val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                    val height = playerObject["height"]!!.jsonPrimitive.float
                    val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                    val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

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
                        club!!,
                        appearances,
                        goals,
                        assists,
                        yellowCards,
                        redCards
                    )
                    players.add(player)
                }
            }
        }
        return players
    }

    suspend fun findById(_id: String) : Player? {
        var player: Player? = null
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/" + _id) {
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

                val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                val position = playerObject["position"]!!.jsonPrimitive.content
                val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                val height = playerObject["height"]!!.jsonPrimitive.float
                val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

                val appearances = playerObject["appearances"]!!.jsonPrimitive.int
                val goals = playerObject["goals"]!!.jsonPrimitive.int
                val assists = playerObject["assists"]!!.jsonPrimitive.int
                val yellowCards = playerObject["yellowCards"]!!.jsonPrimitive.int
                val redCards = playerObject["redCards"]!!.jsonPrimitive.int

                player = Player(
                    _id,
                    firstName,
                    lastName,
                    image,
                    nationality!!,
                    position,
                    shirtNumber,
                    SimpleDateFormat("yyyy-MM-dd").parse(birthDate),
                    height,
                    prefferedFoot,
                    club!!,
                    appearances,
                    goals,
                    assists,
                    yellowCards,
                    redCards
                )
            }
        }
        return player
    }

    suspend fun findByFirstAndLastName(firstName: String, lastName: String): Player? {
        var player: Player? = null
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse =
                    client.get(url + "/name/" + firstName + "/" + lastName) {
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

                val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                val position = playerObject["position"]!!.jsonPrimitive.content
                val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                val height = playerObject["height"]!!.jsonPrimitive.float
                val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

                val appearances = playerObject["appearances"]!!.jsonPrimitive.int
                val goals = playerObject["goals"]!!.jsonPrimitive.int
                val assists = playerObject["assists"]!!.jsonPrimitive.int
                val yellowCards = playerObject["yellowCards"]!!.jsonPrimitive.int
                val redCards = playerObject["redCards"]!!.jsonPrimitive.int

                player = Player(
                    _id,
                    firstName,
                    lastName,
                    image,
                    nationality!!,
                    position,
                    shirtNumber,
                    SimpleDateFormat("yyyy-MM-dd").parse(birthDate),
                    height,
                    prefferedFoot,
                    club!!,
                    appearances,
                    goals,
                    assists,
                    yellowCards,
                    redCards
                )
            }
        }
        return player
    }

    suspend fun findByTeam(teamId: String) : MutableList<Player> {
        val players: MutableList<Player> = mutableListOf<Player>()
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
                for (player in data!!.jsonArray) {
                    val playerObject = player.jsonObject
                    val _id = playerObject["_id"]!!.jsonPrimitive.content
                    val firstName = playerObject["firstName"]!!.jsonPrimitive.content
                    val lastName = playerObject["lastName"]!!.jsonPrimitive.content
                    val image = playerObject["image"]!!.jsonPrimitive.content

                    val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                    val position = playerObject["position"]!!.jsonPrimitive.content
                    val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                    val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                    val height = playerObject["height"]!!.jsonPrimitive.float
                    val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                    val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

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
                        club!!,
                        appearances,
                        goals,
                        assists,
                        yellowCards,
                        redCards
                    )
                    players.add(player)
                }
            }
        }
        return players
    }

    suspend fun findByMostGoals() : MutableList<Player> {
        val players: MutableList<Player> = mutableListOf<Player>()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/stats/mostGoals/") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (player in data!!.jsonArray) {
                    val playerObject = player.jsonObject
                    val _id = playerObject["_id"]!!.jsonPrimitive.content
                    val firstName = playerObject["firstName"]!!.jsonPrimitive.content
                    val lastName = playerObject["lastName"]!!.jsonPrimitive.content
                    val image = playerObject["image"]!!.jsonPrimitive.content

                    val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                    val position = playerObject["position"]!!.jsonPrimitive.content
                    val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                    val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                    val height = playerObject["height"]!!.jsonPrimitive.float
                    val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                    val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

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
                        club!!,
                        appearances,
                        goals,
                        assists,
                        yellowCards,
                        redCards
                    )
                    players.add(player)
                }
            }
        }
        return players
    }

    suspend fun findByMostAssists() : MutableList<Player> {
        val players: MutableList<Player> = mutableListOf<Player>()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/stats/mostAssists") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (player in data!!.jsonArray) {
                    val playerObject = player.jsonObject
                    val _id = playerObject["_id"]!!.jsonPrimitive.content
                    val firstName = playerObject["firstName"]!!.jsonPrimitive.content
                    val lastName = playerObject["lastName"]!!.jsonPrimitive.content
                    val image = playerObject["image"]!!.jsonPrimitive.content

                    val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                    val position = playerObject["position"]!!.jsonPrimitive.content
                    val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                    val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                    val height = playerObject["height"]!!.jsonPrimitive.float
                    val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                    val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

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
                        club!!,
                        appearances,
                        goals,
                        assists,
                        yellowCards,
                        redCards
                    )
                    players.add(player)
                }
            }
        }
        return players
    }

    suspend fun findByMostYellowCards() : MutableList<Player> {
        val players: MutableList<Player> = mutableListOf<Player>()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/stats/mostYellowCards") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (player in data!!.jsonArray) {
                    val playerObject = player.jsonObject
                    val _id = playerObject["_id"]!!.jsonPrimitive.content
                    val firstName = playerObject["firstName"]!!.jsonPrimitive.content
                    val lastName = playerObject["lastName"]!!.jsonPrimitive.content
                    val image = playerObject["image"]!!.jsonPrimitive.content

                    val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                    val position = playerObject["position"]!!.jsonPrimitive.content
                    val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                    val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                    val height = playerObject["height"]!!.jsonPrimitive.float
                    val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                    val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

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
                        club!!,
                        appearances,
                        goals,
                        assists,
                        yellowCards,
                        redCards
                    )
                    players.add(player)
                }
            }
        }
        return players
    }

    suspend fun findByMostRedCards() : MutableList<Player> {
        val players: MutableList<Player> = mutableListOf<Player>()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get(url + "/stats/mostRedCards") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (player in data!!.jsonArray) {
                    val playerObject = player.jsonObject
                    val _id = playerObject["_id"]!!.jsonPrimitive.content
                    val firstName = playerObject["firstName"]!!.jsonPrimitive.content
                    val lastName = playerObject["lastName"]!!.jsonPrimitive.content
                    val image = playerObject["image"]!!.jsonPrimitive.content

                    val nationality = Gson().fromJson(playerObject["nationality"]!!.jsonObject.toString(), Team::class.java)

                    val position = playerObject["position"]!!.jsonPrimitive.content
                    val shirtNumber = playerObject["shirtNumber"]!!.jsonPrimitive.int
                    val birthDate = playerObject["birthDate"]!!.jsonPrimitive.content
                    val height = playerObject["height"]!!.jsonPrimitive.float
                    val prefferedFoot = playerObject["prefferedFoot"]!!.jsonPrimitive.content

                    val club = Gson().fromJson(playerObject["club"]!!.jsonObject.toString(), Club::class.java)

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
                        club!!,
                        appearances,
                        goals,
                        assists,
                        yellowCards,
                        redCards
                    )
                    players.add(player)
                }
            }
        }
        return players
    }
}