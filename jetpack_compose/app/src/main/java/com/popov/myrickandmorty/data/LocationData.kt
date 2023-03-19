package com.popov.myrickandmorty.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationData(
    val name: String
) : Parcelable