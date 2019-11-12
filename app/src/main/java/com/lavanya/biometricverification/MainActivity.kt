package com.lavanya.biometricverification

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val executor = Executors.newSingleThreadExecutor()
        val activity = this // reference to activity
        val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    // user clicked negative button
                    Toast.makeText(activity,"Authentication succeded", Toast.LENGTH_LONG).show()
                } else {
//                    TODO("Called when an unrecoverable error has been encountered and the operation is complete.")
                }
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Log.e("Hello","fefe")
                runOnUiThread{
                    Toast.makeText(activity,"Authentication succeded", Toast.LENGTH_LONG).show()
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.e("Hello","lolo")
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Set the title to display.")
            .setSubtitle("Set the subtitle to display.")
            .setDescription("Set the description to display")
            .setNegativeButtonText("Negative Button")
            .build()

        authenticateButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}