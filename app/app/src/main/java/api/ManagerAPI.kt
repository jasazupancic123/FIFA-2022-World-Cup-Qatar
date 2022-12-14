package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Manager
import java.text.SimpleDateFormat

class ManagerAPI {
    val url = "http://localhost:3000/manager"
    suspend fun find() : List<Manager> {
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
        val managers: MutableList<Manager> = mutableListOf()
        for (manager in data!!.jsonArray) {
            val managerObject = manager.jsonObject
            val _id = managerObject["_id"]!!.jsonPrimitive.content
            val firstName = managerObject["firstName"]!!.jsonPrimitive.content
            val lastName = managerObject["lastName"]!!.jsonPrimitive.content
            val birthDate = managerObject["birthDate"]!!.jsonPrimitive.content
            val image = managerObject["image"]!!.jsonPrimitive.content
            val manager = Manager(_id, firstName, lastName, SimpleDateFormat("yyyy-MM-dd").parse(birthDate), image)
            managers.add(manager)
        }
        return managers
    }

    suspend fun findById(id_: String) : Manager {
        val client = HttpClient()
        val response: HttpResponse = client.get(url + "/" + id_) {
            method = HttpMethod.Get
        }

        if(response.status.value == 404) {
            throw Exception("Status code 404")
        }

        if(Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
            throw Exception("No data found")
        }

        val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
        val managerObject = data!!.jsonObject
        val _id = managerObject["_id"]!!.jsonPrimitive.content
        val firstName = managerObject["firstName"]!!.jsonPrimitive.content
        val lastName = managerObject["lastName"]!!.jsonPrimitive.content
        val birthDate = managerObject["birthDate"]!!.jsonPrimitive.content
        val image = managerObject["image"]!!.jsonPrimitive.content
        return Manager(_id, firstName, lastName, SimpleDateFormat("yyyy-MM-dd").parse(birthDate), image)
    }
}