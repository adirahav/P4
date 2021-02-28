package com.example.p4.ui.signin

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.p4.common.AppPreferences
import com.example.p4.data.DummyData
import com.example.p4.data.network.model.UserModel
import com.example.p4.data.network.services.SigninService
import com.example.p4.ui.base.BaseViewModel
import com.example.p4.ui.home.HomeActivity
import com.example.p4.ui.signin.SigninActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninViewModel internal constructor(private val activityContext: Context,
                                           private val service: SigninService) : BaseViewModel() {

    var _email = ""    // save it just for the dummy content

    // shared preferences
    var preferences: AppPreferences? = null

    // signin data
    val signinData: MutableLiveData<UserModel>

    init {
        // shared preferences
        preferences = AppPreferences.instance

        // signin data
        signinData = MutableLiveData()
    }

    fun submitLogin(email: String, password: String) {
        _email = email

        // login
        /*service.signinAPI
                .setLogin(email, password)
                ?.enqueue(SigninCallback())*/

        // TO DELETE - DUMMY CONTENT
        val userData = DummyData.instance!!.createDummyUserName(email)
        setSigninData(userData)
        // TO DELETE - DUMMY CONTENT
    }

    private fun setSigninData(signinData: UserModel) {

        // save user details
        signinData?.userID?.let { preferences!!.setInt("userID", it, true) }
        preferences!!.setString("userAvatar", signinData.avatar, false)
        preferences!!.setString("userName", signinData.userName, false)

        // signin data
        this.signinData.postValue(signinData)

        // goto HomeActivity
        HomeActivity.start(activityContext)
    }

    private inner class SigninCallback : Callback<UserModel?> {
        override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
            val signinData = response.body()
            setSigninData(signinData!!)
        }

        override fun onFailure(call: Call<UserModel?>, t: Throwable) {

        }
    }
}