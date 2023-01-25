package com.example.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.assignment1.ui.favourite.FavouriteFragment

class FavouriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favourite_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FavouriteFragment())
                .commitNow()
        }
    }
}