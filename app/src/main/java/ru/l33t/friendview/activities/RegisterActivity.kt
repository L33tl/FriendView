package ru.l33t.friendview.activities

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.l33t.friendview.databinding.ActivityRegisterBinding
import ru.l33t.friendview.fragments.EnterPhoneNumberFragment
import ru.l33t.friendview.utils.AUTH
import ru.l33t.friendview.utils.replaceActivity
import ru.l33t.friendview.utils.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        if (AUTH.currentUser == null) {
            Log.w(TAG, "1212onStartREG: ")
            replaceFragment(EnterPhoneNumberFragment(), false)
        } else
            replaceActivity(MainActivity())
    }
}