package com.example.assignment4_annika_jan

data class DataAPIItem (
    val body: String = "",
    val id: Int = 0,
    val title: String = "",
    val userId: Int = 0
) {
    constructor() : this("", 0, "", 0)
}
