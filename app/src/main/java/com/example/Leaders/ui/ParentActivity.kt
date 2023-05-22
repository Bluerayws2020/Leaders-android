package com.example.tasmeme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Log.e
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils
import com.example.nerd_android.helpers.HelperUtils.ISIN_PER
import com.example.nerd_android.helpers.HelperUtils.ISIN_PER_PRO
import com.example.nerd_android.helpers.HelperUtils.getUID
import com.example.nerd_android.helpers.HelperUtils.logout
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityParentBinding
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ParentActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private val viewModel by viewModels<AppViewModel>()
    private var isBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_parent)
        HelperUtils.setDefaultLanguage(this,"en")
        binding.apply {
            navDrawer.itemIconTintList = null
            navController = findNavController(R.id.fragmentContainerView_parent)
            toggle = ActionBarDrawerToggle(
                this@ParentActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            navDrawer.setNavigationItemSelectedListener {
                try {
                    when (it.itemId) {
                        R.id.profile_per -> {
                            if(isProfileFragmentVisible()){
                                popStack()
                            }
                            navController?.navigate(R.id.parentProfile)
                        }
                        R.id.orders_per -> {
                            if(!isProfileFragmentVisible()){
                                popStack()
                            }
                            navController?.navigate(R.id.orders_per)
                        }
                        R.id.logout_per -> {
                            logout(this@ParentActivity)
                            startActivity(Intent(this@ParentActivity, Sign_In_Activity::class.java))
                            finish()
                        }
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                } catch (e: Exception) {
                    Log.e("ayham", e.toString())

                };true
            }

        }
        getData()
        viewModel.getParentProfileData(getUID(this))


    }

    private fun getData() {
        viewModel.getParentProfileLiveData().observe(this){
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
                        Toast.makeText(this,"Un Expected Error Please Try Again", Toast.LENGTH_SHORT).show()
                    }
                }
                is NetworkResults.Error->{
                    Log.e("ayham", it.exception.toString())
                }
            }
        }
    }


        private var navController: NavController?=null
        private lateinit var binding: ActivityParentBinding
        fun goToProfile() {
            try {
                navController?.navigate(R.id.parentProfile)
            } catch (e: Exception) {
                e("ayham", e.toString())
            }
        }
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
                popStack()
                navController?.navigate(R.id.parentProfile)
            }

        }
    }
    fun openDrawer(){
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
    fun popStack(){
        navController = findNavController(R.id.fragmentContainerView_parent)
        navController?.popBackStack()
    }
    private fun isProfileFragmentVisible(): Boolean {
        val currentDestination = navController?.currentDestination
        return currentDestination?.id == R.id.parentProfile
    }

}
