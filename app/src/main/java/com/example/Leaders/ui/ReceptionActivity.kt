package com.example.tasmeme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.HelperUtils.logout
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivityReceptionBinding
import de.hdodenhof.circleimageview.CircleImageView

@Suppress("DEPRECATION")
class ReceptionActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReceptionBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private val viewModel by viewModels<AppViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_reception)
        HelperUtils.setDefaultLanguage(this,"en")
        binding.textView.text="معلوماتي"
        binding.apply {
            viewModel.getUserProfileLiveData().observe(this@ReceptionActivity){
                    result->
                when(result){
                    is NetworkResults.Success ->{
                        if(result.data.status==200){
                            val headerLayout=binding.navDrawer.getHeaderView(0)
                            val headerImage=headerLayout.findViewById<CircleImageView>(R.id.profile_image_nav_drawer)
                            headerImage.setImageResource(R.drawable.leaders)
                            val headerName=headerLayout.findViewById<TextView>(R.id.name_nav_drawerTv)
                            headerName.text=result.data.data.full_name
                            val headerNumber=headerLayout.findViewById<TextView>(R.id.phone_numberTv)
                            headerNumber.text=result.data.data.phone_number
                            binding.profileImage.setImageResource(R.drawable.leaders)
                        }}
                    is NetworkResults.Error ->{
                        Log.e("ayham",result.exception.toString())
                    }

                }
            }
            navDrawer.itemIconTintList=null
            val navController=Navigation.findNavController(this@ReceptionActivity,R.id.fragmentContainerView_Reception)
            toggle= ActionBarDrawerToggle(this@ReceptionActivity, drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            binding.navDrawer.setNavigationItemSelectedListener { menuItem ->
                // Handle item selection events
                try{
                    when (menuItem.itemId) {
                        R.id.profile -> {
                            binding.textView.text = "معلوماتي"

                            // Check if the selected item is already checked
                            if (menuItem.isChecked) {
                                // Do nothing
                            } else {
                                // Navigate to the profile fragment
                                navController.navigate(R.id.action_orders_to_profile)
                            }

                            // Close the navigation drawer
                            binding.drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        R.id.orders -> {
                            binding.textView.text = "الطلبات"

                            // Check if the selected item is already checked
                            if (menuItem.isChecked) {
                                // Do nothing
                            } else {
                                // Navigate to the orders fragment
                                navController.navigate(R.id.action_profile_to_orders)
                            }

                            // Close the navigation drawer
                            binding.drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        R.id.logout_re->{
                            logout(this@ReceptionActivity)
                            startActivity(Intent(this@ReceptionActivity,Sign_In_Activity::class.java))
                            finish()
                        }
                    }
                    true
                }catch (e:java.lang.Exception){
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    e("ayham",e.toString())
                    true
                }
                }




            sideMenuOpener.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }

            backButton.setOnClickListener {
                onBackPressed()
            }}
            val sharedPref=getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
                val uid=sharedPref.getString(UID,"")


            viewModel.getUserProfileData(uid.toString())




    }
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }


    }
}