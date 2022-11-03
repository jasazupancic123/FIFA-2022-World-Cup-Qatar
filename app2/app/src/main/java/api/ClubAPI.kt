package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Club
import com.example.worldcupapp.Goal
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.coroutines.*

class ClubAPI {
    var url = "https://fifa-2022-world-cup-qatar.up.railway.app/club"

    suspend fun find() : MutableList<Club>? {
        val clubs: MutableList<Club> = mutableListOf()
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
                for (club in data!!.jsonArray) {
                    val clubObject = club.jsonObject
                    val _id = clubObject["_id"]!!.jsonPrimitive.content
                    val name = clubObject["name"]!!.jsonPrimitive.content
                    val shortName = clubObject["shortName"]!!.jsonPrimitive.content
                    val image = clubObject["image"]!!.jsonPrimitive.content
                    val league = clubObject["league"]!!.jsonPrimitive.content
                    val club = Club(_id, name, shortName, image, league)
                    clubs.add(club)
                }
            }
        }
        return clubs
    }

    suspend fun findById(_id: String) : Club? {
        var club: Club? = null
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
                val clubObject = data!!.jsonObject
                val name = clubObject["name"]!!.jsonPrimitive.content
                val shortName = clubObject["shortName"]!!.jsonPrimitive.content
                val image = clubObject["image"]!!.jsonPrimitive.content
                val league = clubObject["league"]!!.jsonPrimitive.content
                club = Club(_id, name, shortName, image, league)
            }
        }
        return club
    }
}