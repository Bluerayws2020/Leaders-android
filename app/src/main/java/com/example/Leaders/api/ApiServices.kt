package com.example.Leaders.api

import com.example.Leaders.model.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

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
    ): ViewUserProfileModel

    @Multipart
    @POST("app/viewAllDepartures")
    suspend fun viewAllDepartures(
        @Part("lang") lang:RequestBody,
        @Part("uid") uid:RequestBody
    ): ViewAllDeparturesModel

    @Multipart
    @POST("app/updateDeparture")
    suspend fun updateDeparture(
        @Part("lang") lang: RequestBody,
        @Part("status") status:RequestBody,
        @Part("nid") nid:RequestBody,
        @Part("uid") uid: RequestBody

    ): UpdateDepartureModel

    @Multipart
    @POST("app/getCurrentDepartureInfo")
    suspend fun getCurrentDepartureInfo(
        @Part("lang") lang:RequestBody,
        @Part("uid") uid:RequestBody
    ): GetCurrentDepartureInfoModel

    @Multipart
    @POST("app/viewUserProfile")
    suspend fun viewParentUserProfile(
        @Part("lang")lang:RequestBody,
        @Part("uid") uid:RequestBody
    ): ViewParentProfileInfoModel

    @POST("app/SignUp")
    @Headers("Content-Type: application/json")
    suspend fun retrieveParentRegistration(
        @Body parent: RegisterParent
    ): LoginResponseModel

    @Multipart
    @POST("app/createDeparture")
    suspend fun createDeparture(
        @Part("lang") lang: RequestBody,
        @Part("student") student: RequestBody,
        @Part("departure_type") departure_type: RequestBody,
        @Part("uid") uid: RequestBody,
        @Part("other_person") other_person:RequestBody,
        @Part("phone_number") phone_number:RequestBody,
        @Part("relative_relation") relative_relation:RequestBody
    ):CreateDepartureModel

    @POST("app/updateUserProfile")
    @Headers("Content-Type: application/json")
    suspend fun updateParentInfo(
        @Body parent: RegisterParent
    ): LoginResponseModel

    @Multipart
    @POST("app/getTripUsers")
    suspend fun getTripUsers (
        @Part("lang") lang:RequestBody,
        @Part("uid") uid: RequestBody,
        @Part("trip") trip:RequestBody
    ):GetTripUsers


    @Multipart
    @POST("app/updateTrip")
    suspend fun updateTripMorning (
        @Part("lang") lang:RequestBody,
        @Part("uid") uid: RequestBody,
        //        studint Id

        @Part("nid") nid: RequestBody,

        @Part("trip") trip:RequestBody,
        @Part("morning_trip_actions") morning_trip_actions :RequestBody

    ):MessageModel

    @Multipart
    @POST("app/updateTrip")
    suspend fun updateTripEvning (
        @Part("lang") lang:RequestBody,
        @Part("uid") uid: RequestBody,
        //        studint Id

        @Part("nid") nid: RequestBody,

        @Part("trip") trip:RequestBody,

        @Part("evening_trip_actions") evening_trip_actions :RequestBody

    ):MessageModel




    @Multipart
    @POST("app/createTrip")
    suspend fun createTripMorning (
        @Part("lang") lang:RequestBody,
        @Part("uid") uid: RequestBody,

//BUS NUMBER
        @Part("trip") trip: RequestBody,
//1 FOR MORNINFG & 2 FOR EVNING
        @Part("time") time:RequestBody,
//        id of action
        @Part("morning_trip_actions") morning_trip_actions :RequestBody
    ):MessageModel


    @Multipart
    @POST("app/createTrip")
    suspend fun createTripEvning (
        @Part("lang") lang:RequestBody,
        @Part("uid") uid: RequestBody,

//BUS NUMBER
        @Part("trip") trip: RequestBody,
//1 FOR MORNINFG & 2 FOR EVNING
        @Part("time") time:RequestBody,
//        id of action
        @Part("evening_trip_actions") evening_trip_actions :RequestBody
    ):MessageModel



    @Multipart
    @POST("app/getTripFormOptions")
    suspend fun getTripFormOptions (
        @Part("lang") lang:RequestBody,
        @Part("uid") uid: RequestBody


    ):MessageModel




//    what Nid For
//    getTripFormOptions == > Nid
//viewAllTrips == > Nid
//     viewTrip == > Nid
// update Pearent Profile ==>
}
