package com.discover.soulswipe.presentation.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.discover.soulswipe.R
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.core.utils.getCountryCode
import com.discover.soulswipe.core.utils.gone
import com.discover.soulswipe.core.utils.showToast
import com.discover.soulswipe.core.utils.visible
import com.discover.soulswipe.databinding.ActivityLoginPhoneBinding
import com.discover.soulswipe.presentation.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberActivity : AppCompatActivity(){

    private var numberWithCode = ""

    private lateinit var  binding: ActivityLoginPhoneBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_phone)

        setObservers()
        setClickListeners()
        setCountryCodeIfAvailable()
    }

    private fun setCountryCodeIfAvailable() {
        binding.etCountryCode.setText(getCountryCode(this@PhoneNumberActivity))
    }

    private fun setClickListeners() {
        binding.btnContinue.setOnClickListener {
            val countryCode = binding.etCountryCode.text.toString()
            val phoneNumber = binding.etPhone.text.toString()
            if (countryCode.isEmpty()) {
                showToast(this@PhoneNumberActivity, getString(R.string.please_enter_country_code))
                return@setOnClickListener
            }
            if (phoneNumber.isEmpty()) {
                showToast(this@PhoneNumberActivity, getString(R.string.please_enter_phone_number))
                return@setOnClickListener
            }

            numberWithCode = "$countryCode$phoneNumber"

            loginViewModel.postPhoneNumber(numberWithCode)
        }
    }

    private fun setObservers() {
        loginViewModel.phoneResponse.observe(this@PhoneNumberActivity) { response ->
            when (response) {
                // If data is successfully retrieved, navigate to next screen with data and hide loading view
                is NetworkResult.Success -> binding.apply {
                    binding.progressBar.gone()
                    if (response.data?.status == true) {
                        startActivity(
                            Intent(
                                this@PhoneNumberActivity,
                                OtpActivity::class.java
                            ).putExtra("userNumber", numberWithCode)
                        )
                    } else {
                        showToast(this@PhoneNumberActivity, getString(R.string.phone_number_not_registered))
                    }
                }

                // If there is an error retrieving data, hide loading view
                is NetworkResult.Error -> binding.apply {
                    binding.progressBar.gone()
                    showToast(
                        this@PhoneNumberActivity,
                        getString(R.string.some_error_occurred_please_try_again_in_sometime)
                    )
                }

                // If data is still loading, show loading view
                is NetworkResult.Loading -> binding.apply {
                    binding.progressBar.visible()
                }
            }
        }
    }
}