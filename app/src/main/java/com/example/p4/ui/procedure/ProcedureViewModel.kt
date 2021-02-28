package com.example.p4.ui.procedure

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.p4.data.DummyData
import com.example.p4.data.network.model.ProcedureModel
import com.example.p4.data.network.services.ProcedureService
import com.example.p4.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProcedureViewModel internal constructor(private val service: ProcedureService) : BaseViewModel() {

    private val TAG = "ProcedureViewModel"

    // procedure
    val procedureData: MutableLiveData<ProcedureModel>
    var _procedureID = 0    // save it just for the dummy content

    init {
        // procedure data
        procedureData = MutableLiveData()
    }

    fun getProcedure(procedureID: Int) {

        _procedureID = procedureID

        // family members
        /*service.procedureAPI
                .getProcedure(procedureID)
                ?.enqueue(ProcedureCallback())*/

        // TO DELETE - DUMMY CONTENT
        val procedureData = DummyData.instance!!.createDummyProcedure(_procedureID)
        setProcedure(procedureData)
        // TO DELETE - DUMMY CONTENT
    }

    private fun setProcedure(procedure: ProcedureModel) {
        // procedure
        this.procedureData.postValue(procedure)
    }

    private inner class ProcedureCallback : Callback<ProcedureModel?> {
        override fun onResponse(call: Call<ProcedureModel?>, response: Response<ProcedureModel?>) {
            val procedure = response.body()
            if (procedure != null) {
                setProcedure(procedure)
            }
        }

        override fun onFailure(call: Call<ProcedureModel?>, t: Throwable) {
            Log.e(TAG, "ProcedureCallback(). error = ${t.message}")
        }
    }
}