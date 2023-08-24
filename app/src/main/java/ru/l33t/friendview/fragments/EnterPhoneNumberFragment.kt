package ru.l33t.friendview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import ru.l33t.friendview.R
import ru.l33t.friendview.activities.MainActivity
import ru.l33t.friendview.activities.RegisterActivity
import ru.l33t.friendview.databinding.FragmentEnterPhoneNumberBinding
import ru.l33t.friendview.utils.AUTH
import ru.l33t.friendview.utils.replaceActivity
import ru.l33t.friendview.utils.replaceFragment
import ru.l33t.friendview.utils.showToast
import java.util.concurrent.TimeUnit

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var binding: FragmentEnterPhoneNumberBinding
    private lateinit var phoneNumber: String
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentEnterPhoneNumberBinding.inflate(layoutInflater, container, false)


        binding.buttonReg.setOnClickListener { sendCode() }
        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("121212WELCOME")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast("121212" + task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                showToast(exception.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                replaceFragment(EnterCodeFragment(phoneNumber, id))
            }
        }
        return binding.root
    }

    private fun sendCode() {
        val regionCode = binding.userPhoneRegionCode.text.toString()
        val numberCode = binding.userPhoneNumber.text.toString()
        phoneNumber = regionCode + numberCode

        if (phoneNumber.isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()
        }
    }

    private fun authUser() {
        val options = PhoneAuthOptions.newBuilder()
            .setPhoneNumber(phoneNumber)
            .setTimeout(60, TimeUnit.SECONDS)
            .setActivity(activity as RegisterActivity)
            .setCallbacks(callback).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}