package ru.l33t.friendview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.l33t.friendview.R
import ru.l33t.friendview.utils.BottomPanelFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bottomPanelFragment = BottomPanelFragment()
        fragmentTransaction.add(R.id.bottomPanelContainer, bottomPanelFragment)
        fragmentTransaction.commit()


        setContentView(R.layout.activity_main)

    }
}