package com.shashank.toptopic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.shashank.toptopic.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportActionBar?.hide()

        if(FirebaseAuth.getInstance().currentUser !=null){
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
                Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show()
            }
        }
    }
}