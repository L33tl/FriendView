package ru.l33t.friendview.utils

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class ViewPagerPageChangeListener(
    private val tabLayout: TabLayout
): ViewPager2.OnPageChangeCallback() {

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        tabLayout.setScrollPosition(position, 0f, true)
    }
}