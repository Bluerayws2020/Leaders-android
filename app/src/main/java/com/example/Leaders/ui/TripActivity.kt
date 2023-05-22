package com.example.tasmeme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.nerd_android.helpers.HelperUtils
import com.example.nerd_android.helpers.HelperUtils.showMessage
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityTripBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class TripActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityTripBinding
    private lateinit var navController:NavController
    private var isBackPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTripBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HelperUtils.setDefaultLanguage(this,"en")
//        binding.textView.text="معلوماتي"
        binding.apply {
            navDrawer.itemIconTintList=null
            navController=findNavController(R.id.fragmentContainerView_Trip)
            toggle= ActionBarDrawerToggle(this@TripActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()




        }
        binding.navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
              R.id.morning_trip-> {
                  if(!isProfileFragmentVisible()){
                      popStack()
                  }
                  navController.navigate(R.id.morning_trip)
                  binding.drawerLayout.closeDrawer(GravityCompat.START)
                  true
                }
              R.id.evening_trip ->{
//                  binding.textView.text = "الجولة المسائية"
                  if(!isProfileFragmentVisible()){
                      popStack()
                  }
                  navController.navigate(R.id.evening_trip)
                  binding.drawerLayout.closeDrawer(GravityCompat.START)
                  true
              }
              R.id.profile -> {
                  if(isProfileFragmentVisible()){
                      popStack()
                  }
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
            if(isProfileFragmentVisible()){
                if(isBackPressed){
                    super.onBackPressed()
                }else{
                    showMessage(this,getString(R.string.press_again_to_exit))
                    isBackPressed=true
                    MainScope().launch {
                    Handler(Looper.myLooper()!!).postDelayed({
                        isBackPressed = false
                    },3000)
                    }
                }
            }else{
                super.onBackPressed()
            }

        }
    }
    fun openDrawer(){
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
    fun popStack(){
        navController = findNavController(R.id.fragmentContainerView_Trip)
        navController.popBackStack()
    }
    private fun isProfileFragmentVisible(): Boolean {
        val currentDestination = navController.currentDestination
        return currentDestination?.id == R.id.profile
    }

}