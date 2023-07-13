package com.example.tasmeme.ui

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log.e
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils
import com.example.nerd_android.helpers.HelperUtils.ISIN
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.HelperUtils.logout
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityManagerBinding
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ManagerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityManagerBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private val viewModel by viewModels<AppViewModel>()
    private lateinit var uid:String
    private var isBackPressed = false
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_manager)
        HelperUtils.setDefaultLanguage(this,"en")
        binding.apply {

            navDrawer.itemIconTintList=null
            navController=findNavController(R.id.fragmentContainerView_Manager)

            toggle= ActionBarDrawerToggle(this@ManagerActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            val sharedPreferences=getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
            uid=sharedPreferences.getString(UID,"").toString()
            getData()
            viewModel.getUserProfileData(uid)

            navDrawer.setNavigationItemSelectedListener { menuItem ->
                // Handle item selection events
                try{
                    when (menuItem.itemId) {
                        R.id.managerProfile -> {
                            popStack()
                            navController.navigate(R.id.profile)
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        R.id.notifications -> {
                            if(!isProfileFragmentVisible()){
                                popStack()
                            }
                            navController.navigate(R.id.notifications)
                            // Close the navigation drawer
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        R.id.logout_manager ->{
                            logout(this@ManagerActivity)
                            startActivity(Intent(this@ManagerActivity,Sign_In_Activity::class.java))
                            finish()
                            ISIN=false
                        }


                    }
                    true
                }catch (e:java.lang.Exception){
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    e("ayham", e.toString())
                    true
                }
            }


        }
    }

    private fun getData() {
        viewModel.getUserProfileLiveData().observe(this){

            when(it){
                is NetworkResults.Success ->{
                    if(it.data.status==200){
                        val headerLayout= binding.navDrawer.getHeaderView(0)
                        val profileImg=headerLayout.findViewById<CircleImageView>(R.id.profile_image_nav_drawer)
                        val nameTV=headerLayout.findViewById<TextView>(R.id.name_nav_drawerTv)
                        val numberTV=headerLayout.findViewById<TextView>(R.id.phone_numberTv)
                        profileImg.setImageResource(R.drawable.leaders)
                        nameTV.text=it.data.data.full_name
                        numberTV.text=it.data.data.phone_number

                    } else{
                       Toast.makeText(this,"Un Expected Error Please Try Again",Toast.LENGTH_SHORT).show()
                    }
                }
                is NetworkResults.Error->{
                    e("ayham",it.exception.toString())
                }
            }
        }
    }




    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            if(isProfileFragmentVisible()){

                if(isBackPressed){
                    finish()
                }else{
                    HelperUtils.showMessage(this, getString(R.string.press_again_to_exit))
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
    private fun popStack(){
        navController = findNavController(R.id.fragmentContainerView_Manager)
        navController.popBackStack()
    }
    private fun isProfileFragmentVisible(): Boolean {
        val currentDestination = navController.currentDestination
        return currentDestination?.id == R.id.notifications
    }
}