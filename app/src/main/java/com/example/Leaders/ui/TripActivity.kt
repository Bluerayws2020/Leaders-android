package com.example.tasmeme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nerd_android.helpers.HelperUtils
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityTripBinding

class TripActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding:ActivityTripBinding
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_trip)
        HelperUtils.setDefaultLanguage(this,"en")
        binding.textView.text="معلوماتي"
        binding.apply {
            navDrawer.itemIconTintList=null
            navController=Navigation.findNavController(this@TripActivity,R.id.fragmentContainerView_Trip)
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
        binding.navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
              R.id.morning_trip-> {
                  binding.textView.text = "الجولة الصباحية"
                  navController.navigate(R.id.morning_trip)
                  binding.drawerLayout.closeDrawer(GravityCompat.START)
                  true
                }
              R.id.evening_trip ->{
                  binding.textView.text = "الجولة المسائية"
                  navController.navigate(R.id.evening_trip)
                  binding.drawerLayout.closeDrawer(GravityCompat.START)
                  true
              }
              R.id.profile -> {
                  binding.textView.text = "معلوماتي"
                  navController.navigate(R.id.profile)
                  binding.drawerLayout.closeDrawer(GravityCompat.START)
                  true
              }
              else->{
                  HelperUtils.logout(this@TripActivity)
                  startActivity(Intent(this@TripActivity,Sign_In_Activity::class.java))
                  binding.drawerLayout.closeDrawer(GravityCompat.START)
                  finish()
                  true
              }
            }
        }



    }
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}