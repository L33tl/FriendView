package ru.l33t.friendview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.l33t.friendview.R
import ru.l33t.friendview.activities.RegisterActivity
import ru.l33t.friendview.databinding.FragmentSettingsBinding
import ru.l33t.friendview.utils.AUTH
import ru.l33t.friendview.utils.replaceActivity

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        initFields()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun initViews() {
        binding.logOutButton.setOnClickListener {
            AUTH.signOut()
            replaceActivity(RegisterActivity())
        }
    }

    private fun initFields() {
    }
}