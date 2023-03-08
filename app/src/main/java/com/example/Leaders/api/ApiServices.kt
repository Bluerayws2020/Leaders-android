package com.example.Leaders.api

import com.example.Leaders.model.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServices {
    @Multipart
    @POST("app/login")
    suspend fun login(
        @Part("lang") lang:RequestBody,
        @Part("username") username:RequestBody,
        @Part("password") password:RequestBody,
        @Part("mobile_type") mobile_type:RequestBody
    ) : LoginResponseModel

    @Multipart
    @POST("app/viewUserProfile")
    suspend fun viewUserProfile(
        @Part("lang")lang:RequestBody,
        @Part("uid") uid:RequestBody
    ):ViewUserProfileModel

    @Multipart
    @POST("app/viewAllDepartures")
    suspend fun viewAllDepartures(
        @Part("lang") lang:RequestBody,
        @Part("uid") uid:RequestBody
    ):ViewAllDeparturesModel

    @Multipart
    @POST("app/updateDeparture")
    suspend fun updateDeparture(
        @Part("lang") lang: RequestBody,
        @Part("status") status:RequestBody,
        @Part("nid") nid:RequestBody,
        @Part("uid") uid: RequestBody

    ):UpdateDepartureModel

    @Multipart
    @POST("app/getCurrentDepartureInfo")
    suspend fun getCurrentDepartureInfo(
        @Part("lang") lang:RequestBody,
        @Part("uid") uid:RequestBody
    ):GetCurrentDepartureInfoModel

}