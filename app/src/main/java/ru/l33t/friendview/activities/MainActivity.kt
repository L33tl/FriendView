package ru.l33t.friendview.activities

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ru.l33t.friendview.databinding.ActivityMainBinding
import ru.l33t.friendview.R
import ru.l33t.friendview.utils.AUTH
import ru.l33t.friendview.utils.AppPagerAdapter
import ru.l33t.friendview.utils.TabLayoutTabSelectedListener
import ru.l33t.friendview.utils.ViewPagerPageChangeListener
import ru.l33t.friendview.utils.initFirebase
import ru.l33t.friendview.utils.initUser
import ru.l33t.friendview.utils.replaceActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerPageChangeListener: ViewPagerPageChangeListener
    private lateinit var adapter: AppPagerAdapter
    private lateinit var logOutButton: Button

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
//        AUTH.signOut()
    }

    override fun onStart() {
        super.onStart()
        initFields()

        if (AUTH.currentUser == null) {
            Log.w(TAG, "12121234startREG")
            replaceActivity(RegisterActivity())
        } else {
            Log.w(TAG, "12121234startINIT")
        }
    }

    private fun initFields() {
        initFirebase()
        initUser()

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        logOutButton = findViewById(R.id.log_out_button)
        logOutButton.setOnClickListener {
            AUTH.signOut()
            replaceActivity(RegisterActivity())
        }

        initViewPager()
        initTabLayout()

        Log.w(TAG, "121212initFields")

    }

    private fun initViewPager() {
        viewPagerPageChangeListener = ViewPagerPageChangeListener(tabLayout)
        viewPager.registerOnPageChangeCallback(viewPagerPageChangeListener)
        adapter = AppPagerAdapter(this)
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