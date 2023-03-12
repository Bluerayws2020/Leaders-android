package com.example.nerd_android.helpers


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*


object HelperUtils {
    const val UID="UID"
    const val TOKEN ="TOKEN"
    const val ROLE= "ROLE"
    const val PROFILE_DATA_TAG="profile_data"
    const val BASE_URL = "http://leaders2.br-ws.com/leaders2/web/"
    const val SHARED_PREF = "Leaders"
    const val FACEBOOK_OR_GOOGLE_PROVIDER = "1"
    const val MANUAL_SIGN_UP = "2"
    const val FACEBOOK = "facebook"
    const val GOOGLE = "google"
    const val PHONE_PROVIDER = "phoneRegister"
    const val CONTACT_US_URL = "front_end/contact_us"
    const val ABOUT_US_URL = "front_end/aboutUs"
    const val GALLERY_REQUEST_CODE = 100
    const val FULL_NAME="FULL_NAME"
    var ISIN=false
    var ISIN_PER=false
    var ISIN_PER_PRO=false
    var CHILD_LIST= mutableListOf(0)
    var IS_IN_CHILD=false




    fun setDefaultLanguage(context: Context, lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
    fun logout(mContext: Context?){
        setUID(mContext,"0")
        setRole(mContext,"0")
    }


    fun setUID(mContext: Context?,uid:String){
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val editor= sharedPreferences?.edit()
        editor?.putString(UID,uid)
        editor?.apply()
    }
    fun setRole(mContext: Context?,role:String){
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val editor= sharedPreferences?.edit()
        editor?.putString(ROLE,role)
        editor?.apply()
    }
    fun getLang(mContext: Context?): String {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getString("lang", "en")!!
    }

    fun getUID(mContext: Context?): String {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getString(UID, "0")!!
    }
    fun getFullName(mContext: Context?): String {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getString(FULL_NAME, "0")!!
    }


    fun getRole(mContext: Context?): String {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getString(ROLE, "0")!!
    }
    fun isBranchSelected(mContext: Context?): Boolean {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getInt("branch_id", 0) != 0
    }


    fun hideKeyBoard(activity: Activity) {
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        (activity.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isNetWorkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return if (networkCapabilities != null) {
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
        } else false
    }


    @SuppressLint("HardwareIds")
    fun getAndroidID(mContext: Context?): String =
        Settings.Secure.getString(mContext?.contentResolver, Settings.Secure.ANDROID_ID)


}