package api

import com.example.worldcupapp.*
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*

import kotlinx.serialization.json.*
import okhttp3.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.text.SimpleDateFormat

class MatchAPI {
    val url = "https://fifa-2022-world-cup-qatar.up.railway.app/match"

    suspend fun find(): MutableList<Match> {
        val matches: MutableList<Match> = mutableListOf()
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
                for (match in data!!.jsonArray) {
                    val matchObject = match.jsonObject
                    val _id = matchObject["_id"]!!.jsonPrimitive.content

                    val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
                    val homeTeam =
                        TeamAPI().findById(homeTeamId) ?: throw Exception("No home team found")

                    val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
                    val awayTeam =
                        TeamAPI().findById(awayTeamId) ?: throw Exception("No away team found")

                    val date = matchObject["date"]!!.jsonPrimitive.content
                    val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                    val referee = matchObject["referee"]!!.jsonPrimitive.content
                    val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                    val homeTeamScore =
                        if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["homeTeamScore"]!!.jsonPrimitive.int
                        }

                    val awayTeamScore =
                        if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["awayTeamScore"]!!.jsonPrimitive.int
                        }

                    val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                        null
                    } else {
                        matchObject["minute"]!!.jsonPrimitive.int
                    }

                    val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                    val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                    val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

                    val winner = if (matchObject["winner"] != JsonNull) {
                        val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                        val winner =
                            TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                        winner
                    } else {
                        null
                    }

                    val match = Match(
                        _id,
                        homeTeam!!,
                        awayTeam!!,
                        SimpleDateFormat("yyyy-MM-dd").parse(date),
                        stadium,
                        referee,
                        roundOrGroup,
                        homeTeamScore,
                        awayTeamScore,
                        minute,
                        isFinished,
                        hasStarted,
                        isHalfTime,
                        winner
                    )
                    matches.add(match)
                }
            }
        }
        return matches
    }

    suspend fun findById(_id: String): Match? {
        var match: Match? = null
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
                val matchObject = data!!.jsonObject
                val _id = matchObject["_id"]!!.jsonPrimitive.content

                val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
                val homeTeam =
                    TeamAPI().findById(homeTeamId) ?: throw Exception("No home team found")

                val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
                val awayTeam =
                    TeamAPI().findById(awayTeamId) ?: throw Exception("No away team found")


                val date = matchObject["date"]!!.jsonPrimitive.content
                val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                val referee = matchObject["referee"]!!.jsonPrimitive.content
                val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                val homeTeamScore = if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                    null
                } else {
                    matchObject["homeTeamScore"]!!.jsonPrimitive.int
                }

                val awayTeamScore = if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                    null
                } else {
                    matchObject["awayTeamScore"]!!.jsonPrimitive.int
                }

                val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                    null
                } else {
                    matchObject["minute"]!!.jsonPrimitive.int
                }

                val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

                val winner = if (matchObject["winner"] != JsonNull) {
                    val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                    val winner = TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                    winner
                } else {
                    null
                }

                match = Match(
                    _id,
                    homeTeam!!,
                    awayTeam!!,
                    SimpleDateFormat("yyyy-MM-dd").parse(date),
                    stadium,
                    referee,
                    roundOrGroup,
                    homeTeamScore,
                    awayTeamScore,
                    minute,
                    isFinished,
                    hasStarted,
                    isHalfTime,
                    winner
                )
            }
        }
        return match
    }

    fun findUpcomingFive(): MutableList<Match> {
        val matches: MutableList<Match> = mutableListOf()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://localhost:3000/upcoming/five")
            .build()
        val response: Response = client.newCall(request).execute()
        val data = Json.parseToJsonElement(response.body()!!.string()).jsonObject["data"]
        println(data)
                for (match in data!!.jsonArray) {
                    val matchObject = match.jsonObject
                    val _id = matchObject["_id"]!!.jsonPrimitive.content

                    val homeTeam = Gson().fromJson(matchObject["homeTeam"]!!.jsonPrimitive.content, Team::class.java)

                    val awayTeam = Gson().fromJson(matchObject["awayTeam"]!!.jsonPrimitive.content, Team::class.java)

                    val date = matchObject["date"]!!.jsonPrimitive.content
                    val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                    val referee = matchObject["referee"]!!.jsonPrimitive.content
                    val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                    val homeTeamScore =
                        if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["homeTeamScore"]!!.jsonPrimitive.int
                        }

                    val awayTeamScore =
                        if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["awayTeamScore"]!!.jsonPrimitive.int
                        }

                    val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                        null
                    } else {
                        matchObject["minute"]!!.jsonPrimitive.int
                    }

                    val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                    val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                    val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

                    val winner = if (matchObject["winner"] != JsonNull) {
                        val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                        val winner =
                            TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                        winner
                    } else {
                        null
                    }
                    println("got winner")

                    val match = Match(
                        _id,
                        homeTeam!!,
                        awayTeam!!,
                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'").parse(date),
                        stadium,
                        referee,
                        roundOrGroup,
                        homeTeamScore,
                        awayTeamScore,
                        minute,
                        isFinished,
                        hasStarted,
                        isHalfTime,
                        winner
                    )
                    matches.add(match)
                    println("Added match")
                }
        /*
                val response: HttpResponse = client.get(url + "/upcoming/five") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (match in data!!.jsonArray) {
                    val matchObject = match.jsonObject
                    val _id = matchObject["_id"]!!.jsonPrimitive.content

                    val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
                    val homeTeam = TeamAPI().findById(homeTeamId) ?: throw Exception("No home team found")

                    val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
                    val awayTeam = TeamAPI().findById(awayTeamId) ?: throw Exception("No away team found")

                    val date = matchObject["date"]!!.jsonPrimitive.content
                    val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                    val referee = matchObject["referee"]!!.jsonPrimitive.content
                    val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                    val homeTeamScore =
                        if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["homeTeamScore"]!!.jsonPrimitive.int
                        }

                    val awayTeamScore =
                        if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["awayTeamScore"]!!.jsonPrimitive.int
                        }

                    val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                        null
                    } else {
                        matchObject["minute"]!!.jsonPrimitive.int
                    }

                    val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                    val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                    val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean

                    val winner = if (matchObject["winner"] != JsonNull) {
                        val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                        val winner = TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                        winner
                    } else {
                        null
                    }

                    val match = Match(
                        _id,
                        homeTeam!!,
                        awayTeam!!,
                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'").parse(date),
                        stadium,
                        referee,
                        roundOrGroup,
                        homeTeamScore,
                        awayTeamScore,
                        minute,
                        isFinished,
                        hasStarted,
                        isHalfTime,
                        winner
                    )
                    matches.add(match)
                }
            }
        }
        return matches
         */
        return matches
    }

    suspend fun findByTeam(teamId: String): MutableList<Match>{
        val matches: MutableList<Match> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get("$url/team/$teamId") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (match in data!!.jsonArray) {
                    val matchObject = match.jsonObject
                    val _id = matchObject["_id"]!!.jsonPrimitive.content

                    val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
                    val homeTeam = TeamAPI().findById(homeTeamId) ?: throw Exception("No home team found")

                    val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
                    val awayTeam = TeamAPI().findById(awayTeamId) ?: throw Exception("No away team found")


                    val date = matchObject["date"]!!.jsonPrimitive.content
                    val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                    val referee = matchObject["referee"]!!.jsonPrimitive.content
                    val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                    val homeTeamScore =
                        if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["homeTeamScore"]!!.jsonPrimitive.int
                        }

                    val awayTeamScore =
                        if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["awayTeamScore"]!!.jsonPrimitive.int
                        }

                    val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                        null
                    } else {
                        matchObject["minute"]!!.jsonPrimitive.int
                    }

                    val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                    val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                    val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean


                    val winner = if (matchObject["winner"] != JsonNull) {
                        val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                        val winner = TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                        winner
                    } else {
                        null
                    }

                    val match = Match(
                        _id,
                        homeTeam!!,
                        awayTeam!!,
                        SimpleDateFormat("yyyy-MM-dd").parse(date),
                        stadium,
                        referee,
                        roundOrGroup,
                        homeTeamScore,
                        awayTeamScore,
                        minute,
                        isFinished,
                        hasStarted,
                        isHalfTime,
                        winner
                    )
                    matches.add(match)
                }
            }
        }
        return matches
    }

    suspend fun findByRound(round: String): MutableList<Match>{
        val matches: MutableList<Match> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse =
                    client.get("$url/round/GroupA") { //ne sme biti presledek
                        method = HttpMethod.Get
                    }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (match in data!!.jsonArray) {
                    val matchObject = match.jsonObject
                    val _id = matchObject["_id"]!!.jsonPrimitive.content

                    val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
                    val homeTeam = TeamAPI().findById(homeTeamId) ?: throw Exception("No home team found")

                    val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
                    val awayTeam = TeamAPI().findById(awayTeamId) ?: throw Exception("No away team found")


                    val date = matchObject["date"]!!.jsonPrimitive.content
                    val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                    val referee = matchObject["referee"]!!.jsonPrimitive.content
                    val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                    val homeTeamScore =
                        if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["homeTeamScore"]!!.jsonPrimitive.int
                        }

                    val awayTeamScore =
                        if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["awayTeamScore"]!!.jsonPrimitive.int
                        }

                    val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                        null
                    } else {
                        matchObject["minute"]!!.jsonPrimitive.int
                    }

                    val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                    val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                    val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean


                    val winner = if (matchObject["winner"] != JsonNull) {
                        val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                        val winner = TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                        winner
                    } else {
                        null
                    }

                    val match = Match(
                        _id,
                        homeTeam!!,
                        awayTeam!!,
                        SimpleDateFormat("yyyy-MM-dd").parse(date),
                        stadium,
                        referee,
                        roundOrGroup,
                        homeTeamScore,
                        awayTeamScore,
                        minute,
                        isFinished,
                        hasStarted,
                        isHalfTime,
                        winner
                    )
                    matches.add(match)
                }
            }
        }
        return matches
    }

    suspend fun findFinished() : MutableList<Match>{
        val matches: MutableList<Match> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get("$url/status/finished") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (match in data!!.jsonArray) {
                    val matchObject = match.jsonObject
                    val _id = matchObject["_id"]!!.jsonPrimitive.content

                    val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
                    val homeTeam = TeamAPI().findById(homeTeamId) ?: throw Exception("No home team found")

                    val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
                    val awayTeam = TeamAPI().findById(awayTeamId) ?: throw Exception("No away team found")


                    val date = matchObject["date"]!!.jsonPrimitive.content
                    val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                    val referee = matchObject["referee"]!!.jsonPrimitive.content
                    val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                    val homeTeamScore =
                        if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["homeTeamScore"]!!.jsonPrimitive.int
                        }

                    val awayTeamScore =
                        if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["awayTeamScore"]!!.jsonPrimitive.int
                        }

                    val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                        null
                    } else {
                        matchObject["minute"]!!.jsonPrimitive.int
                    }

                    val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                    val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                    val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean


                    val winner = if (matchObject["winner"] != JsonNull) {
                        val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                        val winner = TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                        winner
                    } else {
                        null
                    }

                    val match = Match(
                        _id,
                        homeTeam!!,
                        awayTeam!!,
                        SimpleDateFormat("yyyy-MM-dd").parse(date),
                        stadium,
                        referee,
                        roundOrGroup,
                        homeTeamScore,
                        awayTeamScore,
                        minute,
                        isFinished,
                        hasStarted,
                        isHalfTime,
                        winner
                    )
                    matches.add(match)
                }
            }
        }
        return matches
    }

    suspend fun findUnfinished(): MutableList<Match>{
        val matches: MutableList<Match> = mutableListOf()
        coroutineScope {
            val coroutine = async {
                val client = HttpClient()
                val response: HttpResponse = client.get("$url/status/unfinished") {
                    method = HttpMethod.Get
                }

                if (response.status.value == 404) {
                    throw Exception("Status code 404")
                }

                if (Json.parseToJsonElement(response.body()).jsonObject["data"].toString() == "null") {
                    throw Exception("No data found")
                }

                val data = Json.parseToJsonElement(response.body()).jsonObject["data"]
                for (match in data!!.jsonArray) {
                    val matchObject = match.jsonObject
                    val _id = matchObject["_id"]!!.jsonPrimitive.content

                    val homeTeamId = matchObject["homeTeam"]!!.jsonPrimitive.content
                    val homeTeam = TeamAPI().findById(homeTeamId) ?: throw Exception("No home team found")

                    val awayTeamId = matchObject["awayTeam"]!!.jsonPrimitive.content
                    val awayTeam = TeamAPI().findById(awayTeamId) ?: throw Exception("No away team found")


                    val date = matchObject["date"]!!.jsonPrimitive.content
                    val stadium = matchObject["stadium"]!!.jsonPrimitive.content
                    val referee = matchObject["referee"]!!.jsonPrimitive.content
                    val roundOrGroup = matchObject["roundOrGroup"]!!.jsonPrimitive.content

                    val homeTeamScore =
                        if (matchObject["homeTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["homeTeamScore"]!!.jsonPrimitive.int
                        }

                    val awayTeamScore =
                        if (matchObject["awayTeamScore"]?.jsonPrimitive?.int == null) {
                            null
                        } else {
                            matchObject["awayTeamScore"]!!.jsonPrimitive.int
                        }

                    val minute = if (matchObject["minute"]?.jsonPrimitive?.int == null) {
                        null
                    } else {
                        matchObject["minute"]!!.jsonPrimitive.int
                    }

                    val isFinished = matchObject["isFinished"]!!.jsonPrimitive.boolean
                    val hasStarted = matchObject["hasStarted"]!!.jsonPrimitive.boolean
                    val isHalfTime = matchObject["isHalfTime"]!!.jsonPrimitive.boolean


                    val winner = if (matchObject["winner"] != JsonNull) {
                        val winnerId = matchObject["winner"]!!.jsonPrimitive.content
                        val winner = TeamAPI().findById(winnerId) ?: throw Exception("No winner found")
                        winner
                    } else {
                        null
                    }

                    val match = Match(
                        _id,
                        homeTeam!!,
                        awayTeam!!,
                        SimpleDateFormat("yyyy-MM-dd").parse(date),
                        stadium,
                        referee,
                        roundOrGroup,
                        homeTeamScore,
                        awayTeamScore,
                        minute,
                        isFinished,
                        hasStarted,
                        isHalfTime,
                        winner
                    )
                    matches.add(match)
                }
            }
        }
        return matches
    }
}