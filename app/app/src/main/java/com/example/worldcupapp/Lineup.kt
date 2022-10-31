package com.example.worldcupapp

class Lineup (
    var _id: String,
    var type: String,
    var team: Team,
    var goalkeeper: Player,
    var defenders: MutableList<Player>,
    var midfielders: MutableList<Player>,
    var attackers: MutableList<Player>,
    var substitutes: MutableList<Player>,
        ) {
    override fun toString(): String {
        return (
            "Lineup{" +
                "id_='" + _id + '\'' +
                ", type='" + type + '\'' +
                ", team=" + team +
                ", goalkeeper=" + goalkeeper.toString() +
                ", defenders=" + defenders.toString() +
                ", midfielders=" + midfielders.toString() +
                ", attackers=" + attackers.toString() +
                ", substitutes=" + substitutes.toString() +
                '}'
            )
    }
}