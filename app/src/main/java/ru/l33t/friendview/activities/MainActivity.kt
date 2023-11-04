package ru.l33t.friendview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
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
import ru.l33t.friendview.utils.makeLog
import ru.l33t.friendview.utils.replaceActivity
import ru.l33t.friendview.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerPageChangeListener: ViewPagerPageChangeListener
    private lateinit var adapter: AppPagerAdapter
    private lateinit var logOutButton: Button

    private lateinit var binding: ActivityMainBinding

    private var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()

        initViews()

        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onStart() {
        super.onStart()
        initFields()

        if (AUTH.currentUser == null) {
            replaceActivity(RegisterActivity())
        } else {
            makeLog("Main Started with acc ${AUTH.currentUser?.phoneNumber}")
        }
    }

    private fun initFields() {
        initFirebase()
        initUser()
    }

    fun initViews() {
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        initViewPager()
        initTabLayout()
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

    private val onBackPressedCallback = object :
        OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime > 2000) {
                backPressedTime = currentTime
                showToast("Для выхода, нажмите ещё раз")
            } else {
                finish()
            }
        }
    }
}