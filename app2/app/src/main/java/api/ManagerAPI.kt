package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.*
import com.example.worldcupapp.Manager
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat

class ManagerAPI {
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/manager"
    suspend fun find() : MutableList<Manager> {
        val managers: MutableList<Manager> = mutableListOf()
        coroutineScope {
            val coroutine = async {
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
            }
        }
        return managers
    }

    fun findById(id_: String) : Manager? {
        val manager: Manager?
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://fifa-2022-world-cup-qatar.up.railway.app/manager/$id_")
            .build()
        val response: Response = client.newCall(request).execute()
        val data = Json.parseToJsonElement(response.body()!!.string()).jsonObject["data"]
        val managerObject = data!!.jsonObject
        val _id = managerObject["_id"]!!.jsonPrimitive.content
        val firstName = managerObject["firstName"]!!.jsonPrimitive.content
        val lastName = managerObject["lastName"]!!.jsonPrimitive.content
        val birthDate = managerObject["birthDate"]!!.jsonPrimitive.content
        val image = managerObject["image"]!!.jsonPrimitive.content
        manager = Manager(
            _id,
            firstName,
            lastName,
            SimpleDateFormat("yyyy-MM-dd").parse(birthDate),
            image
        )
        return manager
    }
}