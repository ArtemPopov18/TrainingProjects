package com.popov.retrofit

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
) {
    fun getURL(): String {
        return large
    }
}
