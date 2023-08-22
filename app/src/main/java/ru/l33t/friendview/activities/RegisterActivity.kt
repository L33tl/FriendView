package ru.l33t.friendview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.l33t.friendview.databinding.ActivityRegisterBinding
import ru.l33t.friendview.fragments.EnterPhoneNumberFragment
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

        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}