package ru.l33t.friendview.utils

import android.content.ContentValues.TAG
import android.util.Log
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

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    PHONE = AUTH.currentUser?.phoneNumber.toString()
    Log.w(TAG, "121212initFirebase: $PHONE")
}

fun initUser() {
    Log.w(TAG, "12121234INITUSER12")
//    REF_DATABASE_ROOT.child(NODE_USERS).child(PHONE)
//        .addListenerForSingleValueEvent(AppValueEventListener {
//            USER = it.getValue(User::class.java) ?: User()
//        })

    REF_DATABASE_ROOT.child(NODE_USERS).child(PHONE).get()
        .addOnSuccessListener {
            USER = it.getValue(User::class.java) ?: User()
            Log.i(TAG, "121212initUserFRIENDIS: ${USER.friendsPhonesList}")

            if (USER.friendsPhonesList.isNotEmpty()) {
                Log.i(TAG, "121212initUser: ${USER.username} has friends")
                for (friendPhone in USER.friendsPhonesList.split("##")) {
                    Log.i(TAG, "121212initUser_FOUND_PHONE: $friendPhone")
                    REF_DATABASE_ROOT.child(NODE_USERS).child(friendPhone).get()
                        .addOnSuccessListener {
                            val user = it.getValue(User::class.java)!!
                            Log.w(TAG, "\n121212initUserPARSE_FRIENDS: $user\n")
                            USER.friendsList.add(user)
                        }
                }
            } else {
                Log.i(TAG, "121212initUserNO_FRIENDS_NUMBERS: $USER")
            }

        }

    Log.w(TAG, "121212init12User: $USER")


    Log.w(TAG, "121212init34User: ${USER.username}")
}