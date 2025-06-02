package com.example.material_components_demo.ui.home.adaptive

import android.os.Parcel
import android.os.Parcelable

data class Email(
    val sender: String,
    val subject: String,
    val preview: String,
    val timestamp: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sender)
        parcel.writeString(subject)
        parcel.writeString(preview)
        parcel.writeString(timestamp)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Email> {
        override fun createFromParcel(parcel: Parcel): Email = Email(parcel)
        override fun newArray(size: Int): Array<Email?> = arrayOfNulls(size)
    }
}