package com.example.tasmeme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityParentBinding

class ParentActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityParentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_parent)
        binding.apply {
            navDrawer.itemIconTintList=null
            val navController=Navigation.findNavController(this@ParentActivity,R.id.fragmentContainerView_parent)
            NavigationUI.setupWithNavController(navDrawer,navController)
            toggle= ActionBarDrawerToggle(this@ParentActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            sideMenuOpener.setOnClickListener{
                drawerLayout.openDrawer(GravityCompat.END)
            }
            backButton.setOnClickListener {
                onBackPressed()

            }
        }

    }
}