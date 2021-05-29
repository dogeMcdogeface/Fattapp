package com.example.fattapp

import FLAG_ISNEWUSER
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import currentUser
import firebaseHelper
import kotlinx.android.synthetic.main.a30_main_activity.*


class a3_main_activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController


    fun updateLayout(){
        val headerView : View = nav_view.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.textName)
        val navUserEmail : TextView = headerView.findViewById(R.id.textMail)
        navUsername.text = currentUser.data?.name
        navUserEmail.text = currentUser.mail

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a30_main_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,
            R.id.nav_gallery,
            R.id.nav_userInfo,
            R.id.nav_slideshow,
            R.id.nav_login
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        var newUser =intent.getBooleanExtra(FLAG_ISNEWUSER, false)
        if(newUser){
            navController.navigate(R.id.nav_userInfo)
        }

        firebaseHelper.addUpdateListener {updateLayout()}
        updateLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onBackPressed() {
        if(!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            onSupportNavigateUp()
        }else if(navController.currentDestination?.id != R.id.nav_home){
            navController.navigate(R.id.nav_home)
        }else{
            drawerLayout.closeDrawers()
        }
    }
}