package com.example.worldcupapp

class Substitution (
    var _id: String,
    var minute: Int,
    var playerIn: Player,
    var playerOut: Player,
    var match: Match,
    var team: Team,
    var isHomeTeamSubstitution: Boolean
        ) {
    override fun toString(): String {
        return (
            "Substitution{" +
                "id_='" + _id + '\'' +
                ", minute=" + minute +
                ", playerIn=" + playerIn +
                ", playerOut=" + playerOut +
                ", match=" + match +
                ", team=" + team +
                ", isHomeTeamSubstitution=" + isHomeTeamSubstitution +
                '}'
            )
    }
}