package com.example.quasaapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, /*toolbar,*/ R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
/*
        val navigationView : NavigationView  = findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        var navUsername : TextView = headerView.findViewById(R.id.nav_header_title_id)

        navUsername.text = "123"
*/



        displayScreen(-1)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)

        }

    }

    fun displayScreen(id: Int) {
        val fragment = when (id) {


            R.id.nav_create_task -> {
                CreateTask()
            }
            R.id.nav_my_tasks -> {
                MyTasks()
            }
            R.id.nav_all_tasks -> {
                AllTasks()
            }
            R.id.nav_be_approved -> {
                UserCard()
            }


            else -> {
                Login()
            }
        }


        supportFragmentManager.beginTransaction()
            .replace(R.id.relativelayout, fragment)
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displayScreen(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun closeDrawer() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    fun changeUsername(userName:String){

        val navigationView : NavigationView = findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        var navUsername : TextView = headerView.findViewById(R.id.nav_header_title_id)

        navUsername.text = userName
    }
}


