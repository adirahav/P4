package com.example.p4.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SplashModel : Parcelable {
    @SerializedName("data1")
    @Expose
    var data1: String? = null

    @SerializedName("data2")
    @Expose
    var data2: String? = null

    protected constructor(parcel: Parcel) {
        data1 = parcel.readString()
        data2 = parcel.readString()
    }

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(data1)
        parcel.writeString(data2)
    }

    companion object {
        val CREATOR: Parcelable.Creator<SplashModel?> = object : Parcelable.Creator<SplashModel?> {
            override fun createFromParcel(parcel: Parcel): SplashModel? {
                return SplashModel(parcel)
            }

            override fun newArray(size: Int): Array<SplashModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}