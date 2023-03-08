package com.example.Leaders.viewModels

import android.app.Application
import android.content.SharedPreferences
import android.media.metrics.LogSessionId
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.Leaders.repo.NetworkRepository
import com.example.nerd_android.helpers.HelperUtils
import kotlinx.coroutines.launch
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.Leaders.model.*
import com.google.gson.Gson

class AppViewModel(application:Application):AndroidViewModel(application) {

    private val deviceId = HelperUtils.getAndroidID(application.applicationContext)
    private val repo = NetworkRepository
    private val language = "ar"
    private val uid = HelperUtils.getUID(application.applicationContext)

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(HelperUtils.SHARED_PREF, AppCompatActivity.MODE_PRIVATE)

    private val loginLiveData= MutableLiveData<NetworkResults<LoginResponseModel>>()
    private val viewUserProfileLiveData= MutableLiveData<NetworkResults<ViewUserProfileModel>>()
    private val viewAllDeparturesLiveData=MutableLiveData<NetworkResults<ViewAllDeparturesModel>>()
    private val updateDepartureLiveData=MutableLiveData<NetworkResults<UpdateDepartureModel>>()
    private val currentDepartureInfoLiveData=MutableLiveData<NetworkResults<GetCurrentDepartureInfoModel>>()


    fun getObjectFromSharedPref(tag:String): String? {
        val data = sharedPreferences.getString(tag, "")
        return data
    }

    fun login(email:String,password:String){
        viewModelScope.launch {
            loginLiveData.value=repo.loginUser(
                email,password
            )
        }
    }
    fun getLoginLiveData()=loginLiveData

    fun getUserProfileData(uid:String){
        viewModelScope.launch {
            viewUserProfileLiveData.value=repo.viewUserProfile(uid)
        }
    }
    fun getUserProfileLiveData()=viewUserProfileLiveData

    fun viewAllDepartures(
        uid: String
    ){
        viewModelScope.launch {
            viewAllDeparturesLiveData.value=repo.viewAllDepartures(uid)
        }
    }
    fun getViewAllDeparturesLiveData()=viewAllDeparturesLiveData


    fun updateDepartures(
        uid: String,
        nid:String,
        status:String
    ){
        viewModelScope.launch {
            updateDepartureLiveData.value=repo.updateDepartures(
                nid,uid,status

            )
        }
    }
    fun getUpdateDeparturesLiveData()=updateDepartureLiveData

    fun getCurrentDepartureInfo(
        uid: String
    ){
        viewModelScope.launch {
        currentDepartureInfoLiveData.value=repo.getCurrentDepartureInfo(
            uid
        )}

    }
    fun getCurrentDepartureInfoLiveData()=currentDepartureInfoLiveData
}