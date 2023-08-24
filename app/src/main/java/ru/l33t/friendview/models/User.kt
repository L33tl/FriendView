package ru.l33t.friendview.models

import ru.l33t.friendview.utils.PHONES_DELIMITER

data class User(
    val phone: String = "",
    var username: String = "",
    var friendsPhonesList: String = "",
    var photosUrls: String = "",
    val friendsList: MutableList<User> = mutableListOf()
) {
    override fun toString(): String {
        return "phone: $phone\n" +
                "username: $username\n" +
                "friendsPhones: ${friendsPhonesList.split(PHONES_DELIMITER)}\n" +
                "friendsList: ${if (friendsList.isNotEmpty()) friendsList[0].username else "[]"}"
    }
}