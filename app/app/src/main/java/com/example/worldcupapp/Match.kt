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
    var homeTeamGoals: MutableList<Goal> = mutableListOf(),
    var awayTeamGoals: MutableList<Goal> = mutableListOf(),
    var homeTeamSubstitutions: MutableList<Substitution> = mutableListOf(),
    var awayTeamSubstitutions: MutableList<Substitution> = mutableListOf(),
    var homeTeamLineup: Lineup? = null,
    var awayTeamLineup: Lineup? = null,
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
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", homeTeamSubstitutions=" + homeTeamSubstitutions +
                ", awayTeamSubstitutions=" + awayTeamSubstitutions +
                ", homeTeamLineup=" + homeTeamLineup.toString() +
                ", awayTeamLineup=" + awayTeamLineup.toString() +
                ", isFinished=" + isFinished +
                ", hasStarted=" + hasStarted +
                ", isHalfTime=" + isHalfTime +
                ", winner=" + winner.toString() +
                '}'
            )
    }

}