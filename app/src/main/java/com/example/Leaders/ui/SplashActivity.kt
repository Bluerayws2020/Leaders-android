package com.example.Leaders.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import com.example.nerd_android.helpers.ContextWrapper
import com.example.nerd_android.helpers.HelperUtils
import com.example.tasmeme.databinding.ActivitySplashBinding
import com.example.tasmeme.ui.ManagerActivity
import com.example.tasmeme.ui.ParentActivity
import com.example.tasmeme.ui.ReceptionActivity
import com.example.tasmeme.ui.Sign_In_Activity
import java.util.*

class SplashActivity  : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivitySplashBinding
    private var navController: NavController? = null

//    private val callback = object :OnBackPressedCallback(false){
//        override fun handleOnBackPressed() {
//            println("HELLO")
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        HelperUtils.setDefaultLanguage(this,"en")

        supportActionBar?.hide()


//        onBackPressedDispatcher.addCallback(this, callback)
//        callback.isEnabled = true


//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }, 3000)

        Handler(Looper.getMainLooper()).postDelayed({
            if (HelperUtils.getUID(this) != "0") {
                openHome()
            }
            else
                openLogin()
        }, 2000)
//        openLogin()




    }

    private fun openHome() {

        val role = HelperUtils.getRole(this)

        if(role=="receptionist"){

            startActivity(Intent(this, ReceptionActivity::class.java))
            finish()}
        else if(role=="department_supervisor"){

            startActivity(Intent(this, ManagerActivity::class.java))
            finish()
        }
        else if(role=="parent"){
            startActivity(Intent(this, ParentActivity::class.java))
            finish()
        }


    }


    private fun openLogin() {
        val intentHome = Intent(this, Sign_In_Activity::class.java)
        startActivity(intentHome)
        finish()
    }



    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

//
//    override fun attachBaseContext(newBase: Context?) {
//        val lang = HelperUtils.getLang(newBase!!)
//        val local = Locale(lang)
//        val newContext = ContextWrapper.wrap(newBase, local)
//        super.attachBaseContext(newContext)
//    }

}