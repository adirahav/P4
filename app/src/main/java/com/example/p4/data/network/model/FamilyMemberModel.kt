package com.example.p4.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FamilyMemberModel : Parcelable {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("score ")
    @Expose
    var score: Int? = null

    @SerializedName("email")
    @Expose
    private val email: String? = null

    @SerializedName("imageURL")
    @Expose
    var avatarURL: String? = null

    @SerializedName("nextProcedure")
    @Expose
    var nextProcedure: List<ProcedureModel?>? = null

    protected constructor(parcel: Parcel) {
        name = parcel.readString()
        score = parcel.readInt()
        avatarURL = parcel.readString()
        parcel.readList(nextProcedure as List<ProcedureModel>, ProcedureModel::class.java.classLoader)
    }

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(name)
        parcel.writeInt(score!!)
        parcel.writeString(avatarURL)
        parcel.writeList(nextProcedure)
    }

    companion object {
        val CREATOR: Parcelable.Creator<FamilyMemberModel?> = object : Parcelable.Creator<FamilyMemberModel?> {
            override fun createFromParcel(parcel: Parcel): FamilyMemberModel? {
                return FamilyMemberModel(parcel)
            }

            override fun newArray(size: Int): Array<FamilyMemberModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}