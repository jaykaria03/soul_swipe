package com.discover.soulswipe.presentation.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.discover.soulswipe.R
import com.discover.soulswipe.core.DatastorePreferences
import com.discover.soulswipe.core.utils.Constants
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.core.utils.gone
import com.discover.soulswipe.core.utils.showToast
import com.discover.soulswipe.core.utils.visible
import com.discover.soulswipe.databinding.ActivityLoginOtpBinding
import com.discover.soulswipe.databinding.ActivityLoginPhoneBinding
import com.discover.soulswipe.presentation.landing.ui.LandingActivity
import com.discover.soulswipe.presentation.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OtpActivity : AppCompatActivity(){
    private lateinit var  binding: ActivityLoginOtpBinding
    private val loginViewModel: LoginViewModel by viewModels()
    var numberWithCode = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_otp)

        numberWithCode = intent.getStringExtra("userNumber").toString()

        binding.tvEnteredPhone.text = numberWithCode

        setObservers()
        setClickListeners()


    }

    private fun setClickListeners() {
        binding.tvEnteredPhone.setOnClickListener {
            finish()
        }

        binding.btnContinue.setOnClickListener {
            val otp = binding.etOtpCode.text.toString()
            if (otp.isEmpty()) {
                showToast(this@OtpActivity, getString(R.string.please_enter_otp))
                return@setOnClickListener
            }

            loginViewModel.postOtp(numberWithCode, otp)
        }
    }

    private fun setObservers() {
        loginViewModel.otpResponse.observe(this@OtpActivity) { response ->
            when (response) {
                // If data is successfully retrieved, navigate to next screen with data and hide loading view
                is NetworkResult.Success -> binding.apply {
                    binding.progressBar.gone()
                    if (response.data?.token != null) {
                        Constants.token = response.data.token
                        lifecycleScope.launch {
                            DatastorePreferences.updateUserToken(this@OtpActivity, Constants.token)
                        }
                        startActivity(Intent(this@OtpActivity, LandingActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                        finish()
                    } else {
                        showToast(this@OtpActivity, getString(R.string.enter_a_valid_otp))
                    }
                }

                // If there is an error retrieving data, hide loading view
                is NetworkResult.Error -> binding.apply {
                    binding.progressBar.gone()
                    showToast(this@OtpActivity, getString(R.string.some_error_occurred_please_try_again_in_sometime))
                }

                // If data is still loading, show loading view
                is NetworkResult.Loading -> binding.apply {
                    binding.progressBar.visible()
                }
            }
        }
    }
}