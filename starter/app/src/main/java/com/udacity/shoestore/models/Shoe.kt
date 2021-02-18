package com.udacity.shoestore.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String,
    var size: Double?,
    var company: String,
    var description: String,
    var image: Bitmap?
) : Parcelable