package ru.l33t.friendview.models

data class User(
    val phone: String = "",
    var username: String = "",
    val friendsPhonesList: String = "",
    val photosUrls: String = "",
    val friendsList: MutableList<User> = mutableListOf()
) {
    override fun toString(): String {
        return "phone: $phone\n" +
                "username: $username\n" +
                "friendsPhones: ${friendsPhonesList.split("##")}\n" +
                "friendsList: ${if (friendsList.isNotEmpty()) friendsList[0].username else "[]"}"
    }
}