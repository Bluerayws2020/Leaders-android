package com.example.tasmeme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityManagerBinding

class ManagerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityManagerBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_manager)
        binding.apply {

            navDrawer.itemIconTintList=null
            val navController=Navigation.findNavController(this@ManagerActivity,R.id.fragmentContainerView_Manager)
            NavigationUI.setupWithNavController(navDrawer,navController)

            toggle= ActionBarDrawerToggle(this@ManagerActivity,drawerLayout,R.string.open,R.string.close)
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