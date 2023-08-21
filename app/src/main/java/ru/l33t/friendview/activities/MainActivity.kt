package ru.l33t.friendview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import ru.l33t.friendview.R
import ru.l33t.friendview.utils.BottomPanelFragment
import ru.l33t.friendview.utils.MyPagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bottomPanelFragment = BottomPanelFragment()
        fragmentTransaction.add(R.id.bottomPanelContainer, bottomPanelFragment)
        fragmentTransaction.commit()

        val viewPager: ViewPager2 = findViewById(R.id.main_pager)
        val adapter = MyPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(1, false)

        val currentPageText: TextView = findViewById(R.id.current_page)

    }
}