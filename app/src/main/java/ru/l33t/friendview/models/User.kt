package ru.l33t.friendview.models

data class User(
    val id: String = "",
    val phone: String = "",
    var username: String = "",
    val friends: Set<String> = setOf()
)