package ru.l33t.friendview.activities

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import ru.l33t.friendview.databinding.ActivityMainBinding
import ru.l33t.friendview.R
import ru.l33t.friendview.utils.AUTH
import ru.l33t.friendview.utils.MyPagerAdapter
import ru.l33t.friendview.utils.TabLayoutTabSelectedListener
import ru.l33t.friendview.utils.ViewPagerPageChangeListener
import ru.l33t.friendview.utils.replaceActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerPageChangeListener: ViewPagerPageChangeListener
    private lateinit var adapter: MyPagerAdapter
    private lateinit var logOutButton: Button

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFields()
        initViewPager()
        initTabLayout()
    }

    override fun onStart() {
        super.onStart()
        Log.w(TAG, "1212onStart: " + AUTH.currentUser)
        if (AUTH.currentUser == null)
            replaceActivity(RegisterActivity())
    }

    private fun initFields() {
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        logOutButton = findViewById(R.id.log_out_button)

        logOutButton.setOnClickListener {
            AUTH.signOut()
            replaceActivity(RegisterActivity())
        }

        AUTH = FirebaseAuth.getInstance()
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