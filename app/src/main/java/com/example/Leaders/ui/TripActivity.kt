package com.example.tasmeme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityTripBinding

class TripActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding:ActivityTripBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_trip)

        binding.apply {
            navDrawer.itemIconTintList=null
            val navController=Navigation.findNavController(this@TripActivity,R.id.fragmentContainerView_Trip)
            NavigationUI.setupWithNavController(navDrawer,navController)
            toggle= ActionBarDrawerToggle(this@TripActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            sideMenuOpener.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            backButton.setOnClickListener {
                onBackPressed()
            }

        }



    }
}