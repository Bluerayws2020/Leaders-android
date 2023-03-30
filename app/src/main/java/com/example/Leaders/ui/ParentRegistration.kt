package com.example.Leaders.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nerd_android.helpers.HelperUtils
import com.example.tasmeme.databinding.ActivityParentRegisterationBinding

class ParentRegistration : AppCompatActivity() {
    private lateinit var binding:ActivityParentRegisterationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityParentRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding=DataBindingUtil.setContentView(this,R.layout.activity_parent_registeration)
        HelperUtils.setDefaultLanguage(this,"en")
    }
}