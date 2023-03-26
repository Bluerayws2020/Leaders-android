package com.example.tasmeme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityDepartmentManagerBinding
import com.example.tasmeme.databinding.FragmentTeamLeaderBinding

class DepartmentManagerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDepartmentManagerBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_department_manager)
        binding.apply {

            navDrawer.itemIconTintList=null
            val navController=Navigation.findNavController(this@DepartmentManagerActivity,R.id.fragmentContainerView_Department_Manager)
            NavigationUI.setupWithNavController(navDrawer,navController)
            toggle=
                ActionBarDrawerToggle(this@DepartmentManagerActivity,drawerLayout,R.string.open,R.string.close)
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