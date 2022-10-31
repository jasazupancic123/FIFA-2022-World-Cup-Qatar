package com.example.worldcupapp

import java.util.*

class Player(
    var id_: String,
    var firstName: String,
    var lastName: String,
    var image: String,
    var nationality: Team,
    var position: String,
    var shirtNumber: String,
    var birthDate: Date,
    var height: Float,
    var prefferedFoot: String,
    var club: Club,
    var appearances: Int = 0,
    var goals: Int = 0,
    var assists: Int = 0,
    var yellowCards: Int = 0,
    var redCards: Int = 0,
) {
    override fun toString(): String{
        return (
            "Player{" +
                "id_='" + id_ + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", nationality='" + nationality + '\'' +
                ", position='" + position + '\'' +
                ", shirtNumber='" + shirtNumber + '\'' +
                ", birthDate=" + birthDate +
                ", height=" + height +
                ", prefferedFoot='" + prefferedFoot + '\'' +
                ", club='" + club.toString() + '\'' +
                ", appearances=" + appearances +
                ", goals=" + goals +
                ", assists=" + assists +
                ", yellowCards=" + yellowCards +
                ", redCards=" + redCards + "}"
            )
    }
}