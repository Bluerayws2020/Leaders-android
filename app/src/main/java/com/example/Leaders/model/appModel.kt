package com.example.Leaders.model



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
    val `data`: List<GetCurrentDepartureInfoData>,
    val status: Int
)