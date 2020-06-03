package com.example.mystock.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mystock.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            openProfile()
    }

    private fun openProfile() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, ProfileFragment())
            .commit()
    }
}