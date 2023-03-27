package com.example.assignment4_annika_jan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.bottom_nav).setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.ic_api -> {
                    startActivity(Intent(this, APIActivity::class.java))
                    true
                }
                R.id.ic_display -> {
                    startActivity(Intent(this, DisplayActivity::class.java))
                    true
                }
                else -> { false }
            }
        }

    }
}