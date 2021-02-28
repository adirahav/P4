package com.example.p4.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LastVisitModel : Parcelable {
    @SerializedName("docName")
    @Expose
    var doctorName: String? = null

    @SerializedName("dateTime")
    @Expose
    var visitDateTime: String? = null

    @SerializedName("rate")
    @Expose
    var rate: Float? = null


    protected constructor(parcel: Parcel) {
        doctorName = parcel.readString()
        visitDateTime = parcel.readString()
        rate = parcel.readFloat()
    }

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(doctorName)
        parcel.writeString(visitDateTime)
        parcel.writeFloat(rate!!)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LastVisitModel?> = object : Parcelable.Creator<LastVisitModel?> {
            override fun createFromParcel(parcel: Parcel): LastVisitModel? {
                return LastVisitModel(parcel)
            }

            override fun newArray(size: Int): Array<LastVisitModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}