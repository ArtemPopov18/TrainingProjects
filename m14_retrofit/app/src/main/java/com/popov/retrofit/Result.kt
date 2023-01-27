package com.popov.retrofit

data class Result(
    val gender: String,
    val location: Location,
    val name: Name,
    val nat: String,
    val picture: Picture
) {
    override fun toString(): String {
        return "$gender \n" +
                "${name.title} ${name.first} ${name.last} \n" +
                "${location.city} ${location.street.name} ${location.street.number} \n" +
                "$nat"
    }
}