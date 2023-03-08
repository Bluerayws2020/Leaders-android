package com.example.tasmeme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.Leaders.model.LoginResponseModel
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.ROLE
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.TOKEN
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.isInputEmpty
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ActivitySignInBinding

class Sign_In_Activity : AppCompatActivity() {
    private val viewModel by viewModels<AppViewModel>()
    private lateinit var binding:ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.
        setContentView(this,R.layout.activity_sign_in)
        binding.paginationProgressBar.hide()


        retriveLogin()

        binding.register.setOnClickListener {
            binding.paginationProgressBar.show()
            if (binding.username.isInputEmpty()) {
                showMessage("Please Enter Username")
                binding.paginationProgressBar.hide()
            } else if (binding.password.isInputEmpty()) {
                showMessage("Please Enter Password")
                binding.paginationProgressBar.hide()
            } else {
                viewModel.login(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )


            }

        }
    }
    private fun saveData(result: NetworkResults.Success<LoginResponseModel>){

        Log.d("ayhamm",result.data.data.uid)
        val sharedPreferences = getSharedPreferences(SHARED_PREF,
            MODE_PRIVATE)
        val editor= sharedPreferences.edit()
        editor.putString(UID,result.data.data.uid)
        editor.putString(TOKEN,result.data.data.token)
        editor.putString(ROLE,result.data.data.role)
        editor.apply()
        binding.paginationProgressBar.hide()

    }
    private fun showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }



 fun retriveLogin(){
     viewModel.getLoginLiveData().observe(this){
             result ->
         when(result){
             is NetworkResults.Success->{
                 if (result.data.status==200){
                     if(result.data.data.role=="receptionist"){
                         saveData(result)
                         startActivity(Intent(this,ReceptionActivity::class.java))
                         finish()}
                     else if(result.data.data.role=="department_supervisor"){
                         saveData(result)
                         startActivity(Intent(this,ManagerActivity::class.java))
                         finish()
                     }

                 }else if(result.data.status==500){
                     binding.paginationProgressBar.hide()
                 }else{
                     binding.paginationProgressBar.hide()
                     showMessage("Un Expected Error")
                 }


             }
             is NetworkResults.Error-> {
                 binding.paginationProgressBar.hide()
                 showMessage("Username or Password are Incorrect")
                 Log.e("Ayham",result.exception.toString())
             }
             else->{
                 //nothing
             }
         }
     }
 }


}