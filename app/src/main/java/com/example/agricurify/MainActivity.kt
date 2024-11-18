package com.example.agricurify

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.agricurify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cek apakah ini pertama kali aplikasi dibuka
        val preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val isFirstRun = preferences.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            // Jika pertama kali, tampilkan OnboardingActivity
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()  // Tutup MainActivity
        } else {
            // Jika bukan pertama kali, lanjutkan ke MainActivity
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val navView: BottomNavigationView = binding.navView
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_profile
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }

        // Set flag supaya Onboarding tidak muncul lagi di masa depan
        val editor = preferences.edit()
        editor.putBoolean("isFirstRun", false)
        editor.apply()
    }
}