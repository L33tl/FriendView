package ru.l33t.friendview.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.l33t.friendview.fragments.FriendsFragment
import ru.l33t.friendview.fragments.GalleryFragment
import ru.l33t.friendview.fragments.PhotoFragment
import ru.l33t.friendview.fragments.ProfileFragment

class MyPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments =
        arrayOf(FriendsFragment(), PhotoFragment(), GalleryFragment(), ProfileFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}