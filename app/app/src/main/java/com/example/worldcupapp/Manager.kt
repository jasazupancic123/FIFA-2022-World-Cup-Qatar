package com.example.worldcupapp

import java.util.*

class Manager(
    var id_: String,
    var firstName: String,
    var lastName: String,
    var birthDate: Date,
    var image: String
) {
    override fun toString(): String {
        return (
            "Manager{" +
                "id_='" + id_ + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", image='" + image + '\'' +
                '}'
        )
    }
}