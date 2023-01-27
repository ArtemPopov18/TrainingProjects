package com.popov.retrofit

data class People(
    val info: Info,
    val results: List<Result>
) {
    override fun toString(): String {
        return "${results.toString()}"
    }
}