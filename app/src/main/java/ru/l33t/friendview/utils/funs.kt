package ru.l33t.friendview.utils

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.l33t.friendview.R

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity, saveCurrent: Boolean = false) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    if (!saveCurrent) this.finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
    if (addToBackStack) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.dataContainer, fragment)
            .commit()
    } else
        supportFragmentManager.beginTransaction().replace(R.id.dataContainer, fragment).commit()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    this.parentFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.dataContainer, fragment)
        .commit()
}

fun Fragment.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this.activity, activity::class.java)
    startActivity(intent)
}

fun makeLog(message: String, mode: Char = 'd') {
    when (mode.lowercaseChar()) {
        'w' -> Log.w(TAG, "F1R2I3E4D:\t$message")
        'i' -> Log.i(TAG, "F1R2I3E4D:\t$message")
        'e' -> Log.e(TAG, "F1R2I3E4D:\t$message")
        'd' -> Log.d(TAG, "F1R2I3E4D:\t$message")
    }
}