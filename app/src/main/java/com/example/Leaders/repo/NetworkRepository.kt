package com.example.Leaders.repo

import com.example.Leaders.api.ApiClient
import com.example.Leaders.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.sql.ClientInfoStatus

object NetworkRepository {


    suspend fun loginUser(
        username:String,
        password:String
    ):NetworkResults<LoginResponseModel>{
        return withContext(Dispatchers.IO){

            val lang="ar"
            val phone_type= "1"
            val phoneTypeRequestBody=phone_type.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val langRequestBody=lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val usernameRequestBody=username.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val passwordRequestBody=password.toRequestBody("multipart/form-data".toMediaTypeOrNull())


            try {
                val result=ApiClient.retrofitService.login(
                    langRequestBody,
                    usernameRequestBody,
                    passwordRequestBody,
                    phoneTypeRequestBody
                )
                NetworkResults.Success(result)

            }catch (e:Exception){
                NetworkResults.Error(e)
            }
        }
    }


    suspend fun viewUserProfile(
        uid:String
    ):NetworkResults<ViewUserProfileModel>{
        return withContext(Dispatchers.IO){
            val lang="ar"
            val langRequestBody=lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val uidRequestBody=uid.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val result=ApiClient.retrofitService.viewUserProfile(
                    langRequestBody,
                    uidRequestBody
                )
                NetworkResults.Success(result)
            }catch (e:Exception){
                NetworkResults.Error(e)
            }
        }
    }

    suspend fun viewAllDepartures(
        uid:String
    ):NetworkResults<ViewAllDeparturesModel>{
        return withContext(Dispatchers.IO){
            val lang ="ar"
            val uidRequestBody=uid.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val langRequestBody=lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try{
                val result=ApiClient.retrofitService.viewAllDepartures(
                    langRequestBody,uidRequestBody
                )
                NetworkResults.Success(result)
            }catch (e:Exception){
                NetworkResults.Error(e)
            }
        }
    }
    suspend fun  updateDepartures(
        nid:String,
        uid: String,
        status: String

    ):NetworkResults<UpdateDepartureModel>{
        return withContext(Dispatchers.IO){
            val lang= "ar"
            val langRequestBody=lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val uidRequestBody=uid.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val nidRequestBody=nid.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val statusRequestBody=status.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val result=ApiClient.retrofitService.updateDeparture(
                    langRequestBody,
                    statusRequestBody,
                    nidRequestBody,
                    uidRequestBody
                )
                NetworkResults.Success(result)
            }catch (e:Exception){
                NetworkResults.Error(e)
            }

        }
    }
    suspend fun getCurrentDepartureInfo(
        uid:String
    ):NetworkResults<GetCurrentDepartureInfoModel>{
        return withContext(Dispatchers.IO){
            val lang ="ar"
            val langRequestBody=lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val uidRequestBody=uid.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val result=ApiClient.retrofitService.getCurrentDepartureInfo(
                    langRequestBody,
                    uidRequestBody
                )
                 NetworkResults.Success(result)
            }catch (e:Exception){
                NetworkResults.Error(e)
            }
        }
    }
}