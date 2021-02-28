package com.example.p4.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.p4.common.AppPreferences
import com.example.p4.data.network.model.ProcedureModel
import com.example.p4.data.network.services.HomeService
import com.example.p4.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeProceduresViewModel internal constructor(// service
        private val service: HomeService) : BaseViewModel() {

    // shared preferences
    var preferences: AppPreferences? = null

    // proceduresp
    val procedures: MutableLiveData<List<ProcedureModel>>

    init {

        // shared preferences
        preferences = AppPreferences.instance

        // procedures
        procedures = MutableLiveData()
    }

    private fun setProcedures(procedures: List<ProcedureModel>) {

        // family members
        this.procedures.postValue(procedures)
    }

    private inner class ProceduresCallback : Callback<List<ProcedureModel?>> {
        override fun onResponse(call: Call<List<ProcedureModel?>?>, response: Response<List<ProcedureModel?>?>) {
            val procedures = response.body()
            if (procedures != null) {
                setProcedures(procedures as List<ProcedureModel>)
            } else {
                setProcedures(emptyList())
            }
        }

        override fun onFailure(call: Call<List<ProcedureModel?>?>, t: Throwable) {
            // NOTE: Create a dummy result
            val familyMemebersData = ArrayList<ProcedureModel>()
            familyMemebersData.add(createDummyFamilyMemebr("Amy", 68, "https://i2.wp.com/nofiredrills.com/wp-content/uploads/2016/10/myavatar.png?fit=400%2C400&ssl=1"))
            familyMemebersData.add(createDummyFamilyMemebr("David", 24, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1HWDB6J1D4N-80QGiBJ8hgqVq3fFkSkcOTQ&usqp=CAU"))
            familyMemebersData.add(createDummyFamilyMemebr("Kim", 88, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHGqKC8WXE9zYm0uAbibq_YT2Y9DjUzXxlDg&usqp=CAU"))
            familyMemebersData.add(createDummyFamilyMemebr("Mathew", 74, "https://thumbs.dreamstime.com/b/portrait-young-man-beard-hair-style-male-avatar-vector-portrait-young-man-beard-hair-style-male-avatar-105082137.jpg"))
            setProcedures(familyMemebersData)
        }
    }

    fun createDummyFamilyMemebr(name: String, score: Int, imageURL: String) : ProcedureModel{
        val familyMemeberData = ProcedureModel()
        familyMemeberData.name = name
        //familyMemeberData.score = score
        //familyMemeberData.imageURL = imageURL
        return familyMemeberData
    }
}