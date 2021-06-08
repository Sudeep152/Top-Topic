package com.shashank.toptopic.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.shashank.toptopic.AuthActivity
import com.shashank.toptopic.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHost =supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        bottomNavigationView.apply {
            background=null
            menu.getItem(2).isEnabled=false
            setupWithNavController(navHost.findNavController())

        }

        fabNewPost.setOnClickListener {

           navHost.findNavController().navigate(R.id.gloabalActionCreatePostFragment)


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> logout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
       FirebaseAuth.getInstance().signOut()
        Intent(this,AuthActivity::class.java).also {
            startActivity(it)
        }
        finish()

    }
}