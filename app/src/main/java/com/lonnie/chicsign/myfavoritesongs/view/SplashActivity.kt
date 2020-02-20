package com.lonnie.chicsign.myfavoritesongs.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lonnie.chicsign.myfavoritesongs.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val mIntent = Intent(this, MainActivity::class.java)
        startActivity(mIntent)
    }
}