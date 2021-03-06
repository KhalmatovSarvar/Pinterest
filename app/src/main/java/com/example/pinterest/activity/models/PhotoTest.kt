package com.example.pinterest.activity.models

import android.os.Parcel
import android.os.Parcelable

data class PhotoTest(val img: String?):Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoTest> {
        override fun createFromParcel(parcel: Parcel): PhotoTest {
            return PhotoTest(parcel)
        }

        override fun newArray(size: Int): Array<PhotoTest?> {
            return arrayOfNulls(size)
        }
    }
}