package com.example.worldcupapp

class Substitution (
    var id_: String,
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
                "id_='" + id_ + '\'' +
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