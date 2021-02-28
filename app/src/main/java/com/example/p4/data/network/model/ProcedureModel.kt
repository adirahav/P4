package com.example.p4.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProcedureModel : Parcelable {
    @SerializedName("id")
    @Expose
    var procedureID: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("prevent")
    @Expose
    var prevent: String? = null

    @SerializedName("value")
    @Expose
    var value: Int? = null

    @SerializedName("probability")
    @Expose
    var probability: Int? = null

    @SerializedName("probabilityScale")
    @Expose
    var probabilityScale: Int? = null

    @SerializedName("lifePotential")
    @Expose
    var lifePotential: Int? = null

    @SerializedName("lifePotentialGoal")
    @Expose
    var lifePotentialGoal: Int? = null

    @SerializedName("lifePotentialGained")
    @Expose
    var lifePotentialGained: Int? = null

    @SerializedName("personalMessage")
    @Expose
    var personalMessage: String? = null

    @SerializedName("riskFactorList")
    @Expose
    var riskFactorList: List<String>? = null

    @SerializedName("lastVisit")
    @Expose
    var lastVisit: LastVisitModel? = null

    protected constructor(parcel: Parcel) {
        procedureID = parcel.readInt()
        name = parcel.readString()
        description = parcel.readString()
        value = parcel.readInt()
        probability = parcel.readInt()
        probabilityScale = parcel.readInt()
        lifePotential = parcel.readInt()
        lifePotentialGoal = parcel.readInt()
        lifePotentialGained = parcel.readInt()
        personalMessage = parcel.readString()
        parcel.readList(riskFactorList as List<String>, String::class.java.classLoader)
        lastVisit = parcel.readParcelable(String::class.java.classLoader)
    }

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(procedureID!!)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(value!!)
        parcel.writeInt(probability!!)
        parcel.writeInt(probabilityScale!!)
        parcel.writeInt(lifePotential!!)
        parcel.writeInt(lifePotentialGoal!!)
        parcel.writeInt(lifePotentialGained!!)
        parcel.writeString(personalMessage)
        parcel.writeList(riskFactorList)
        parcel.writeParcelable(lastVisit, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProcedureModel?> = object : Parcelable.Creator<ProcedureModel?> {
            override fun createFromParcel(parcel: Parcel): ProcedureModel? {
                return ProcedureModel(parcel)
            }

            override fun newArray(size: Int): Array<ProcedureModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}