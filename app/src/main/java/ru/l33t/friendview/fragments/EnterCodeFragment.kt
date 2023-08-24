package ru.l33t.friendview.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import ru.l33t.friendview.R
import ru.l33t.friendview.activities.MainActivity
import ru.l33t.friendview.activities.RegisterActivity
import ru.l33t.friendview.databinding.FragmentEnterCodeBinding
import ru.l33t.friendview.utils.AUTH
import ru.l33t.friendview.utils.AppTextWatcher
import ru.l33t.friendview.utils.CHILD_FRIENDS_PHONES
import ru.l33t.friendview.utils.CHILD_PHONE
import ru.l33t.friendview.utils.CHILD_PHOTOS
import ru.l33t.friendview.utils.CHILD_USERNAME
import ru.l33t.friendview.utils.NODE_USERS
import ru.l33t.friendview.utils.REF_DATABASE_ROOT
import ru.l33t.friendview.utils.replaceActivity
import ru.l33t.friendview.utils.showToast

class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    private lateinit var binding: FragmentEnterCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterCodeBinding.inflate(layoutInflater, container, false)

        (activity as RegisterActivity).title = phoneNumber

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.userInputCode.addTextChangedListener(AppTextWatcher {
            val string = binding.userInputCode.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code = binding.userInputCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)

        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_USERNAME] = "User" + phoneNumber.takeLast(4)
                dateMap[CHILD_FRIENDS_PHONES] = ""
                dateMap[CHILD_PHOTOS] = ""

                Log.w(TAG, "121212enterCode: ${dateMap}")

                REF_DATABASE_ROOT.child(NODE_USERS).child(phoneNumber).updateChildren(dateMap)
                    .addOnCompleteListener { task2 ->
                        showToast("123WELCOME")
                        if (task2.isSuccessful) {
                            showToast("WELCOME")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        } else showToast(task2.exception?.message.toString())
                    }
                    .addOnCanceledListener {
                        showToast("123CANCEL")
                    }
            } else showToast(task.exception?.message.toString())
        }
    }
}