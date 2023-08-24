package ru.l33t.friendview.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.l33t.friendview.R
import ru.l33t.friendview.databinding.FragmentFriendsBinding
import ru.l33t.friendview.utils.AppRecyclerViewAdapter
import ru.l33t.friendview.utils.USER
import ru.l33t.friendview.utils.showToast

class FriendsFragment : Fragment(R.layout.fragment_friends) {

    private lateinit var binding: FragmentFriendsBinding
    private lateinit var friendsRecyclerView: RecyclerView
    private lateinit var friendsViewAdapter: AppRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentFriendsBinding.inflate(layoutInflater, container, false)

        friendsRecyclerView = binding.friendsList
        val linearLayoutManager = LinearLayoutManager(this.context)
        friendsRecyclerView.layoutManager = linearLayoutManager

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "121212FRIENDSonStart: $USER")

        if (USER.friendsList.isNotEmpty()) {
            Log.w(TAG, "121212FRIENDSonResume: ${USER.friendsList}")
            friendsViewAdapter = AppRecyclerViewAdapter(USER.friendsList)
            friendsRecyclerView.adapter = friendsViewAdapter
        } else {
            showToast("You have no friends(")
        }
    }
}