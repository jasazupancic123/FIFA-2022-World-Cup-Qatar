package api

suspend fun main() {
    val matchAPI = MatchAPI()
    val matches = matchAPI.findUnfinished()
    for (match in matches) {
        println(match.toString())
    }
}