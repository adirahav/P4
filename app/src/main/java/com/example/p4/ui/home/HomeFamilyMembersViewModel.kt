package com.example.p4.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.p4.common.AppPreferences
import com.example.p4.data.DummyData
import com.example.p4.data.network.model.FamilyMemberModel
import com.example.p4.data.network.services.HomeService
import com.example.p4.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFamilyMembersViewModel internal constructor(
        private val service: HomeService) : BaseViewModel() {

    private val TAG = "HomeFamilyMembersViewModel"

    // shared preferences
    var preferences: AppPreferences? = null

    // family members
    val familyMembers: MutableLiveData<List<FamilyMemberModel>>

    init {

        // shared preferences
        preferences = AppPreferences.instance

        // family members
        familyMembers = MutableLiveData()
    }

    fun getFamilyMembers() {

        // family members
        /*service.homeAPI
                .getFamilyMemebrs(preferences!!.getInt("userID", 0))
                ?.enqueue(FamilyMembersCallback())*/

        // TO DELETE - DUMMY CONTENT
        val familyMembersData = DummyData.instance!!.createDummyFamilyMembers()
        setFamilyMembers(familyMembersData)
        // TO DELETE - DUMMY CONTENT
    }

    private fun setFamilyMembers(familyMembers: List<FamilyMemberModel>) {

        // family members
        this.familyMembers.postValue(familyMembers)
    }

    private inner class FamilyMembersCallback : Callback<List<FamilyMemberModel?>?> {
        override fun onResponse(call: Call<List<FamilyMemberModel?>?>, response: Response<List<FamilyMemberModel?>?>) {
            val familyMembers = response.body()
            if (familyMembers != null) {
                setFamilyMembers(familyMembers as List<FamilyMemberModel>)
            } else {
                setFamilyMembers(emptyList())
            }
        }

        override fun onFailure(call: Call<List<FamilyMemberModel?>?>, t: Throwable) {
            Log.e(TAG, "ProcedureCallback(). error = ${t.message}")
        }
    }
}