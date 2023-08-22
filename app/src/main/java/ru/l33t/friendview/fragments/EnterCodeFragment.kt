package ru.l33t.friendview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.l33t.friendview.R
import ru.l33t.friendview.databinding.FragmentEnterCodeBinding
import ru.l33t.friendview.utils.AppTextWatcher
import ru.l33t.friendview.utils.showToast

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    private lateinit var binding: FragmentEnterCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterCodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.userInputCode.addTextChangedListener(AppTextWatcher {
            val string = binding.userInputCode.text.toString()
            if (string.length == 6) {
                verificateCode()
            }
        })
    }

    private fun verificateCode() {
        showToast("OK")
    }
}