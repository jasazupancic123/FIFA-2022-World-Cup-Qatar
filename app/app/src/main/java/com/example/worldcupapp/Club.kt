package com.example.worldcupapp

class Club(
    var id_: String,
    var name: String,
    var shortName: String,
    var image: String,
    var league: String
) {
    override fun toString(): String {
        return (
            "Club{" +
                "id_='" + id_ + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", image='" + image + '\'' +
                ", league='" + league + '\'' +
            '}'
            )
    }
}