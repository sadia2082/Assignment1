package com.example.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment1.ui.addrecord.AddRecordFragment

class AddRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addrecord_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddRecordFragment.newInstance())
                .commitNow()
        }
    }
}