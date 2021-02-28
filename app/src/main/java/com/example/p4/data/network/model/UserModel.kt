package com.example.p4.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel : Parcelable {
    @SerializedName("id")
    @Expose
    var userID: Int? = null

    @SerializedName("userName")
    @Expose
    var userName: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

    protected constructor(parcel: Parcel) {
        userID = parcel.readInt()
        userName = parcel.readString()
        email = parcel.readString()
        avatar = parcel.readString()
    }

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(userID!!)
        parcel.writeString(email)
        parcel.writeString(userName)
        parcel.writeString(avatar)
    }

    companion object {
        val CREATOR: Parcelable.Creator<UserModel?> = object : Parcelable.Creator<UserModel?> {
            override fun createFromParcel(parcel: Parcel): UserModel? {
                return UserModel(parcel)
            }

            override fun newArray(size: Int): Array<UserModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}