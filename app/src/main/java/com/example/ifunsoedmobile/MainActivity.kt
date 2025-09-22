package com.unsoed.informatikamobile // Disesuaikan dengan namespace

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.unsoed.informatikamobile.databinding.ActivityMainBinding // Disesuaikan dengan namespace

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        binding.btnToPage2.setOnClickListener {
            // Pastikan Halaman2Activity ada di package yang benar atau import jika berbeda
            startActivity(Intent(this, Halaman2Activity::class.java))
        }
    }
}