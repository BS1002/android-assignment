package com.mahfuznow.android_assignment.model.country

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Flags(val svg: String, val png: String) : Parcelable {

}