package com.example.worldcupapp


class Club(
    var _id: String,
    var name: String,
    var shortName: String,
    var image: String,
    var league: String
) {
    override fun toString(): String {
        return (
            "Club{" +
                "id_='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", image='" + image + '\'' +
                ", league='" + league + '\'' +
            '}'
            )
    }
}