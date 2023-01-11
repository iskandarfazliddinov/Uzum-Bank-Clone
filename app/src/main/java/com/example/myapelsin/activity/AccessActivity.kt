package com.example.myapelsin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.myapelsin.R
import com.example.myapelsin.databinding.ActivityAccessBinding
import java.util.concurrent.Executor

class AccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccessBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    val i = Intent(this@AccessActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }

            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometriyadan foydalanish")
            .setNegativeButtonText("BEKOR QILISH")
            .setSubtitle("Biometrik ma`lumotlardan foydalanga\nholda tizimga kiring")
            .build()


        if (biometricsAvailable()) {
            biometricPrompt.authenticate(promptInfo)
            binding.btnAuth.setOnClickListener {
                val i = Intent(this@AccessActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }

    }

    private fun biometricsAvailable(): Boolean {
        val biometricManager = BiometricManager.from(this)
        return when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                true
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                false
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                false
            }
            else -> false
        }
    }
}