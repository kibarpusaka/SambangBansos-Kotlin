package com.example.sambang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.sambang.Login.Login
import com.example.sambang.SharedPref.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_header.*
import kotlinx.android.synthetic.main.navigation_header.view.*

class MainActivity : AppCompatActivity()  {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var sessionManager : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tv_nama_profile.text = "User\n${user?.username}"
        sessionManager = SessionManager(this)
        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this, R.id.fragment_container)
        NavigationUI.setupWithNavController(nav_view, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        setupDrawerView()
    }

    private fun setupDrawerView(){
        val user = sessionManager.getUserData()
        user?.let { u ->
            nav_view.getHeaderView(0).tv_nama_profile.text = u.username
            nav_view.getHeaderView(0).im_log_out_profile.setOnClickListener {
                doLogout()
            }
        }

        //        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
//        val headerView: View = navigationView.getHeaderView(0)
//        headerView.findViewById(R.id.navUsername).text = "Your Text Here"
    }

    private fun doLogout(){
        sessionManager.signOut()
        goToLoginActivity()
    }

    override fun onStart() {
        super.onStart()
        if (!isLoggedIn()){
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity(){
        startActivity(Intent(this@MainActivity, Login::class.java))
        finish()
    }

    private fun isLoggedIn() : Boolean {
        val temp = sessionManager.getUserToken()
        return temp.isNotEmpty()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}