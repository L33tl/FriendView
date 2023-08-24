package ru.l33t.friendview.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ru.l33t.friendview.models.User

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: User
lateinit var PHONE: String

const val NODE_USERS = "users"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FRIENDS_PHONES = "friendsPhonesList"
const val CHILD_PHOTOS = "photos"
const val PHONES_DELIMITER = "#"

@Suppress("unused")
const val PHOTOS_DELIMITER = "##"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    PHONE = AUTH.currentUser?.phoneNumber.toString()
}

fun initUser() {
    REF_DATABASE_ROOT.child(NODE_USERS).child(PHONE).get()
        .addOnSuccessListener { task ->
            USER = task.getValue(User::class.java) ?: User()

//            USER.friendsPhonesList += "#"
//            USER.friendsPhonesList = USER.friendsPhonesList.repeat(20).dropLast(1)

            if (USER.friendsPhonesList.isNotEmpty()) {
                for (friendPhone in USER.friendsPhonesList.split(PHONES_DELIMITER)) {
                    REF_DATABASE_ROOT.child(NODE_USERS).child(friendPhone).get()
                        .addOnSuccessListener { task2 ->
                            val user = task2.getValue(User::class.java) ?: User()
                            USER.friendsList.add(user)
                        }
                }
            }
        }
}

fun createNewUser(phoneNumber: String): MutableMap<String, Any> {
    val dateMap = mutableMapOf<String, Any>()
    dateMap[CHILD_PHONE] = phoneNumber
    dateMap[CHILD_USERNAME] = "User" + phoneNumber.takeLast(4)
    dateMap[CHILD_FRIENDS_PHONES] = ""
    dateMap[CHILD_PHOTOS] = ""

//    TODO("TESTING")
    if (phoneNumber.last() == '4')
        dateMap[CHILD_FRIENDS_PHONES] = "+16505553435"
    else
        dateMap[CHILD_FRIENDS_PHONES] = "+16505553434"

    return dateMap
}