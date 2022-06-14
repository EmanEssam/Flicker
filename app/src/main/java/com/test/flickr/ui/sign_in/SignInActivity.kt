package com.test.flickr.ui.sign_in

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.flickr.R
import com.test.flickr.ui.home.HomeActivity
import com.test.flickr.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    // [END declare_auth]


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        initViews()
    }

    private fun initViews() {
        binding.loginBtn.setOnClickListener {
            val userEmail = binding.loginEmailET.editText?.text
            val userPassword = binding.loginPasswordET.editText?.text

            if (userEmail?.isEmpty()!! || userEmail.isBlank()) {
                binding.loginEmailET.editText?.error = getString(R.string.required_field)
            } else if (userPassword?.isEmpty()!! || userPassword.isBlank()) {
                binding.loginPasswordET.editText?.error = getString(R.string.required_field)
            } else {
                binding.loginProgressBar.visibility = View.VISIBLE
                signIn(
                    binding.loginEmailET.editText?.text.toString(),
                    binding.loginPasswordET.editText?.text.toString(),
                )
            }
        }
    }
    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                binding.loginProgressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, getString(R.string.authentication_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(
                this, getString(R.string.authentication_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    companion object {
        private const val TAG = "EmailPassword"
    }
}