package com.example.Leaders.model

import android.media.tv.TableRequest


sealed class NetworkResults<out R> {
    data class Success<out T>(val data: T) : NetworkResults<T>()
    data class Error(val exception: Exception) : NetworkResults<Nothing>()
}

//log in response
data class LoginResponseModel(
    val `data`: Data,
    val message: String,
    val status: Int
)

data class Data(
    val email: String,
    val full_name: String,
    val phone: String,
    val role: String,
    val token: String,
    val uid: String,
    val user_name: String
)
data class VUPClasse(
    val `2`: String
)
data class VUPData(
    val classes: List<VUPClasse>,
    val full_name: String,
    val mail: String,
    val phone_number: String,
    val role: String,
    val sections: List<VUPSection>,
    val username: String
)
data class VUPSection(
    val `1`: String,
    val `3`: String
)
data class ViewUserProfileModel
    (
    val `data`: VUPData,
    val status: Int
)
data class ViewAllDeparturesModel(
    val `data`: ViewAllDeparturesData,
    val status: Int
)
data class Departure(
    val departure_type: String,
    val nid: String,
    val person: String,
    val phone_number: String,
    val relative_relation: String,
    val status: String,
    val student: String
)
data class ViewAllDeparturesData(
    val departure: List<Departure>
)
data class UpdateDepartureModel(
    val message: String,
    val status: Int
)
data class GetCurrentDepartureInfoData(
    val count: Int,
    val id: String,
    val name: String
)
data class GetCurrentDepartureInfoModel(
    val `data`: List<GetCurrentDepartureInfoData> ? = null,
    val status: Int,
    val message: String? = null
)
data class ViewParentProfileInfoModel(
    val `data`: ViewParentProfileInfoModelData,
    val status: Int
)
data class ViewParentProfileInfoModelData(
    val classes: List<Classe>,
    val full_name: String,
    val id: String,
    val mail: String,
    val phone_number: String,
    val role: String,
    val sections: List<Section>,
    val students: List<Student>,
    val username: String
)
data class Classe(
    val `2`: String
)
data class Section(
    val `1`: String,
    val `3`: String
)
data class Student(
    val `class`: String,
    val full_name: String,
    val school_bus: String,
    val section: String,
    val sid:String
)

data class RegisterParent(
    val lang:String,
    val email:String,
    val password:String,
    val full_name:String,
    val phone:String,
    val id:String,
    val students: List<ChildData>,
    val mobile_type:String,
    val player_id:String

)
data class ChildData(
    val name:String,
    val department:String,
    val `class`:String,
    val section:String
)


data class ChildListData(
    var name: String,
    var department: String,
    var grade :String,
    var section: String,
    var id :Int
)

data class CreateDepartureModel(
    val status: Int,
    val message: String
)
data class GetTripUsersData(
    val escort: List<Escort>,
    val students: List<TripStudents>

)
data class Escort(
    val eid: String,
    val full_name: String
)
data class GetTripUsers(
    val `data`: GetTripUsersData,
    val status: Int,
    val message: String?=null
)
data class TripStudents(
    val full_name: String,
    val sid: String
)
