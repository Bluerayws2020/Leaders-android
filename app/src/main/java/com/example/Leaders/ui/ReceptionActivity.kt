package com.example.tasmeme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityReceptionBinding

class ReceptionActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReceptionBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_reception)

        binding.apply {

            navDrawer.itemIconTintList=null
            val navController=Navigation.findNavController(this@ReceptionActivity,R.id.fragmentContainerView_Reception)
            NavigationUI.setupWithNavController(navDrawer,navController)

            toggle= ActionBarDrawerToggle(this@ReceptionActivity, drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            sideMenuOpener.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.END)
            }

            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }
}