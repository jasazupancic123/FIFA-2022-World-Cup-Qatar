package com.example.worldcupapp

class Goal (
    var _id: String,
    var scorer: Player,
    var assister: Player,
    var minute: Int,
    var match: Match,
    var isHomeTeamGoal: Boolean,
    var isOwnGoal: Boolean = false
        ) {
    override fun toString(): String {
        return (
            "Goal{" +
                "id_='" + _id + '\'' +
                ", scorer=" + scorer +
                ", assister=" + assister +
                ", minute=" + minute +
                ", match=" + match +
                ", isHomeTeamGoal=" + isHomeTeamGoal +
                ", isOwnGoal=" + isOwnGoal +
                '}'
            )
    }
}