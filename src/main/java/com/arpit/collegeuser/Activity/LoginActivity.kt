package com.arpit.collegeuser.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arpit.collegeuser.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val signInRequest = 45

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        var intent = Intent()
        intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(intent, signInRequest)
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == signInRequest) {
            val task =
                    GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } catch (e: ApiException) {
                Log.e("TAG", "signInResult:failed code=" + e.statusCode)
            }
        }
    }

}