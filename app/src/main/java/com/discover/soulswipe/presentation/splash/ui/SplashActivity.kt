package com.discover.soulswipe.presentation.splash.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.discover.soulswipe.R
import com.discover.soulswipe.core.DatastorePreferences
import com.discover.soulswipe.core.utils.Constants
import com.discover.soulswipe.databinding.ActivityLoginOtpBinding
import com.discover.soulswipe.databinding.ActivitySplashBinding
import com.discover.soulswipe.presentation.landing.ui.LandingActivity
import com.discover.soulswipe.presentation.login.ui.PhoneNumberActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        initView()
    }

    private fun initView() {
        lifecycleScope.launch {
            DatastorePreferences.getUserToken(this@SplashActivity)
                .collect { token ->
                    if (token.isNotEmpty()){
                        Constants.token = token
                        startActivity(Intent(this@SplashActivity, LandingActivity::class.java))
                        finish()
                    }
                    else{
                        startActivity(Intent(this@SplashActivity, PhoneNumberActivity::class.java))
                        finish()
                    }

                }
        }
    }

}