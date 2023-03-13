package com.example.Leaders.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.Leaders.repo.NetworkRepository
import kotlinx.coroutines.launch
import com.example.Leaders.model.*


class AppViewModel(application:Application):AndroidViewModel(application) {

   // private val deviceId = HelperUtils.getAndroidID(application.applicationContext)
    private val repo = NetworkRepository
    //private val language = "ar"
    //private val uid = HelperUtils.getUID(application.applicationContext)

//    private val sharedPreferences: SharedPreferences =
//        application.getSharedPreferences(HelperUtils.SHARED_PREF, AppCompatActivity.MODE_PRIVATE)

    private val loginLiveData= MutableLiveData<NetworkResults<LoginResponseModel>>()
    private val viewUserProfileLiveData= MutableLiveData<NetworkResults<ViewUserProfileModel>>()
    private val viewParentProfileLiveData= MutableLiveData<NetworkResults<ViewParentProfileInfoModel>>()
    private val viewAllDeparturesLiveData=MutableLiveData<NetworkResults<ViewAllDeparturesModel>>()
    private val updateDepartureLiveData=MutableLiveData<NetworkResults<UpdateDepartureModel>>()
    private val currentDepartureInfoLiveData=MutableLiveData<NetworkResults<GetCurrentDepartureInfoModel>>()
    private val retrieveParentRegistration=MutableLiveData<NetworkResults<LoginResponseModel>>()
    private val createDepartureLiveData=MutableLiveData<NetworkResults<CreateDepartureModel>>()



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
    fun getParentProfileData(uid:String){
        viewModelScope.launch {
            viewParentProfileLiveData.value=repo.viewParentUserProfile(uid)
        }
    }
    fun getUserProfileLiveData()=viewUserProfileLiveData
    fun getParentProfileLiveData()=viewParentProfileLiveData

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

    fun retrieveParentRegistration(
        nationalNumber:String,
        password: String,
        email: String,
        phoneNumber: String,
        students:List<ChildData>,
        fullName:String
    ){
        viewModelScope.launch {
            retrieveParentRegistration.value=repo.retrieveParentRegistration(
                nationalNumber,password,email,phoneNumber,students,fullName
            )
        }
    }
    fun getRetrieveParentRegistrationLiveData()=retrieveParentRegistration

    fun createDeparture(
        student: String,
        departure: String,
        uid:String,
        otherPerson:String,
        phoneNumber: String,
        relativeRelation:String
    ){
       viewModelScope.launch {
           createDepartureLiveData.value=repo.createDeparture(
               student,
               departure,
               uid,
               otherPerson,
               phoneNumber,
               relativeRelation
           )
       }
    }
    fun getCreateDepartureLiveData()=createDepartureLiveData
}