package ru.l33t.friendview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.l33t.friendview.databinding.FragmentProfileBinding
import ru.l33t.friendview.utils.USER
import ru.l33t.friendview.utils.replaceFragment
import ru.l33t.friendview.R
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initFields()
        return binding.root
    }

    private fun initFields() {
        binding.profileName.text = USER.username
        binding.profileName.setOnClickListener {}
        binding.invitationButton.setOnClickListener {}
        binding.settingsButton.setOnClickListener { replaceFragment(SettingsFragment()) }
    }
}