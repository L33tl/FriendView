package ru.l33t.friendview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.l33t.friendview.R
import ru.l33t.friendview.databinding.FragmentEnterPhoneNumberBinding

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
        val user_input_phone_number =
            binding.userPhoneRegionCode.text.toString() + binding.userPhoneNumber.text.toString()
        if (user_input_phone_number.isEmpty()) {
            Toast.makeText(
                activity,
                getString(R.string.register_toast_enter_phone),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            parentFragmentManager.beginTransaction()
                .replace(R.id.registerDataContainer, EnterCodeFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}