package com.example.worldcupapp

import java.util.Date

class Match(
    var _id: String,
    var homeTeam: Team,
    var awayTeam: Team,
    var date: Date,
    var stadium: String,
    var referee: String,
    var roundOrGroup: String,
    var homeTeamScore: Int? = null,
    var awayTeamScore: Int? = null,
    var minute: Int? = null,
    var isFinished: Boolean = false,
    var hasStarted: Boolean = false,
    var isHalfTime: Boolean = false,
    var winner: Team? = null
) {
    override fun toString(): String {
        return (
            "Match{" +
                "id_='" + _id + '\'' +
                ", homeTeam=" + homeTeam.toString() +
                ", awayTeam=" + awayTeam.toString() +
                ", date=" + date +
                ", stadium='" + stadium + '\'' +
                ", referee='" + referee + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", roundOrGroup='" + roundOrGroup + '\'' +
                ", minute=" + minute +
                ", isFinished=" + isFinished +
                ", hasStarted=" + hasStarted +
                ", isHalfTime=" + isHalfTime +
                ", winner=" + winner.toString() +
                '}'
            )
    }

}