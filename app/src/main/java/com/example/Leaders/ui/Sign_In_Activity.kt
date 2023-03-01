package com.example.tasmeme.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.Navigation
import com.example.tasmeme.R
import com.google.android.material.navigation.NavigationView

class Sign_In_Activity : AppCompatActivity() {
    private lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        btn=findViewById(R.id.register)
        btn.setOnClickListener{
            val intent =Intent(this,ManagerActivity::class.java)
            startActivity(intent)
        }

    }
}