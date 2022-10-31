package com.example.worldcupapp

class Team (
    var id_: String,
    var name: String,
    var region: String,
    var fifaCode: String,
    var iso2: String,
    var manager: Manager,
    var flag: String,
    var group: String,
    var noOfTitles: Int = 0,
    var isEliminated: Boolean = false,
    var points: Int = 0,
    var goalsScored: Int = 0,
    var matchesPlayed: Int = 0,
    var matchesWon: Int = 0,
    var matchesDrawn: Int = 0,
    var matchesLost: Int = 0,
    var goalsAgainst: Int = 0
) {
    override fun toString(): String {
        return (
            "Team{" +
                "id_='" + id_ + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", fifaCode='" + fifaCode + '\'' +
                ", iso2='" + iso2 + '\'' +
                ", manager=" + manager +
                ", flag='" + flag + '\'' +
                ", group='" + group + '\'' +
                ", noOfTitles=" + noOfTitles +
                ", isEliminated=" + isEliminated +
                ", points=" + points +
                ", goalsScored=" + goalsScored +
                ", goalsAgainst=" + goalsAgainst +
                ", matchesPlayed=" + matchesPlayed +
                ", matchesWon=" + matchesWon +
                ", matchesDrawn=" + matchesDrawn +
                ", matchesLost=" + matchesLost +
                '}'
            )
    }
}