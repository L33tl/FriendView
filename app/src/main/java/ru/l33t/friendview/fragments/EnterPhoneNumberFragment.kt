package ru.l33t.friendview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.l33t.friendview.R
import ru.l33t.friendview.databinding.FragmentEnterPhoneNumberBinding
import ru.l33t.friendview.utils.replaceFragment
import ru.l33t.friendview.utils.showToast

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var binding: FragmentEnterPhoneNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentEnterPhoneNumberBinding.inflate(layoutInflater, container, false)

        binding.buttonReg.setOnClickListener { sendCode() }
        return binding.root
    }

    private fun sendCode() {
        val userPhone =
            binding.userPhoneRegionCode.text.toString() + binding.userPhoneNumber.text.toString()
        if (userPhone.isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            replaceFragment(EnterCodeFragment())
        }
    }
}