package ru.l33t.friendview.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ru.l33t.friendview.R
import ru.l33t.friendview.utils.MyPagerAdapter
import ru.l33t.friendview.utils.TabLayoutTabSelectedListener
import ru.l33t.friendview.utils.ViewPagerPageChangeListener

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerPageChangeListener: ViewPagerPageChangeListener
    private lateinit var adapter: MyPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        initViewPager()
        initTabLayout()
    }

    override fun onStart() {
        super.onStart()
        if (false) {
            // If user has already been authorized
        } else {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViewPager() {
        viewPagerPageChangeListener = ViewPagerPageChangeListener(tabLayout)
        viewPager.registerOnPageChangeCallback(viewPagerPageChangeListener)
        adapter = MyPagerAdapter(this)
        viewPager.adapter = adapter
    }

    private fun initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Friends"))
        tabLayout.addTab(tabLayout.newTab().setText("Photo"))
        tabLayout.addTab(tabLayout.newTab().setText("Gallery"))
        tabLayout.addTab(tabLayout.newTab().setText("Profile"))
        viewPager.setCurrentItem(1, false)
        tabLayout.addOnTabSelectedListener(TabLayoutTabSelectedListener(viewPager))
    }
}