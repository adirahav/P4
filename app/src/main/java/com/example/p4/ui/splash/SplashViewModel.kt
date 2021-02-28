package com.example.p4.ui.splash

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.p4.data.network.model.SplashModel
import com.example.p4.data.network.services.SplashService
import com.example.p4.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.p4.ui.signin.SigninActivity

class SplashViewModel internal constructor(private val activityContext: Context,
                                           private val service: SplashService) : BaseViewModel() {

    // loader
    val loadingStatus: MutableLiveData<Boolean>

    // splash data
    val splashData: MutableLiveData<SplashModel>

    init {
        // loader
        loadingStatus = MutableLiveData()

        // splash data
        splashData = MutableLiveData()
    }

    fun getSplashData() {

        // loader
        setIsLoading(true)

        // splash
        service.splashAPI
                .splash
                ?.enqueue(SplashCallback())
    }

    private fun setIsLoading(loading: Boolean) {
        loadingStatus.postValue(loading)
    }

    private fun setSplashData(splashData: SplashModel) {

        // NOTE: do something with this data (for example decide if the user should
        //       go to login/sign up page, save important data on the device, etc.

        // loader
        setIsLoading(false)

        // splash data
        this.splashData.postValue(splashData)

        // goto SigninActivity
        SigninActivity.start(activityContext)
    }

    private inner class SplashCallback : Callback<SplashModel?> {
        override fun onResponse(call: Call<SplashModel?>, response: Response<SplashModel?>) {

            // NOTE: We will never reach the success scope, because the get URL is incorrect
            val splashData = response.body()
            setSplashData(splashData!!)
        }

        override fun onFailure(call: Call<SplashModel?>, t: Throwable) {
            // NOTE: Create a dummy result and wait 5 seconds to simulate a server call
            val splashData = SplashModel()
            splashData.data1 = "data1"
            splashData.data2 = "data2"
            Thread.sleep(5000)
            setSplashData(splashData)
        }
    }
}